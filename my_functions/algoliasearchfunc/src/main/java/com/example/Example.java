package com.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.io.BufferedWriter;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;
import java.util.logging.Logger;

import com.google.cloud.functions.HttpFunction;
import com.google.cloud.functions.HttpRequest;
import com.google.cloud.functions.HttpResponse;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import com.algolia.search.DefaultSearchClient;
import com.algolia.search.SearchClient;
import com.algolia.search.SearchIndex;
import com.algolia.search.models.indexing.Query;
import com.algolia.search.models.indexing.SearchResult;

import com.micro.dto.BreadcrumbDto;
import com.micro.dto.search.FacetsDto;
import com.micro.dto.PaginationDto;
import com.micro.dto.Product;
import com.micro.dto.ProductDto;
import com.micro.dto.QueryDto;
import com.micro.dto.QueryValueDto;
import com.micro.dto.search.SearchDto;
import com.micro.dto.SortsDto;
import com.micro.populator.ProductDtoPopulator;
import com.micro.populator.SearchDtoPopulator;
import java.util.logging.Logger;
import java.util.Optional;
import com.google.gson.GsonBuilder;
import com.micro.populator.SortDtoPopulator;

public class Example implements HttpFunction {

  private static final Logger logger = Logger.getLogger(Example.class.getName());
  @Override
  public void service(HttpRequest request, HttpResponse response) throws Exception {
	
	if("OPTIONS".equalsIgnoreCase(request.getMethod())){
	  response.appendHeader("Allow", "GET, HEAD, OPTIONS");
	  response.appendHeader("Access-Control-Allow-Methods","GET, HEAD, OPTIONS");
      response.appendHeader("Access-Control-Allow-Headers", "Content-Type, Authorization, Accept, Accept-Language, X-Anonymous-Consents");
	  response.appendHeader("Access-Control-Expose-Headers","X-Anonymous-Consents");
	  response.appendHeader("Vary","Origin,User-Agent,Access-Control-Request-Method,Access-Control-Request-Headers");
	  response.appendHeader("Access-Control-Allow-Origin","https://jsapps.caibq7rk87-capgemini2-d1-public.model-t.cc.commerce.ondemand.com");
      //response.appendHeader("Access-Control-Max-Age", "3600");
      response.setStatusCode(200);
      return;
	}else{
	response.appendHeader("Content-Type", "application/json");
    BufferedWriter writer = response.getWriter();
	SearchDtoPopulator searchDtoPopulator = new SearchDtoPopulator();
	ProductDtoPopulator productDtoPopulator = new ProductDtoPopulator();
	SortDtoPopulator sortDtoPopulator = new SortDtoPopulator();
    //Gson gson = new Gson();
	Gson gson = new GsonBuilder().disableHtmlEscaping().create();
	
	Optional<String> fields = request.getFirstQueryParameter("fields");
	Optional<String> query = request.getFirstQueryParameter("query");
	Optional<String> pageSize = request.getFirstQueryParameter("pageSize");
	Optional<String> lang = request.getFirstQueryParameter("lang");
	Optional<String> curr = request.getFirstQueryParameter("curr");
	Optional<String> page = request.getFirstQueryParameter("currentPage");
	
		Map<String,String> queryMap = new HashMap<String,String>();
		
		if(fields.isPresent()){
			queryMap.put("fields",fields.get().toString());
		}
		if(query.isPresent()){
			queryMap.put("query",query.get().toString());
		}
		if(pageSize.isPresent()){
			queryMap.put("pageSize",pageSize.get().toString());
		}
		if(lang.isPresent()){
			queryMap.put("lang",lang.get().toString());
		}
		if(curr.isPresent()){
			queryMap.put("curr",curr.get().toString());
		}
		if(page.isPresent()){
        	queryMap.put("page",page.get().toString());
		}
		queryMap.put("facets","*");
		
		System.out.println("query::"+query.get().toString());
		//System.out.println("**** Curr*****"+queryMap.get("curr").toString());
		//System.out.println("**** Lang*****"+queryMap.get("lang").toString());

    SearchClient client = DefaultSearchClient.create("N07QN5D3KU", "e6649095e6de175f7683a4a2fa975bf9");

    SearchIndex<Product> index = client.initIndex("mbdemo", Product.class);
    
	List<String> facetsList = new ArrayList<>();
		//facetsList.add("itemtype_string");
		facetsList.add("category_string_mv");
		//facetsList.add("priceValue_eur_double");
		//facetsList.add("stockLevelStatus_string");
		//facetsList.add("pickupAvailableFlag_boolean");
		/*if(queryMap.get("lang").toString().equals("en") || queryMap.get("curr").toString().equals("USD")) {
			facetsList.add("price_usd_string");
		}else { */
			facetsList.add("price_usd_string");
		//}
		
    //map spartacus query to algolia query 
		
		int pagee = queryMap.get("page")!=null ? Integer.parseInt(queryMap.get("page")) : 0;
		SearchResult<Product> sr = null;
		if(queryMap.get("query").contains(":relevance:")){
			System.out.println("Query---> "+queryMap.get("query"));
			String[] queryObj = queryMap.get("query").split(":");
			String firstQuery = queryObj[0]+":"+queryObj[1]+":";
			String facetFilter = queryMap.get("query").replace(firstQuery, "");
			facetFilter = facetFilter.replace("allCategories", "allCategories_string_mv");
			facetFilter = facetFilter.replace("category", "category_string_mv");
			facetFilter = facetFilter.replace("price", "price_usd_string");
			
			System.out.println("facetFilters--->"+facetFilter);

			List<List<String>> ffList = new ArrayList<>();
			String[] ffArr = facetFilter.split(":");
			for (int i = 0; i < ffArr.length; i += 2) {
				String fq = ffArr[i] + ":" + ffArr[i + 1];
				List<String> faceList = new ArrayList<>();
				System.out.println("fq:::" + fq);
				faceList.add(fq);
				ffList.add(faceList);
			}
			System.out.println("ffList size..."+ffList.size());
			sr = index.search(
				new Query(queryObj[0])
				.setAttributesToRetrieve(Arrays.asList("*"))
				.setFacets(facetsList)
				.setHitsPerPage(Integer.parseInt(queryMap.get("pageSize")))
				.setFacetFilters(ffList)
				.setPage(pagee)
			);
		}else{
			sr = index.search(
				new Query(queryMap.get("query"))
				.setAttributesToRetrieve(Arrays.asList("*"))
				.setFacets(facetsList)
				.setHitsPerPage(Integer.parseInt(queryMap.get("pageSize")))
				.setPage(pagee)
			);
		} 
		

		//writer.write("Products size : "+sr.getHits().size());

		List<ProductDto> productDto = new ArrayList<>();
		SearchDto searchDto = new SearchDto();
		
		List<Product> prodResult = sr.getHits();
		System.out.println("Product size..."+prodResult.size());

		for(Product prod:sr.getHits()) {
			if (prod.getCatalogVersion()!=null && prod.getCatalogVersion().equals("Online")) {
			ProductDto pDto = new ProductDto();
			try {
				if(queryMap.get("query")!=null){
				pDto.setLanguage(queryMap.get("query").toString());
				}
				if(queryMap.get("curr")!=null){
				pDto.setCurrency(queryMap.get("curr").toString());
				}
				productDtoPopulator.populate(prod, pDto);
				productDto.add(pDto);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
		}

		Map<String,Map<String,Long>> facetMap = sr.getFacets();
		if(facetMap!=null && !sr.getFacets().isEmpty()) {
			//searchDtoPopulator.populate(sr, searchDto);
			searchDtoPopulator.populate(sr, searchDto,queryMap.get("query").toString(),queryMap.get("lang").toString(),queryMap.get("curr").toString());
		}
		searchDto.setType("productCategorySearchPageWsDTO");
		
		//List<BreadcrumbDto> bDtos = new ArrayList<>();
		//bDtos.add(new BreadcrumbDto());
		//searchDto.setBreadcrumbs(bDtos);
		searchDto.setCurrentQuery(new QueryDto());
		List<FacetsDto> fDtos = new ArrayList<>();
		//fDtos.add(new FacetsDto());

		//PaginationDto
		PaginationDto paginationDto = new PaginationDto();
        paginationDto.setCurrentPage(sr.getPage()!=null?sr.getPage().intValue():0);
        paginationDto.setTotalResults(sr.getNbHits()!=null?sr.getNbHits().intValue():0);
        paginationDto.setSort("relevance");
        paginationDto.setTotalPages(sr.getNbPages()!=null?sr.getNbPages().intValue():0);
        paginationDto.setPageSize(sr.getHitsPerPage()!=null?sr.getHitsPerPage().intValue():0);
        searchDto.setPagination(paginationDto);

		QueryDto queryDto = new QueryDto();
		QueryValueDto qvd = new QueryValueDto();
		String url;
		if(queryMap.get("query").contains("relevance")){
			qvd.setValue(queryMap.get("query").toString());
			url = "/search?q="+queryMap.get("query").toString();
		}else{
			qvd.setValue(queryMap.get("query").toString()+":relevance");
			url = "/search?q="+queryMap.get("query").toString()+"%3Arelevance";
		}
		queryDto.setUrl(url);
		queryDto.setQuery(qvd);
		// Need to set the Query Dto changes
		searchDto.setCurrentQuery(queryDto);

		searchDto.setFreeTextSearch(query.get().toString());
		
		searchDto.setProducts(productDto);
		//List<SortsDto> sDtos = new ArrayList<>();
		//sDtos.add(new SortsDto());
		sortDtoPopulator.populate(sr, searchDto, queryMap.get("lang").toString(), queryMap.get("curr").toString());
		//searchDto.setSorts(sDtos);
		String jsonInString = gson.toJson(searchDto);
		writer.write(jsonInString);
		//JsonObject productsForSpartacus = gson.fromJson(jsonInString,productDto);
	}
  }
}
