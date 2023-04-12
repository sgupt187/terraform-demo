package com.example;

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

public class Example implements HttpFunction {

	private static final Logger logger = Logger.getLogger(Example.class.getName());
	private static final double INDEX_RANGE = 200.00;
	private static final String GBP_SYMBOL = "GBP";
	private static final String EUR_SYMBOL = "EUR";
	private static final String USD_SYMBOL = "USD";
	private static HttpClient client = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(10)).build();
	String apikey = "7582d92ead72037cec3e45bc5f8e5e09";
	String applicationId = "N07QN5D3KU";
	private static final Gson gson = new Gson();

	@Override
	public void service(HttpRequest request, HttpResponse response) throws Exception {
		BufferedWriter writer = response.getWriter();
		// String payloadRequest = getBody(request);
		JsonObject requestJson = gson.fromJson(request.getReader(), JsonObject.class);
		logger.info(requestJson.toString());
		JsonObject catalogVersion = requestJson.get("catalogVersion").getAsJsonObject();
		String version = catalogVersion.get("version").getAsString();
		JsonObject catalog = catalogVersion.get("catalog").getAsJsonObject();
		String catloag = catalog.get("id").getAsString();
		String productCode = requestJson.get("code").getAsString();
		if (requestJson.get("name") != null) {
			String productName = requestJson.get("name").getAsString();
		}
		String manufacturerName = "";
		if (requestJson.get("manufacturerName") != null) {
			manufacturerName = requestJson.get("manufacturerName").getAsString();
		}
		String summary = "";
		if (requestJson.get("summary") != null) {
			summary = requestJson.get("summary").getAsString();
		}
		StringBuffer productURL = new StringBuffer();
		String uid = catloag + "-" + version + "-" + productCode;
		String indexUrl = "https://N07QN5D3KU.algolia.net/1/indexes/mbdemo/" + uid;

		String stockURL = "https://34.149.133.90.nip.io/stocks?products=" + productCode + "&baseStore=highstreet";
		var stockResponseObj = java.net.http.HttpRequest.newBuilder().uri(URI.create(stockURL))
				.header("Content-Type", "application/json").GET().build();
		java.net.http.HttpResponse<String> stockResponse = client.send(stockResponseObj, BodyHandlers.ofString());
		JsonArray stockJsonArray = gson.fromJson(stockResponse.body(), JsonArray.class);
		String stockValue = "0";
		boolean stockValueBoolean = false;
		if (stockJsonArray != null && stockJsonArray.size() > 0) {
			JsonObject stock = stockJsonArray.get(0).getAsJsonObject();
			stockValue = "available".equals(stock.get("status").getAsString()) ? "inStock" : "outOfStock";
			stockValueBoolean = "available".equals(stock.get("status").getAsString()) ? true : false;
		}
		String usdPriceURL = "https://34.149.133.90.nip.io/price?productCode=" + productCode
				+ "&site=highstreet-spa&currency=USD";
		var priceResponseObj = java.net.http.HttpRequest.newBuilder().uri(URI.create(usdPriceURL))
				.header("Content-Type", "application/json").GET().build();
		java.net.http.HttpResponse<String> priceResponse = client.send(priceResponseObj, BodyHandlers.ofString());
		JsonArray priceResponseArray = gson.fromJson(priceResponse.body(), JsonArray.class);
		String priceValue = "0.0";
		String priceRangeValue = getPriceRange(0.0, USD_SYMBOL);
		if (priceResponseArray != null && priceResponseArray.size() > 0) {
			JsonObject priceJson = priceResponseArray.get(0).getAsJsonObject();
			priceValue = priceJson.get("price").getAsString();
			priceRangeValue = getPriceRange(priceJson.get("price").getAsDouble(), USD_SYMBOL);
		}

		String gbpPriceURL = "https://34.149.133.90.nip.io/price?productCode=" + productCode
				+ "&site=highstreet-spa&currency=USD";
		var gbpPriceResponseObj = java.net.http.HttpRequest.newBuilder().uri(URI.create(gbpPriceURL))
				.header("Content-Type", "application/json").GET().build();
		java.net.http.HttpResponse<String> gbpPriceResponse = client.send(gbpPriceResponseObj, BodyHandlers.ofString());
		JsonArray gbppriceResponseArray = gson.fromJson(gbpPriceResponse.body(), JsonArray.class);
		String gbpPriceValue = "0.0";
		String gbpPriceRangeValue = getPriceRange(0.0, GBP_SYMBOL);
		if (gbppriceResponseArray != null && gbppriceResponseArray.size() > 0) {
			JsonObject gbppriceJson = gbppriceResponseArray.get(0).getAsJsonObject();
			gbpPriceValue = gbppriceJson.get("price").getAsString();
			gbpPriceRangeValue = getPriceRange(gbppriceJson.get("price").getAsDouble(), GBP_SYMBOL);
		}

		// var ukpriceResponse =
		// java.net.http.HttpRequest.newBuilder().uri(URI.create(gbpPriceURL)).header("Content-Type",
		// "application/json").GET().build();

		JsonArray catCodeList = new JsonArray();
		JsonArray catNameList = new JsonArray();
		JsonArray catURLMappingList = new JsonArray();

		if (requestJson.get("supercategories") != null) {
			JsonArray superCat = requestJson.get("supercategories").getAsJsonArray();
			for (JsonElement element : superCat) {
				JsonObject obj = element.getAsJsonObject();
				catCodeList.add(obj.get("code"));
				catNameList.add(obj.get("name"));
				catURLMappingList.add("/" + obj.get("code").getAsString() + "/");
				JsonArray allSupercategories = obj.get("allSupercategories").getAsJsonArray();
				for (JsonElement allsuper : allSupercategories) {
					JsonObject allsupobj = allsuper.getAsJsonObject();
					catCodeList.add(allsupobj.get("code"));
					catNameList.add(allsupobj.get("name"));
					catURLMappingList
							.add("/" + allsupobj.get("code").getAsString() + "/" + obj.get("code").getAsString() + "/");
					productURL.append("/").append(allsupobj.get("name").getAsString()).append("/")
							.append(obj.get("name").getAsString()).append("/");
				}
			}
		}

		// Hybris Index JSON creation
		JsonObject indexJson = new JsonObject();
		// indexJson.addProperty("indexOperationId", uid);
		indexJson.addProperty("objectID", uid);
		indexJson.addProperty("id", uid);
		indexJson.addProperty("catalogId", catloag);
		indexJson.addProperty("catalogVersion", version);
		indexJson.add("category_string_mv", catCodeList);
		indexJson.addProperty("pickupAvailableFlag_boolean", false); // from basestore config

		// config
		indexJson.addProperty("priceValue_usd_double", priceValue);
		indexJson.addProperty("price_usd_string", priceRangeValue); // from basestore
		indexJson.addProperty("priceValue_gbp_double", gbpPriceValue);
		indexJson.addProperty("price_gbp_string", gbpPriceRangeValue); // from basestore
		indexJson.add("allCategories_string_mv", catCodeList);
		indexJson.addProperty("configurable_boolean", false);// from basestore config
		indexJson.addProperty("inStockFlag_boolean", stockValueBoolean);// from basestore config
		indexJson.addProperty("configuratorType_string", "");
		indexJson.addProperty("code_string", productCode);

		indexJson.addProperty("multidimensional_boolean", false);// from basestore config
		indexJson.addProperty("manufacturerName_text", manufacturerName);
		indexJson.addProperty("itemtype_string", "Product");
		indexJson.add("categoryPath_string_mv", catURLMappingList);
		indexJson.addProperty("stockLevelStatus_string", stockValue);
		// indexJson.addProperty("_version_", "inStock");

		// Localized attributes
		JsonArray mainLocalValuesArray = requestJson.get("localizedAttributes").getAsJsonArray();
		for (JsonElement localElement : mainLocalValuesArray) {
			JsonObject obj = localElement.getAsJsonObject();
			String langCode = obj.get("language").getAsString();
			indexJson.addProperty("name_text_" + langCode, obj.get("name").getAsString());
			indexJson.addProperty("summary_text_" + langCode, obj.get("summary").getAsString());
			indexJson.addProperty("name_sortable_" + langCode + "_sortabletext", obj.get("name").getAsString());
			String localProductUrl = productURL.toString() + obj.get("name").getAsString() + "/p/" + productCode;
			indexJson.addProperty("url_" + langCode + "_string", localProductUrl.replaceAll(" ", "-"));
		}
		indexJson.add("categoryName_text_en_mv", catNameList);
		indexJson.add("categoryName_text_de_mv", catNameList); // Need to be derive with local lang

		if (requestJson.get("galleryImages") != null) {
			JsonArray galleryImagesArray = requestJson.get("galleryImages").getAsJsonArray();
			JsonObject galleryImages = galleryImagesArray.get(0).getAsJsonObject();
			if (galleryImages.get("medias") != null) {
				JsonArray mediasArray = galleryImages.get("medias").getAsJsonArray();
				for (JsonElement mediaElement : mediasArray) {
					JsonObject media = mediaElement.getAsJsonObject();
					JsonObject formatObj = media.get("mediaFormat").getAsJsonObject();
					String indexImg = "img-" + formatObj.get("name").getAsString() + "_string";
					indexJson.add(indexImg, media.get("URL"));
				}
			}
		}

		/*
		 * // ADD API call JSON creation logger.info(indexJson.toString()); JsonObject
		 * lineItemPayload = new JsonObject(); lineItemPayload.addProperty("action",
		 * "addObject"); lineItemPayload.add("body", indexJson);
		 * logger.info(lineItemPayload.toString());
		 * 
		 * // Append all API calls JSON array JsonArray array = new JsonArray();
		 * array.add(lineItemPayload);
		 * 
		 * // Create JSON object for request payload JsonObject indexPayload = new
		 * JsonObject(); indexPayload.add("requests", array);
		 * logger.info(indexPayload.toString());
		 */

		var getPostRequest = java.net.http.HttpRequest.newBuilder().uri(URI.create(indexUrl))
				.header("X-Algolia-API-Key", apikey).header("X-Algolia-Application-Id", applicationId)
				.header("Content-Type", "application/json").PUT(BodyPublishers.ofString(indexJson.toString())).build();
		var getResponse = client.send(getPostRequest, BodyHandlers.ofString());
		writer.write("Hello world!" + getResponse);
	}

	private String getPriceRange(Double price, String sybol) {
		if (price != null && sybol != null) {
			double startValue = 0.0;
			double endValue = 0.0;
			int i = 1;
			while (i != 0) {
				if (price <= i * INDEX_RANGE - 0.01) {
					startValue = (i - 1) * INDEX_RANGE;
					endValue = i * INDEX_RANGE - 0.01;
					break;
				}
				i++;
			}
			return sybol + String.valueOf(startValue) + "-" + sybol + String.valueOf(endValue);
		}
		return null;

	}
}