package com.commnow.elasticsearch.bussiness.entity;

import java.util.Date;

public class TbCommnowMedia {
	
	private Integer id;
	private String mediaName;
	private Integer newsTotalCount;
	private Integer newsOriginalCount;
	private Integer newsReprintedCount;
	private Double originalRatio;
	private Date createDate;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMediaName() {
		return mediaName;
	}
	public void setMediaName(String mediaName) {
		this.mediaName = mediaName;
	}
	public Integer getNewsTotalCount() {
		return newsTotalCount;
	}
	public void setNewsTotalCount(Integer newsTotalCount) {
		this.newsTotalCount = newsTotalCount;
	}
	public Integer getNewsOriginalCount() {
		return newsOriginalCount;
	}
	public void setNewsOriginalCount(Integer newsOriginalCount) {
		this.newsOriginalCount = newsOriginalCount;
	}
	public Integer getNewsReprintedCount() {
		return newsReprintedCount;
	}
	public void setNewsReprintedCount(Integer newsReprintedCount) {
		this.newsReprintedCount = newsReprintedCount;
	}
	public Double getOriginalRatio() {
		return originalRatio;
	}
	public void setOriginalRatio(Double originalRatio) {
		this.originalRatio = originalRatio;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	
}
