package com.micro.dto.search;

import org.springframework.stereotype.Component;

@Component
public class SearchStateDto {
	
	private String url;
	private SearchQueryDto query;
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public SearchQueryDto getQuery() {
		return query;
	}
	public void setQuery(SearchQueryDto query) {
		this.query = query;
	}
	
}
