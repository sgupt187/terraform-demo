package com.micro.populator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.algolia.search.models.indexing.SearchResult;
import com.micro.dto.BreadcrumbDto;
import com.micro.dto.Product;
import com.micro.dto.QueryDto;
import com.micro.dto.QueryValueDto;
import com.micro.dto.search.FacetValueDto;
import com.micro.dto.search.FacetsDto;
import com.micro.dto.search.SearchDto;
import com.micro.dto.search.SearchQueryDto;
import com.micro.dto.search.SearchStateDto;
import static java.util.Map.entry;
import java.util.Currency;

@Component
public class SearchDtoPopulator {

	Map<String, String> categoryIdEn = Map.ofEntries(
		entry("1","Open Catalogue"),
		entry("700","Bracelets"),
	    entry("602","Sun Glasses"),
		entry("601","Wallets"),
		entry("600","Accessories"),
		entry("580","Male Moist"),
		entry("570","Male Dry"),
		entry("560","Woody"),
		entry("550","Gift Packs"),
		entry("540","Natural"),
		entry("530","Special Occasions"),
		entry("510","Latest"),
		entry("420","Party Wear"),
		entry("410","T Shirts"),
		entry("395","Female Moist"),
		entry("390","Female Dry"),
		entry("380","Fruity"), 
		entry("370","Gift Packs"),
		entry("360","Natural"),
		entry("350","Special Occasions"),
		entry("330","Latest"),
		entry("320","Women Perfumes"),
		entry("310","Men Perfumes"),
		entry("220","Travel Bags"),
		entry("210","Handbags"),
		entry("120","Winter"),
		entry("110","Summer"),
		entry("400","Clothes"),
		entry("300","Perfumes"),
		entry("206","Women Pink Bags"),
		entry("205","Women Blue Bags"),
		entry("202","Women Bags"),
		entry("200","Bags"),
		entry("106","Women Pink Shoes"),
		entry("105","Women Blue Shoes"),
		entry("104","Men Pink Shoes"),
		entry("103","Men Blue Shoes"),
		entry("102","Women Shoes"),
		entry("101","Men Shoes"),
		entry("100","Shoes")
	);
	Map<String, String> categoryIdDe = Map.ofEntries(
		entry("1","Open Catalogue"),
		entry("572","Innenraum"),
		entry("106","Zubeh r Flexibles Ladesystem"),
		entry("1201","Exterieur "),
		entry("570","Kofferraum"),
		entry("571","Radnabenabdeckungen"),
		entry("573","Kindersitze")
	);
	Map<String, String> facetNameEn = Map.ofEntries(
		entry("category","Category"),
		entry("priceValue","Price"),
		entry("price","Price")
	);
	
	public void populate(SearchResult<Product>  source, SearchDto target,String query,String lang,String curr) {
		Currency cur = Currency.getInstance(curr); 
		Map<String,Map<String,Long>> facetMap = source.getFacets();
		List<FacetsDto> facets = new ArrayList<>();
		int randomVal=0;
		for (Map.Entry<String, Map<String, Long>> entry : facetMap.entrySet()) {
				FacetsDto facet = new FacetsDto();
				String facetName = entry.getKey();
				String[] catArr = facetName.split("_");
				if (catArr.length>0) {
					facet.setName(facetNameEn.get(catArr[0]));
				}else {
					facet.setName(facetName.replace(curr, cur.getSymbol()));
				}
				facet.setCategory(Boolean.FALSE);
				facet.setMultiSelect(Boolean.TRUE);
				facet.setVisible(Boolean.TRUE);
				facet.setPriority(1000+randomVal);
				facet.setValues(getFacets(entry.getValue(),source,entry.getKey().toString(),curr,query));
				facets.add(facet);
				randomVal++;
		}
		target.setFacets(facets);
		target.setBreadcrumbs(populateBreadCrumbs(query, lang, curr));
	}
	
	private List<FacetValueDto> getFacets(Map<String, Long> facetsValues,SearchResult<Product>  source,String indexProperty,String curr,String query) {
		Currency cur = Currency.getInstance(curr); 
		List<FacetValueDto> facets = new ArrayList<>();
		for (Map.Entry<String, Long> entry : facetsValues.entrySet()) {
			FacetValueDto facetValue = new FacetValueDto();
			if (indexProperty.contains("category_string_mv")) {
				facetValue.setName(categoryIdEn.get(entry.getKey().replace(curr, cur.getSymbol())));
			}else{
				facetValue.setName(categoryIdEn.get(entry.getKey().replace(curr, cur.getSymbol())));
			}
			facetValue.setCount(entry.getValue());
			facetValue.setSelected(Boolean.FALSE);
			facetValue.setQuery(getQueryUpdated(source,indexProperty,entry.getKey(),query));
			facets.add(facetValue);
		}
		return facets;
	}
	
