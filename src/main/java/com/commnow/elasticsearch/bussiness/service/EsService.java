package com.commnow.elasticsearch.bussiness.service;


import java.util.List;

import com.commnow.elasticsearch.bussiness.entity.CompanyNews;
import com.commnow.elasticsearch.util.SearchResult;
import com.commnow.elasticsearch.vo.BriefingVo;

public interface EsService {

	public List<CompanyNews> searchNews(String companyName, String scope, String start, String end, int limit);
	public SearchResult createExcel(List<CompanyNews> list, String directory, String company);
	public SearchResult createPage(List<CompanyNews> list, String dir, String company);
	public List<BriefingVo> briefing(String company, int limit, int days, int suggestCount);
}
