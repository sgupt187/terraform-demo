package com.micro.populator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.micro.dto.ImageDto;
import com.micro.dto.PriceDto;
import com.micro.dto.Product;
import com.micro.dto.ProductDto;
import com.micro.dto.StockDto;
import java.util.Currency;

@Component
public class ProductDtoPopulator {
	
	public void populate(Product source,ProductDto target) {
		String lang = target.getLanguage();
		//System.out.println("lang...******.."+lang);
		String curr = target.getCurrency();
		//System.out.println("curr...******.."+curr);
		target.setCatalogVersion(source.getCatalogVersion());
		target.setCode(source.getCode_string());
		
	/*	if(lang.equals("de") || curr.equals("EUR")) {
			target.setName(source.getName_text_de());
			target.setSummary(source.getSummary_text_de());
		}else { */
			target.setName(source.getName_text_en());
			target.setSummary(source.getSummary_text_en());
	//	}

		target.setConfiguratorType(source.getConfiguratorType_string());
		target.setPrice(populatePrice(source,curr,lang));
		target.setStock(populateStock(source));
		List<ImageDto> images = new ArrayList<>();
		images.add(populateImage(source, "thumbnail", "PRIMARY"));
		images.add(populateImage(source, "product", "PRIMARY"));
		target.setImages(images);
	}
	
	
	private PriceDto populatePrice(Product source,String curr,String lang) {
		PriceDto priceDto = new PriceDto();
		Currency cur = Currency.getInstance(curr);  
		String symbol = cur.getSymbol();
		//System.out.println("***** symbol ***** "+symbol);
		String euros = "\u20AC";
		String pund = "\\u00A3";
		/*if(curr.equals("GBP")) {
			priceDto.setCurrencyIso(source.getPrice_gbp_string());
			priceDto.setFormattedValue(String.valueOf(source.getPriceValue_gbp_double())+" "+symbol);
			priceDto.setValue(source.getPriceValue_gbp_double());
		}else { */
			priceDto.setCurrencyIso(source.getPrice_usd_string());
			priceDto.setFormattedValue(String.valueOf(source.getPriceValue_usd_double())+" "+symbol);
			priceDto.setValue(source.getPriceValue_usd_double());
		//}
		priceDto.setPriceType("BUY");
		//System.out.println("**Actual Formatted Value **** "+priceDto.getFormattedValue());
		return priceDto;
	}
	
	private StockDto populateStock(Product source) {
		StockDto stockDto = new StockDto();
		stockDto.setStockLevelStatus(source.getStockLevelStatus_string());
		stockDto.setValueRounded(source.isInStockFlag_boolean());
		return stockDto;
	}
	
	private ImageDto populateImage(Product source,String format,String imageType) {
		ImageDto imageDto = new ImageDto();
		imageDto.setFormat(format);
		imageDto.setImageType(imageType);
		imageDto.setUrl(source.getImg_300W_300H_string());
		return imageDto;
	}
}
