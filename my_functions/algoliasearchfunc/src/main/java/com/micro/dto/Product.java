package com.micro.dto;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

@Component
public class Product {
	
	//General attributes
	private String catalogVersion;
	private String code_string;
	private String objectID;
	private boolean configurable_boolean;
	private String configuratorType_string;
	private String name_text_en;
	private String name_text_de;
	private String summary_text_en;
	private String summary_text_de;

	// Prices
	private double priceValue_eur_double;
	private double priceValue_gbp_double;
	private double priceValue_usd_double;
	private String price_gbp_string;
	private String price_eur_string;
	private String price_usd_string;

	// Stocks
	private boolean inStockFlag_boolean;
	private String stockLevelStatus_string;

	// Images
	@JsonProperty("img-1200Wx1200H_string")
	private String img_1200W_1200H_String;

	@JsonProperty("img-515Wx515H_string")
	private String img_515W_515H_string;

	@JsonProperty("img-300Wx300H_string")
	private String img_300W_300H_string;

	@JsonProperty("img-96Wx96H_string")
	private String img_96W_96H_string;

	@JsonProperty("img-65Wx65H_string")
	private String img_65W_65H_string;

	@JsonProperty("img-30Wx30H_string")
	private String img_30W_30H_string;

	public String getImg_515W_515H_string() {
		return img_515W_515H_string;
	}

	public void setImg_515W_515H_string(String img_515w_515h_string) {
		img_515W_515H_string = img_515w_515h_string;
	}

	public String getImg_300W_300H_string() {
		return img_300W_300H_string;
	}

	public void setImg_300W_300H_string(String img_300w_300h_string) {
		img_300W_300H_string = img_300w_300h_string;
	}

	public String getImg_96W_96H_string() {
		return img_96W_96H_string;
	}

	public void setImg_96W_96H_string(String img_96w_96h_string) {
		img_96W_96H_string = img_96w_96h_string;
	}

	public String getImg_65W_65H_string() {
		return img_65W_65H_string;
	}

	public void setImg_65W_65H_string(String img_65w_65h_string) {
		img_65W_65H_string = img_65w_65h_string;
	}

	public String getImg_30W_30H_string() {
		return img_30W_30H_string;
	}

	public void setImg_30W_30H_string(String img_30w_30h_string) {
		img_30W_30H_string = img_30w_30h_string;
	}

	public String getImg_1200W_1200H_String() {
		return img_1200W_1200H_String;
	}

	public void setImg_1200W_1200H_String(String img_1200w_1200h_String) {
		img_1200W_1200H_String = img_1200w_1200h_String;
	}

	public boolean isInStockFlag_boolean() {
		return inStockFlag_boolean;
	}

	public void setInStockFlag_boolean(boolean inStockFlag_boolean) {
		this.inStockFlag_boolean = inStockFlag_boolean;
	}

	public String getStockLevelStatus_string() {
		return stockLevelStatus_string;
	}

	public void setStockLevelStatus_string(String stockLevelStatus_string) {
		this.stockLevelStatus_string = stockLevelStatus_string;
	}

	public double getPriceValue_eur_double() {
		return priceValue_eur_double;
	}

	public void setPriceValue_eur_double(double priceValue_eur_double) {
		this.priceValue_eur_double = priceValue_eur_double;
	}

	public double getPriceValue_gbp_double() {
		return priceValue_gbp_double;
	}

	public void setPriceValue_gbp_double(double priceValue_gbp_double) {
		this.priceValue_gbp_double = priceValue_gbp_double;
	}

	public double getPriceValue_usd_double() {
		return priceValue_usd_double;
	}

	public void setPriceValue_usd_double(double priceValue_usd_double) {
		this.priceValue_usd_double = priceValue_usd_double;
	}

	public String getPrice_gbp_string() {
		return price_gbp_string;
	}

	public void setPrice_gbp_string(String price_gbp_string) {
		this.price_gbp_string = price_gbp_string;
	}

	public String getName_text_en() {
		return name_text_en;
	}

	public void setName_text_en(String name_text_en) {
		this.name_text_en = name_text_en;
	}

	public String getObjectID() {
		return objectID;
	}

	public void setObjectID(String objectID) {
		this.objectID = objectID;
	}

	public String getCode_string() {
		return code_string;
	}

	public void setCode_string(String code_string) {
		this.code_string = code_string;
	}

	public String getName_text_de() {
		return name_text_de;
	}

	public void setName_text_de(String name_text_de) {
		this.name_text_de = name_text_de;
	}

	public String getSummary_text_en() {
		return summary_text_en;
	}

	public void setSummary_text_en(String summary_text_en) {
		this.summary_text_en = summary_text_en;
	}

	public String getSummary_text_de() {
		return summary_text_de;
	}

	public void setSummary_text_de(String summary_text_de) {
		this.summary_text_de = summary_text_de;
	}

	public boolean isConfigurable_boolean() {
		return configurable_boolean;
	}

	public void setConfigurable_boolean(boolean configurable_boolean) {
		this.configurable_boolean = configurable_boolean;
	}
	public String getConfiguratorType_string() {
		return configuratorType_string;
	}

	public void setConfiguratorType_string(String configuratorType_string) {
		this.configuratorType_string = configuratorType_string;
	}
	public String getCatalogVersion() {
		return catalogVersion;
	}

	public void setCatalogVersion(String catalogVersion) {
		this.catalogVersion = catalogVersion;
	}

	public String getPrice_eur_string() {
		return price_eur_string;
	}

	public void setPrice_eur_string(String price_eur_string) {
		this.price_eur_string = price_eur_string;
	}

	public String getPrice_usd_string() {
		return price_usd_string;
	}

	public void setPrice_usd_string(String price_usd_string) {
		this.price_usd_string = price_usd_string;
	}
}
