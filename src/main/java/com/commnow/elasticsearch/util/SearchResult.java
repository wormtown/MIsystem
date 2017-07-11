package com.commnow.elasticsearch.util;

import java.io.Serializable;

/**
 * 返回结果
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
public class SearchResult implements Serializable{
	private int status;//状态ص�̬
	private String msg;//消息
	private Object data;//存放返回数据
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
}
