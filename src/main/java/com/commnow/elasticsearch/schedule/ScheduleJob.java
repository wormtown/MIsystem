package com.commnow.elasticsearch.schedule;


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

public class ScheduleJob {
	private static Logger logger = Logger.getLogger(ScheduleJob.class);
	
	@Scheduled(cron="0 20 20 * * ?")
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
			element = XMLUtils.getElements("D:/apache-tomcat-CommnowMi8.0.38/apache-tomcat-8.0.38/webapps/CommnowMi/WEB-INF/classes/schedule.arguments.xml", "companys");
			List<String> list = XMLUtils.readText(element);
			for(String company : list){
				ElasticsearchVo search = new ElasticsearchVo();
				search.setFromDate(today);
				search.setToDate(tomorrow);
				search.setCompany(company);
				List<CompanyNews> newsList = ESClient.searchNewsList(search,"content");
				
				String directory = "D:/Apache2.2/htdocs/ceshi/autogeneration"+today+"/";
				ESClient.createNewsExcel(newsList, directory, company);
				ESClient.createNewsPage(newsList, directory, company);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("写入失败："+e);
		}
	}
	
}
