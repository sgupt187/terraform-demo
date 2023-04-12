package com.micro.dto;

import org.springframework.stereotype.Component;

@Component
public class QueryDto {
	private String url;
	private QueryValueDto query;
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public QueryValueDto getQuery() {
		return query;
	}
	public void setQuery(QueryValueDto query) {
		this.query = query;
	}
	
}
