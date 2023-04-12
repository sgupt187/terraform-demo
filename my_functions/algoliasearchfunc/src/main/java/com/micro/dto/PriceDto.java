package com.micro.dto;

import org.springframework.stereotype.Component;

@Component
public class PriceDto {
	private String currencyIso;
	private String formattedValue;
	private String priceType;
	private double value;
	
	public String getCurrencyIso() {
		return currencyIso;
	}
	public void setCurrencyIso(String currencyIso) {
		this.currencyIso = currencyIso;
	}
	public String getFormattedValue() {
		return formattedValue;
	}
	public void setFormattedValue(String formattedValue) {
		this.formattedValue = formattedValue;
	}
	public String getPriceType() {
		return priceType;
	}
	public void setPriceType(String priceType) {
		this.priceType = priceType;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	
	
}
