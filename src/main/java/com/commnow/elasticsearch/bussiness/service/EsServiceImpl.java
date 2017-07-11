package com.commnow.elasticsearch.bussiness.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.commnow.elasticsearch.bussiness.entity.CompanyNews;
import com.commnow.elasticsearch.util.DateUtil;
import com.commnow.elasticsearch.util.ESClient;
import com.commnow.elasticsearch.util.SearchResult;
import com.commnow.elasticsearch.vo.ElasticsearchVo;

@Service
public class EsServiceImpl implements EsService{
	
	
	
	public List<CompanyNews> searchNews(String companyName, String scope, String start, String end) {
		List<CompanyNews> list = null;
		ElasticsearchVo search = new ElasticsearchVo();
		search.setFromDate(start);
		search.setToDate(end);
		search.setCompany(companyName);
		list = ESClient.searchNewsList(search,scope);
		
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

}
