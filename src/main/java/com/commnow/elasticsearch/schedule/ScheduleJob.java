package com.commnow.elasticsearch.schedule;


import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.dom4j.Element;
import org.springframework.scheduling.annotation.Scheduled;

import com.commnow.elasticsearch.bussiness.entity.CompanyNews;
import com.commnow.elasticsearch.util.ESClient;
import com.commnow.elasticsearch.util.XMLUtils;
import com.commnow.elasticsearch.vo.ElasticsearchVo;
import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.dictionary.py.Pinyin;

public class ScheduleJob {
	private static Logger logger = Logger.getLogger(ScheduleJob.class);
	//private News news = null;
	
	@Scheduled(cron="0 50 23 * * ?")
	public void writeExcelAndPage(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_MONTH,1);
		Date date2 = cal.getTime();
		String today = sdf.format(date);
		String tomorrow = sdf.format(date2);
		Element element = null;
		try {
			element = XMLUtils.getElements(XMLUtils.CONFIG_PATH+"schedule.arguments.xml", "companys");
			List<String> list = XMLUtils.readText(element);
			for(String company : list){
				ElasticsearchVo search = new ElasticsearchVo();
				search.setFromDate(today);
				search.setToDate(tomorrow);
				search.setCompany(company);
				List<CompanyNews> newsList = ESClient.searchNewsList(search,"content",5000);
				
				String text = company;
		        List<Pinyin> pinyinList = HanLP.convertToPinyinList(text);
		        String companyDir = "";
		        for (Pinyin pinyin : pinyinList)
		        {	
		        	companyDir += (""+pinyin);
		        	
		        }
				
		        Calendar date1 = Calendar.getInstance();
		        String rootDir = XMLUtils.FILE_SERVER_DIRECTORY;
		        
		        File file = new File(rootDir + File.separator + date1.get(Calendar.YEAR)
                + File.separator + (date1.get(Calendar.MONTH)+1) + File.separator
                + date1.get(Calendar.DAY_OF_MONTH));
		        
				String directory = file.getAbsolutePath() + File.separator+companyDir+File.separator;
				ESClient.createNewsExcel(newsList, directory, company);
				ESClient.createNewsPage(newsList, directory, company);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("写入失败："+e);
		}
	}
	
}
