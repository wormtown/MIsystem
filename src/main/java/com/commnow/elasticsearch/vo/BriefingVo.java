package com.commnow.elasticsearch.vo;

import java.util.List;

import com.commnow.elasticsearch.bussiness.entity.CompanyNews;

public class BriefingVo {
	
	
	private String title;
	private String summary;
	private List<CompanyNews> relevantNews;
	
	
	
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public List<CompanyNews> getRelevantNews() {
		return relevantNews;
	}
	public void setRelevantNews(List<CompanyNews> relevantNews) {
		this.relevantNews = relevantNews;
	}
}
