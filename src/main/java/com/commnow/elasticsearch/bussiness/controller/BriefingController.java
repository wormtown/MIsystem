package com.commnow.elasticsearch.bussiness.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.commnow.elasticsearch.bussiness.service.EsService;
import com.commnow.elasticsearch.vo.BriefingVo;
@Controller
@RequestMapping("/brief")
public class BriefingController {
	
	@Resource
	EsService service;
	
	
	@RequestMapping("/briefing")
	public String execute(Model model){
		Date date = new Date();
		List<BriefingVo> blocks = service.briefing(date, "陶氏", 500, -10, 10);
		model.addAttribute("blocks",blocks);
		return "jsp/briefing";
	}
}
