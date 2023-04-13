package com.example;

import java.io.BufferedWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.logging.Logger;
import com.algolia.search.DefaultSearchClient;
import com.algolia.search.SearchClient;
import com.algolia.search.SearchIndex;
import com.commercetools.api.client.ProjectApiRoot;
import com.commercetools.api.defaultconfig.ApiRootBuilder;
import com.commercetools.api.defaultconfig.ServiceRegion;
import com.commercetools.api.models.category.Category;
import com.commercetools.api.models.product.Product;
import com.commercetools.api.models.product.ProductPagedQueryResponse;
import com.google.cloud.functions.HttpFunction;
import com.google.cloud.functions.HttpRequest;
import com.google.cloud.functions.HttpResponse;

import io.vrap.rmf.base.client.ApiHttpResponse;
import io.vrap.rmf.base.client.oauth2.ClientCredentials;

public class Example implements HttpFunction {
	@Override
	public void service(HttpRequest request, HttpResponse response) throws Exception {
		BufferedWriter writer = response.getWriter();
		writer.write("OK");
		try {
			int offset = 0;
			while (true) {
				int endOf = offset + 100;
				int li = offset;
				ProjectApiRoot client = createApiClient();
				CompletableFuture<ApiHttpResponse<ProductPagedQueryResponse>> products = extracted(li, client);
				if (products.get().getBody().getResults().isEmpty()) {
					break;
				}
				offset = endOf;
				li = offset + 1;
				ProductsToAlgolia(products);
				if (offset > products.get().getBody().getTotal()) {
					break;
				}
			}
		} catch (Exception e) {
			return;
		}
		return;
	}

