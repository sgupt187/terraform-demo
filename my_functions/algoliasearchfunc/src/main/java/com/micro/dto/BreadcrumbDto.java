package com.micro.dto;

import org.springframework.stereotype.Component;

@Component
public class BreadcrumbDto {
	private String facetCode;
	private String facetName;
	private String facetValueCode;
	private String facetValueName;
	private QueryDto removeQuery;
	
	public String getFacetCode() {
		return facetCode;
	}
	public void setFacetCode(String facetCode) {
		this.facetCode = facetCode;
	}
	public String getFacetName() {
		return facetName;
	}
	public void setFacetName(String facetName) {
		this.facetName = facetName;
	}
	public String getFacetValueCode() {
		return facetValueCode;
	}
	public void setFacetValueCode(String facetValueCode) {
		this.facetValueCode = facetValueCode;
	}
	public String getFacetValueName() {
		return facetValueName;
	}
	public void setFacetValueName(String facetValueName) {
		this.facetValueName = facetValueName;
	}
	public QueryDto getRemoveQuery() {
		return removeQuery;
	}
	public void setRemoveQuery(QueryDto removeQuery) {
		this.removeQuery = removeQuery;
	}
}
