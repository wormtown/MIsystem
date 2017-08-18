package com.commnow.elasticsearch.bussiness.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.commnow.elasticsearch.bussiness.entity.CompanyNews;
import com.commnow.elasticsearch.bussiness.service.EsService;
import com.commnow.elasticsearch.util.SearchResult;

@Controller
@RequestMapping("/esclient")
public class CreateExcelController {
	@Resource
	private EsService service;
	
	@RequestMapping("/createExcel")
	@ResponseBody
	public SearchResult execute(String companyName, String scope, String start, String end, String dir){
		List<CompanyNews> list = service.searchNews(companyName, scope, start, end, 5000);
		SearchResult result = service.createExcel(list, dir, companyName);
		return result;
	}
}
