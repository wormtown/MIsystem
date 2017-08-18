package com.commnow.elasticsearch.bussiness.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.ListModel;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.commnow.elasticsearch.bussiness.entity.CompanyNews;
import com.commnow.elasticsearch.util.ESClient;
import com.commnow.elasticsearch.util.SearchResult;
import com.commnow.elasticsearch.vo.BriefingVo;
import com.commnow.elasticsearch.vo.ElasticsearchVo;
import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.suggest.Suggester;

@Service
public class EsServiceImpl implements EsService{
	
	
	
	public List<CompanyNews> searchNews(String companyName, String scope, String start, String end, int limit) {
		List<CompanyNews> list = null;
		ElasticsearchVo search = new ElasticsearchVo();
		search.setFromDate(start);
		search.setToDate(end);
		search.setCompany(companyName);
		list = ESClient.searchNewsList(search,scope, limit);
		
		return list;
	}



	
	public SearchResult createExcel(List<CompanyNews> list, String directory, String company) {
		if(directory == null || directory == ""){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			String dateStamp = sdf.format(date);
			directory = "D:/Apache2.2/htdocs/ceshi/"+dateStamp+"/";
		}
		SearchResult result = new SearchResult();
		ESClient.createNewsExcel(list, directory, company);
		result.setMsg("excel创建成功");
		result.setStatus(0);
		return result;
	}
	
	public SearchResult createPage(List<CompanyNews> list, String dir, String company){
		if(dir == null || dir == ""){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			String dateStamp = sdf.format(date);
			dir = "D:/Apache2.2/htdocs/ceshi/"+dateStamp+"/";
		}
		
		SearchResult result = new SearchResult();
		ESClient.createNewsPage(list, dir, company);
		result.setMsg("页面创建成功");
		result.setStatus(0);
		return result;
	}




	@Override
	/**
	 * param
	 * company:检索的公司名称
	 * limit:检索限制条目数（全局检索限制输出结果数）
	 * days:回溯天数
	 * suggestCount:推荐新闻限制条目数
	 */
	
	public List<BriefingVo> briefing(Date date, String company, int limit, int days, int suggestCount) {
		Map<String,String> newsMap = new HashMap<String,String>();
		Suggester suggester = new Suggester();
		List<BriefingVo> resultList = new ArrayList<BriefingVo>(); 
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_MONTH,1);
		Date date2 = cal.getTime();
		cal.add(Calendar.DAY_OF_YEAR, days);
		Date date3 = cal.getTime();
		String today = sdf.format(date);
		String tomorrow = sdf.format(date2);
		String scope = sdf.format(date3);
		
		ElasticsearchVo search = new ElasticsearchVo();
		search.setFromDate(today);
		search.setToDate(tomorrow);
		search.setCompany(company);
		List<CompanyNews> newsList = ESClient.searchNewsList(search,"content", limit);
		
		search.setFromDate(scope);
		search.setToDate(tomorrow);
		search.setCompany(company);
		//这个list最好是加载在内存中
		List<CompanyNews> relevantNews = ESClient.searchNewsList(search, "content", limit);
 		for(CompanyNews news : newsList){
			String title = news.getTitle();
			if(news.getContent().contains(company)){
				List<String> summary = HanLP.extractSummary(news.getContent(), 5);
				newsMap.put(title.trim(), summary.toString());
			}
		}
 		
 		for(String key : newsMap.keySet()) {
 		   BriefingVo newsBlock = new BriefingVo();
		   newsBlock.setTitle(key);
		   newsBlock.setSummary(newsMap.get(key));
		   List<CompanyNews> rel = new ArrayList<CompanyNews>();
 		   for(CompanyNews relevant : relevantNews){
 			   //hanlp下的推荐方法suggest
 			  suggester.addSentence(relevant.getTitle());
 		   }
 		   //根据key生成推荐，key即是title
 		   List<String> titles = suggester.suggest(key, suggestCount);
 		   for(String title : titles){
 			  for(CompanyNews relevant : relevantNews){
 				  if(title == relevant.getTitle()){
 					  rel.add(relevant);
 				  }
 			  }
 		   }
 		   newsBlock.setRelevantNews(rel);
 		   resultList.add(newsBlock);
 		}
 		
 		return resultList;
	}
		public static void main(String[] args) {
			Map<String, String> newsMap = new HashMap<String,String>();
			Suggester suggester = new Suggester();
			List<BriefingVo> resultList = new ArrayList<BriefingVo>(); 
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.add(Calendar.DAY_OF_MONTH,1);
			Date date2 = cal.getTime();
			cal.add(Calendar.DAY_OF_YEAR, -10);
			Date date3 = cal.getTime();
			String today = sdf.format(date);
			String tomorrow = sdf.format(date2);
			String scope = sdf.format(date3);
			
			ElasticsearchVo search = new ElasticsearchVo();
			search.setFromDate(today);
			search.setToDate(tomorrow);
			search.setCompany("陶氏");
			List<CompanyNews> newsList = ESClient.searchNewsList(search,"content", 500);
			
			search.setFromDate(scope);
			search.setToDate(tomorrow);
			search.setCompany("陶氏");
			//这个list最好是加载在内存中
			List<CompanyNews> relevantNews = ESClient.searchNewsList(search, "content", 500);
	 		for(CompanyNews news : newsList){
				String title = news.getTitle();
				if(news.getContent().contains("陶氏")){
					List<String> summary = HanLP.extractSummary(news.getContent(), 3);
					newsMap.put(title, summary.toString());
				}
			}
	 		
	 		for(String key : newsMap.keySet()) {
	 		   System.out.println("========="+key.replace("\r\n","").trim()+"=========");
	 		   //将一个新闻的标题与相关的新闻集合封装为一个对象
	 		   BriefingVo newsBlock = new BriefingVo();
			   newsBlock.setTitle(key);
			   List<CompanyNews> rel = new ArrayList<CompanyNews>();
	 		   for(CompanyNews relevant : relevantNews){
	 			   //hanlp下的推荐方法suggest
	 			  suggester.addSentence(relevant.getTitle());
	 		   }
	 		   List<String> titles = suggester.suggest(key, 10);
	 		   for(String title : titles){
	 			  for(CompanyNews relevant : relevantNews){
	 				  if(title == relevant.getTitle()){
	 					  System.out.println(relevant.getSourceName()+"      "+relevant.getTitle().replace("\r\n","").trim()+"      "+relevant.getPublishDate());
	 					  rel.add(relevant);
	 				  }
	 			  }
	 		   }
	 		   newsBlock.setRelevantNews(rel);
	 		   resultList.add(newsBlock);
	 		}
	 		
			
		}
}
	
	