	private SearchStateDto getQueryUpdated(SearchResult<Product>  source,String key,String value,String query) {
		SearchStateDto searchStateDto = new SearchStateDto();
		SearchQueryDto searchQueryDto = new SearchQueryDto();
		String facetVal;
		//String facetVal =query;
		String url;
		
		key = key.contains("_")?key.split("_")[0]:key;

		if(!query.contains("relevance")){
			query += ":relevance"+":"+key+":"+value;
			facetVal = source.getQuery()+":relevance:"+key+":"+value;
			url = "/search?q="+query;
		}
		else{
			query += ":"+key+":"+value;
			facetVal = query; //source.getQuery()+":"+key+":"+value;
			url = "/search?q="+query;//+"%3Arelevance";
		}
		searchQueryDto.setValue(facetVal);
		//String url = "/search?q="+source.getQuery()+"%3Arelevance%3Acategory%3A"+value;
		searchStateDto.setQuery(searchQueryDto);
		searchStateDto.setUrl(url);
		return searchStateDto;
	}
	
	 List<BreadcrumbDto> populateBreadCrumbs(String query,String lang,String curr) {
		Currency cur = Currency.getInstance(curr); 
		List<BreadcrumbDto> breadcrumbs = new ArrayList<>();
		BreadcrumbDto breadcrumbDto = new BreadcrumbDto();
		String[] face = query.split(":");
		System.out.println("***** query...."+query);
		System.out.println("face len...."+face.length);
		if(face.length>=3){
			breadcrumbDto.setFacetCode(face[2]);
			breadcrumbDto.setFacetName(face[2]);
			if (face[2].contains("category")) {
				breadcrumbDto.setFacetValueCode(getFacetname(face[3], lang, curr));
				breadcrumbDto.setFacetValueName(getFacetname(face[3], lang, curr));
			}else{
				breadcrumbDto.setFacetValueCode(face[3].replace(curr, cur.getSymbol()));
				breadcrumbDto.setFacetValueName(face[3].replace(curr, cur.getSymbol()));
			}
		
		QueryDto removedQuery = new QueryDto();
		QueryValueDto queryValue = new QueryValueDto();
		String url = face[0];
		queryValue.setValue(url);
		removedQuery.setQuery(queryValue);
		breadcrumbDto.setRemoveQuery(removedQuery);
		breadcrumbs.add(breadcrumbDto);
		}
		return breadcrumbs;
	}
	

	/*List<BreadcrumbDto> populateBreadCrumbs(String query,String lang,String curr) {
		Currency cur = Currency.getInstance(curr); 
		List<BreadcrumbDto> breadcrumbs = new ArrayList<>();
		BreadcrumbDto breadcrumbDto = new BreadcrumbDto();
		//String query1 = "shoes:relevance:category:100:category:101";
		String[] face = query.split(":");
		System.out.println("face...."+face.length);
		
		for(int i=3;i<face.length;i+=2) {
			if(face[i].contains("_")) {
				breadcrumbDto.setFacetCode(face[i].split("_")[0]);
				breadcrumbDto.setFacetName(face[i].split("_")[0]);
				if (face[2].contains("price")) {
					breadcrumbDto.setFacetValueCode(face[i+1]);
					breadcrumbDto.setFacetValueName(face[i+1]);
				} else {
					breadcrumbDto.setFacetValueCode(getFacetname(face[i+1], lang, curr));
					breadcrumbDto.setFacetValueName(getFacetname(face[i+1], lang, curr).replace(curr, cur.getSymbol()));
				}
			}
			if(face[2].contains("allCategories")) {
				breadcrumbDto.setFacetCode(face[i]);
				breadcrumbDto.setFacetName(face[i]);
				breadcrumbDto.setFacetValueCode(face[i+1]);
				breadcrumbDto.setFacetValueName(face[i+1]);
			}
			QueryDto removedQuery = new QueryDto();
			QueryValueDto queryValue = new QueryValueDto();
			String url = face[0];
			queryValue.setValue(url);
			removedQuery.setQuery(queryValue);
			breadcrumbDto.setRemoveQuery(removedQuery);
			breadcrumbs.add(breadcrumbDto);
		}
		System.out.println("breadcrumbs size....."+breadcrumbs.size());
		
		return breadcrumbs;
	} */
	
	private String getFacetname(String code, String lang, String curr) {
		Currency cur = Currency.getInstance(curr);
		String facetName = categoryIdEn.get(code);
		/*if (lang.equals("de") || curr.equals("eur")) {
			facetName = categoryIdDe.get(code);
		} else {*/
			//facetName = categoryIdEn.get(code);
		//}
		return facetName;
	}
	
}
