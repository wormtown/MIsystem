package com.commnow.elasticsearch.bussiness.controller;


import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.commnow.elasticsearch.bussiness.service.EsService;
import com.commnow.elasticsearch.util.SearchResult;


@Controller
@RequestMapping("/esclient")
public class SearchController {
	@Resource
	private EsService service;
	
	@RequestMapping("/createPreview.do")
	@ResponseBody
	public SearchResult execute(String companyName, String scope, String start, String end){
		return null;
	}
	
}
