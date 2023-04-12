package com.micro.dto;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class SearchDto {
	private String type;
	private List<BreadcrumbDto> breadcrumbs;
	private QueryDto currentQuery;
	private List<FacetsDto> facets;
	private String freeTextSearch;
	private PaginationDto pagination;
	private List<ProductDto> products;
	private List<SortsDto> sorts;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public QueryDto getCurrentQuery() {
		return currentQuery;
	}
	public void setCurrentQuery(QueryDto currentQuery) {
		this.currentQuery = currentQuery;
	}
	
	public List<BreadcrumbDto> getBreadcrumbs() {
		return breadcrumbs;
	}
	public void setBreadcrumbs(List<BreadcrumbDto> breadcrumbs) {
		this.breadcrumbs = breadcrumbs;
	}
	public List<FacetsDto> getFacets() {
		return facets;
	}
	public void setFacets(List<FacetsDto> facets) {
		this.facets = facets;
	}
	public List<SortsDto> getSorts() {
		return sorts;
	}
	public void setSorts(List<SortsDto> sorts) {
		this.sorts = sorts;
	}
	public String getFreeTextSearch() {
		return freeTextSearch;
	}
	public void setFreeTextSearch(String freeTextSearch) {
		this.freeTextSearch = freeTextSearch;
	}
	public PaginationDto getPagination() {
		return pagination;
	}
	public void setPagination(PaginationDto pagination) {
		this.pagination = pagination;
	}
	public List<ProductDto> getProducts() {
		return products;
	}
	public void setProducts(List<ProductDto> products) {
		this.products = products;
	}
	
}
