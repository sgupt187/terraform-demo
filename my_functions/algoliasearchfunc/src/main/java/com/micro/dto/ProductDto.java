package com.micro.dto;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class ProductDto {

	private String catalogVersion;
	private String code;
	private boolean configurable;
	private String configuratorType;
	private String name;
	private String summary;
	private PriceDto price;
	private StockDto stock;
	private List<ImageDto> images;
	private String language;
	private String currency;
	
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	public List<ImageDto> getImages() {
		return images;
	}
	public void setImages(List<ImageDto> images) {
		this.images = images;
	}
	public boolean isConfigurable() {
		return configurable;
	}
	public void setConfigurable(boolean configurable) {
		this.configurable = configurable;
	}
	public String getConfiguratorType() {
		return configuratorType;
	}
	public void setConfiguratorType(String configuratorType) {
		this.configuratorType = configuratorType;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public PriceDto getPrice() {
		return price;
	}
	public void setPrice(PriceDto price) {
		this.price = price;
	}
	public StockDto getStock() {
		return stock;
	}
	public void setStock(StockDto stock) {
		this.stock = stock;
	}
	public String getCatalogVersion() {
		return catalogVersion;
	}
	public void setCatalogVersion(String catalogVersion) {
		this.catalogVersion = catalogVersion;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	
}
