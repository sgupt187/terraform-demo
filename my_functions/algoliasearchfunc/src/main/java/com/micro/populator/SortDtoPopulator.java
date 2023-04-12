package com.micro.populator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.algolia.search.models.indexing.SearchResult;
import com.micro.dto.Product;
import com.micro.dto.SortsDto;
import com.micro.dto.search.SearchDto;

@Component
public class SortDtoPopulator {
	public void populate(SearchResult<Product>  source, SearchDto searchDto,String language,String currency) {
		
		Map<String,String> sortsDe = new HashMap<>();
		sortsDe.put("relevance", "Relevanz");
		sortsDe.put("topRated", "Hochstbewertet");
		sortsDe.put("name-asc", "Name (aufsteigend)");
		sortsDe.put("price-asc", "Preis (aufsteigend)");
		sortsDe.put("price-desc", "Preis (absteigend)");
		
		Map<String,String> sortsEe = new HashMap<>();
		sortsEe.put("relevance", "Relevance");
		sortsEe.put("topRated", "Top Rated");
		sortsEe.put("name-asc", "Name (ascending)");
		sortsEe.put("price-asc", "Price (Lowest First)");
		sortsEe.put("price-desc", "Price (Highest First)");
	
		List<SortsDto> sorts = new ArrayList<>();
		/*if(currency.equals("EUR")) {
			for (Map.Entry<String, String> entry : sortsDe.entrySet()) {
				populateSortsDto(entry, sorts);
			}
		}else {*/
			for (Map.Entry<String, String> entry : sortsEe.entrySet()) {
				populateSortsDto(entry, sorts);
			}
		//}
		searchDto.setSorts(sorts);
		
	}
	
	void populateSortsDto(Map.Entry<String, String> entry,List<SortsDto> sorts){
		 SortsDto sortsDto = new SortsDto();
		 sortsDto.setCode(entry.getKey());
		 sortsDto.setName(entry.getValue());
		 sortsDto.setSelected(Boolean.FALSE);
		 sorts.add(sortsDto);
	}
}
