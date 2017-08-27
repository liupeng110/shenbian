package com.sbh.vo.service;

import java.io.Serializable;

public class ImgInfo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String url;
	String text;
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	

}
