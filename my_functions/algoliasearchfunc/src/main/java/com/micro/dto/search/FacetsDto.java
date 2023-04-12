package com.micro.dto.search;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class FacetsDto {
	private String name;
	private Integer priority;
	private Boolean category;
	private Boolean multiSelect;
	private Boolean visible;
	private List<FacetValueDto> topValues;
	private List<FacetValueDto> values;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getPriority() {
		return priority;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	public Boolean getCategory() {
		return category;
	}
	public void setCategory(Boolean category) {
		this.category = category;
	}
	public Boolean getMultiSelect() {
		return multiSelect;
	}
	public void setMultiSelect(Boolean multiSelect) {
		this.multiSelect = multiSelect;
	}
	public Boolean getVisible() {
		return visible;
	}
	public void setVisible(Boolean visible) {
		this.visible = visible;
	}
	public List<FacetValueDto> getTopValues() {
		return topValues;
	}
	public void setTopValues(List<FacetValueDto> topValues) {
		this.topValues = topValues;
	}
	public List<FacetValueDto> getValues() {
		return values;
	}
	public void setValues(List<FacetValueDto> values) {
		this.values = values;
	}
     
}
