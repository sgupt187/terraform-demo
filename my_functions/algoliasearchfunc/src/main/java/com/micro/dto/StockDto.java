package com.micro.dto;

import org.springframework.stereotype.Component;

@Component
public class StockDto {

	private boolean isValueRounded;
	private String stockLevelStatus;
	
	public boolean isValueRounded() {
		return isValueRounded;
	}
	public void setValueRounded(boolean isValueRounded) {
		this.isValueRounded = isValueRounded;
	}
	public String getStockLevelStatus() {
		return stockLevelStatus;
	}
	public void setStockLevelStatus(String stockLevelStatus) {
		this.stockLevelStatus = stockLevelStatus;
	}
}
