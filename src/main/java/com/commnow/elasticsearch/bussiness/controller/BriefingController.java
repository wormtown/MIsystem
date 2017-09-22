package com.commnow.elasticsearch.bussiness.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
	
	@RequestMapping("/index")
	public String index(){
		return "jsp/brief_index";
	}
	
	@RequestMapping("/briefing")
	public String execute(Model model,
			@RequestParam(value="date",required = false) String date,
			@RequestParam(value="company",required = false) String company,
			@RequestParam(value="days",required = false) String days){
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date1 = null;
		try {
			if(date == null || date == ""){
				Date today = new Date();
				String dateStr = sdf.format(today);
				date1 = sdf.parse(dateStr);
			}else{
				date1 = sdf.parse(date);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Integer days2 = Integer.parseInt(days);
		List<BriefingVo> blocks = service.briefing(date1, company, 500, days2, 10);
		model.addAttribute("blocks",blocks);
		return "jsp/briefing";
	}
}
