package com.micro.dto;

import org.springframework.stereotype.Component;

@Component
public class QueryValueDto {

	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
