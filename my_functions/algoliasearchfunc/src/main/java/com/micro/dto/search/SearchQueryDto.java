package com.micro.dto.search;

import org.springframework.stereotype.Component;

@Component
public class SearchQueryDto {
	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}
