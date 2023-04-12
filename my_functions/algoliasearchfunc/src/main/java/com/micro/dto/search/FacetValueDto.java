package com.micro.dto.search;

import org.springframework.stereotype.Component;

@Component
public class FacetValueDto {
	private String name;
	private Long count;
	private SearchStateDto query;
	private Boolean selected;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public SearchStateDto getQuery() {
		return query;
	}
	public void setQuery(SearchStateDto query) {
		this.query = query;
	}
	public Boolean getSelected() {
		return selected;
	}
	public void setSelected(Boolean selected) {
		this.selected = selected;
	}
}
