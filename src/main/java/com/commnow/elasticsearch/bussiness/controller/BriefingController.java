package com.commnow.elasticsearch.bussiness.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.commnow.elasticsearch.bussiness.service.EsService;
import com.commnow.elasticsearch.vo.BriefingVo;
@Controller
@RequestMapping("/brief")
public class BriefingController {
	
	@Resource
	EsService service;
	
	
	@SuppressWarnings("unused")
	@RequestMapping("/briefing")
	public String execute(String company){
		List<BriefingVo> blocks = service.briefing(company, 500, -10, 10);
		return "jsp/briefing";
	}
}