	private void ProductsToAlgolia(CompletableFuture<ApiHttpResponse<ProductPagedQueryResponse>> products)
			throws Exception, ExecutionException {
		String lvl1 = null;
		String lvl2 = null;
		String lvl1ger = null;
		String lvl2ger = null;
		ProjectApiRoot client = createApiClient();
		List<Record> algolist = new ArrayList<>();
		List<Product> prodlist = products.get().getBody().getResults();
		List<Record> germanlist = new ArrayList<>();
		System.out.println("outside for loop");
		for (Product plist : prodlist) {
			System.out.println("inside for loop");
			if(plist.getMasterData().getPublished()==true){
				System.out.println("inside if");
			Record algpl = new Record();
			Record germanrecord = new Record();
			algpl.setObjectID(plist.getId());
			germanrecord.setObjectID(plist.getId());
			if (plist.getMasterData().getCurrent().getMasterVariant().getAvailability() != null) {
				algpl.setStockStatus(
						plist.getMasterData().getCurrent().getMasterVariant().getAvailability().getIsOnStock());
			}
	
			LinkedHashMap<String, String> namemap=new LinkedHashMap<String, String>();
			if (plist.getMasterData().getCurrent().getDescription() != null) {
				algpl.setDescription(plist.getMasterData().getCurrent().getDescription().get("en"));
				try {
					germanrecord.setDescription(plist.getMasterData().getCurrent().getDescription().get("de"));
				} catch (Exception e) {
				}
			}
			// algpl.setNames(plist.getMasterData().getCurrent().getName().values().get("en"));
			namemap.put("en",plist.getMasterData().getCurrent().getName().values().get("en"));
			if (plist.getMasterData().getCurrent().getName().values().get("de") != null) {
				// germanrecord.setName(plist.getMasterData().getCurrent().getName().values().get("de"));
				namemap.put("de",plist.getMasterData().getCurrent().getName().values().get("de"));
				germanrecord.setNames(namemap);

			}
			algpl.setNames(namemap);
			algpl.setSlug(plist.getMasterData().getCurrent().getSlug().get("en"));
			if (plist.getMasterData().getCurrent().getSlug().get("de") != null) {
				germanrecord.setSlug(plist.getMasterData().getCurrent().getSlug().get("de"));

			}
			if (plist.getMasterData().getCurrent().getMasterVariant().getPrices().get(0).getValue()
					.getFractionDigits() == 2) {
				float price = (plist.getMasterData().getCurrent().getMasterVariant().getPrices().get(0).getValue()
						.getCentAmount().floatValue()) / 100;
				algpl.setPrice(price);
			}
			if (plist.getMasterData().getCurrent().getMasterVariant().getPrices().get(0).getValue()
					.getFractionDigits() == 1) {
				float price = (plist.getMasterData().getCurrent().getMasterVariant().getPrices().get(0).getValue()
						.getCentAmount().floatValue()) / 10;
				algpl.setPrice(price);
			}
			if (plist.getMasterData().getCurrent().getMasterVariant().getPrices().get(0).getValue()
					.getFractionDigits() == 3) {
				float price = (plist.getMasterData().getCurrent().getMasterVariant().getPrices().get(0).getValue()
						.getCentAmount().floatValue()) / 1000;
				algpl.setPrice(price);
			}

			try {
				if (plist.getMasterData().getCurrent().getMasterVariant().getPrices().get(1).getValue()
						.getFractionDigits() == 2) {
					float price = (plist.getMasterData().getCurrent().getMasterVariant().getPrices().get(1).getValue()
							.getCentAmount()) / 100;
					germanrecord.setPrice(price);
				}
				if (plist.getMasterData().getCurrent().getMasterVariant().getPrices().get(1).getValue()
						.getFractionDigits() == 1) {
					float price = (plist.getMasterData().getCurrent().getMasterVariant().getPrices().get(1).getValue()
							.getCentAmount()) / 10;
					germanrecord.setPrice(price);
				}
				if (plist.getMasterData().getCurrent().getMasterVariant().getPrices().get(1).getValue()
						.getFractionDigits() == 3) {
					float price = (plist.getMasterData().getCurrent().getMasterVariant().getPrices().get(1).getValue()
							.getCentAmount()) / 1000;
					germanrecord.setPrice(price);
				}

			} catch (Exception e) {

			}
			//Discounted price
			try{
				if (plist.getMasterData().getCurrent().getMasterVariant().getPrices().get(0).getDiscounted().getValue()
					.getFractionDigits() == 2) {
				float discountedPrice = (plist.getMasterData().getCurrent().getMasterVariant().getPrices().get(0).getDiscounted().getValue()
						.getCentAmount().floatValue()) / 100;
				algpl.setDiscountedPrice(discountedPrice);
			}
			if (plist.getMasterData().getCurrent().getMasterVariant().getPrices().get(0).getDiscounted().getValue()
					.getFractionDigits() == 1) {
				float discountedPrice = (plist.getMasterData().getCurrent().getMasterVariant().getPrices().get(0).getDiscounted().getValue()
						.getCentAmount().floatValue()) / 10;
				algpl.setDiscountedPrice(discountedPrice);
			}
			if (plist.getMasterData().getCurrent().getMasterVariant().getPrices().get(0).getDiscounted().getValue()
					.getFractionDigits() == 3) {
				float discountedPrice = (plist.getMasterData().getCurrent().getMasterVariant().getPrices().get(0).getDiscounted().getValue()
						.getCentAmount().floatValue()) / 1000;
				algpl.setDiscountedPrice(discountedPrice);
			}
			}
			catch(Exception e)
			{
				
			}

			//Discount attribute
		try{
			if (plist.getMasterData().getCurrent().getMasterVariant().getPrices().get(0).getDiscounted().getValue()
						.getCentAmount().floatValue() > 0.0) {
				algpl.setDiscount(true);
			}
			
		}
		catch(Exception e)
		{
			
		}
		if(algpl.getDiscount()==null)
		{
			algpl.setDiscount(false);
		}


			algpl.setSku(plist.getMasterData().getCurrent().getMasterVariant().getSku());
			algpl.setImages(plist.getMasterData().getCurrent().getMasterVariant().getImages().get(0).getUrl());
			Category categoryproductlvl = client.categories()
					.withId(plist.getMasterData().getCurrent().getCategories().get(0).getId()).get().executeBlocking()
					.getBody();
			if (categoryproductlvl.getAncestors().size() == 2) {
				Category categorylvl0 = client.categories().withId(categoryproductlvl.getAncestors().get(0).getId())
						.get().executeBlocking().getBody();
				String lvl0 = categorylvl0.getName().get("en");
				String lvl0ger = categorylvl0.getName().get("de");
				Category categorylvl1 = client.categories().withId(categoryproductlvl.getAncestors().get(1).getId())
						.get().executeBlocking().getBody();
				// lvl1 = lvl0 + " > " + categorylvl1.getName().get("en");
				// lvl2 = lvl1 + " > " + categoryproductlvl.getName().get("en");
				lvl1 = categorylvl1.getName().get("en");
				lvl2 = categoryproductlvl.getName().get("en");
				// lvl1ger = lvl0 + " > " + categorylvl1.getName().get("de");
				// lvl2ger = lvl1 + " > " + categoryproductlvl.getName().get("de");
				lvl1ger = categorylvl1.getName().get("de");
				lvl2ger = categoryproductlvl.getName().get("de");
				// Map<String, String> mp = new LinkedHashMap<String, String>();
				// Map<String, String> mpger = new LinkedHashMap<String, String>();
				ArrayList<String> aleng=new ArrayList<String>();
				ArrayList<String> alger=new ArrayList<String>();
				// mp.put("lvl0", lvl0);
				// mp.put("lvl1", lvl1);
				// mp.put("lvl2", lvl2);
				// mpger.put("lvl0", lvl0ger);
				// mpger.put("lvl1", lvl1ger);
				// mpger.put("lvl2", lvl2ger);
				aleng.add(lvl0);
				aleng.add(lvl1);
				aleng.add(lvl2);
				alger.add(lvl0ger);
				alger.add(lvl1ger);
				alger.add(lvl2ger);
				algpl.setCategories(aleng);
				germanrecord.setCategories(alger);
				algolist.add(algpl);
				germanlist.add(germanrecord);
			}
			if (categoryproductlvl.getAncestors().size() == 1) {
				try {
					Category categorylvl0 = client.categories().withId(categoryproductlvl.getAncestors().get(0).getId())
							.get().executeBlocking().getBody();

					String lvl0 = categorylvl0.getName().get("en");
					String lvl0ger = categorylvl0.getName().get("de");
					// lvl1ger = lvl0 + " > " + categoryproductlvl.getName().get("de");
					// lvl1 = lvl0 + " > " + categoryproductlvl.getName().get("en");
					lvl1ger = categoryproductlvl.getName().get("de");
					lvl1 = categoryproductlvl.getName().get("en");
					// Map<String, String> mp = new LinkedHashMap<String, String>();
					// Map<String, String> mpger = new LinkedHashMap<String, String>();
					ArrayList<String> aleng=new ArrayList<String>();
				ArrayList<String> alger=new ArrayList<String>();
					// mp.put("lvl0", lvl0);
					// mp.put("lvl1", lvl1);
					// mpger.put("lvl0", lvl0ger);
					// mpger.put("lvl1", lvl1ger);
					// algpl.setCategories(mp);
					// germanrecord.setCategories(mpger);
					// algolist.add(algpl);
					// germanlist.add(germanrecord);
					aleng.add(lvl0);
				alger.add(lvl0ger);
				aleng.add(lvl1);
				alger.add(lvl1ger);
				algpl.setCategories(aleng);
				germanrecord.setCategories(alger);
				algolist.add(algpl);
				germanlist.add(germanrecord);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			}

		}
		// Connect and authenticate with your Algolia app
		SearchClient searchclient = DefaultSearchClient.create("VHXT4I8OD8", "09d92dfd1e9669b1d8e5ee86653e7a3f");
		SearchIndex<Object> index = searchclient.initIndex("Products_cmtools_store1", Object.class);
		SearchIndex<Object> germanindex = searchclient.initIndex("Products_cmtools_ger_store1", Object.class);
		boolean autoGenerateObjectIDIfNotExist = true;
		index.saveObjects(Arrays.asList(algolist.toArray()), autoGenerateObjectIDIfNotExist).waitTask();
		germanindex.saveObjects(Arrays.asList(germanlist.toArray()), autoGenerateObjectIDIfNotExist).waitTask();
	}

	public CompletableFuture<ApiHttpResponse<ProductPagedQueryResponse>> extracted(int li, ProjectApiRoot client) {
		CompletableFuture<ApiHttpResponse<ProductPagedQueryResponse>> products = client.products().get().addLimit(100)
				.addOffset(li).addSort("id").withWithTotal(true).execute();
		return products;
	}

	public static ProjectApiRoot createApiClient() {
		final ProjectApiRoot apiRoot = ApiRootBuilder.of()
				.defaultClient(
						ClientCredentials.of().withClientId("CzyOtBWiKNdRy5hlvYRvUi8a")
								.withClientSecret("pRVUmacYXS95m7x_Y7o3yzUT8Z32zlC9").build(),
						ServiceRegion.GCP_EUROPE_WEST1)
				.build("composable-asset");
		return apiRoot;

	}

	public class Categories {
		private String lvl0;
		private String lvl1;
		private String lvl2;

		public String getLvl0() {
			return lvl0;
		}

		public void setLvl0(String lvl0) {
			this.lvl0 = lvl0;
		}

		public String getLvl1() {
			return lvl1;
		}

		public void setLvl1(String lvl1) {
			this.lvl1 = lvl1;
		}

		public String getLvl2() {
			return lvl2;
		}

		public void setLvl2(String lvl2) {
			this.lvl2 = lvl2;
		}

	}

	public static class Record implements Serializable {

		private LinkedHashMap<String, String> names;
		private String description;
		private float price;
		private String images;
		private ArrayList<String> categories;
		private Boolean stockStatus;
		private float averageRating;
		private String sku;
		private String objectID;
		private String slug;
		private float discountedPrice;
		private boolean discount;

		// constructor
		public Record(LinkedHashMap<String, String> names, String description, float price, String images, Boolean stockStatus,
				float averageRating, String sku, String objectID, float discountedPrice, Boolean discount) {
			super();
			this.names = names;
			this.description = description;
			this.price = price;
			this.images = images;
			this.stockStatus = stockStatus;
			this.averageRating = averageRating;
			this.sku = sku;
			this.objectID = objectID;
			this.discountedPrice=discountedPrice;
			this.discount=discount;
		}

		public ArrayList<String> getCategories() {
			return categories;
		}

		public void setCategories(ArrayList<String> categories) {
			this.categories = categories;
		}

		public Record() {
			// TODO Auto-generated constructor stub
		}

		@Override
		public String toString() {
			return "Record [name=" + names + ", description=" + description + ", price=" + price + ", images=" + images
					+ ", categories=" + categories + ", stockStatus=" + stockStatus + ", averageRating=" + averageRating
					+ ", sku=" + sku + ", objectID=" + objectID + "]";
		}

		// getters and setters
		public LinkedHashMap<String, String> getNames() {
			return names;
		}

		public void setNames(LinkedHashMap<String, String> names) {
			this.names = names;
		}

		public String getObjectID() {
			return objectID;
		}

		public void setObjectID(String objectID) {
			this.objectID = objectID;
		}

		public float getPrice() {
			return price;
		}

		public void setPrice(float price) {
			this.price = price;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public String getImages() {
			return images;
		}

		public void setImages(String images) {
			this.images = images;
		}

		public float getAverageRating() {
			return averageRating;
		}

		public void setAverageRating(float averageRating) {
			this.averageRating = averageRating;
		}

		public String getSku() {
			return sku;
		}

		public void setSku(String sku) {
			this.sku = sku;
		}

		public Boolean getStockStatus() {
			return stockStatus;
		}

		public void setStockStatus(Boolean stockStatus) {
			this.stockStatus = stockStatus;
		}

			public String getSlug() {
			return slug;
		}

		public void setSlug(String slug) {
			this.slug = slug;
		}

			public float getDiscountedPrice() {
			return discountedPrice;
		}

		public void setDiscountedPrice(float discountedPrice) {
			this.discountedPrice = discountedPrice;
		}

		
		public Boolean getDiscount() {
			return discount;
		}

		public void setDiscount(Boolean discount) {
			this.discount = discount;
		}

	}

}