package com.mb;

import com.google.cloud.functions.HttpFunction;
import com.google.cloud.functions.HttpRequest;
import com.google.cloud.functions.HttpResponse;
import com.google.gson.Gson;
import com.mb.amplience.AmplienceResponse;
import com.mb.amplience.Banner;
import com.mb.amplience.Component;
import com.mb.amplience.Content;
import com.mb.amplience.ContentSlot;
import com.mb.amplience.category.Category;
import com.mb.amplience.category.Response;
import com.mb.amplience.cmslink.CmsLink;
import com.mb.ampliencebanner.AmplienceBannerResponse;
import com.mb.spartacus.*;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest.BodyPublishers;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AmplienceAdapter implements HttpFunction {

	private static final String AMP_CAT_LINK = "AmpCatLink";
	private static final String AMP_FOOT_LINK = "AmpFootLink";
	private static final String AMP_NAV_LINK = "AmpNavLink";
	private static final String RESTRICT_COMPONENTS = "AnonymousConsent";
	private String globalLocal = null;
	private boolean isLoggedInreuest = false;

	public String getGlobalLocal() {
		return globalLocal;
	}

	public void setGlobalLocal(String globalLocal) {
		this.globalLocal = globalLocal;
	}

	/*
	 * public static void main(String[] args) { AmplienceAdapter main = new
	 * AmplienceAdapter(); try { main.service(null, null); } catch (Exception e) {
	 * e.printStackTrace(); } }
	 */
//	Function Created by Rishabh  to Fetch the LocaleUrl
	private String getLocaleUrl(String url, Map<String, List<String>> params) {

		String locale = params != null && params.containsKey("lang") ? params.get("lang").get(0) : "";
		if (locale.equals("de")) {
			locale = "de-DE";
		} else if (locale.equals("en")) {
			locale = "en-GB";
		}
		setGlobalLocal(locale);

		System.out.println("locale in request:" + locale);

		if (!locale.isEmpty()) {
			url = url + "&locale=" + locale;
		}

		return url;

	}

	@Override
	public void service(HttpRequest httpRequest, HttpResponse httpResponse) throws Exception {

		/*
		 * String test =
		 * "466ed737-ccea-4420-886a-a657d11be0dcAmpFootLink,84bde416-225e-4b59-a315-848d3e47180bAmpFootLink,9b8b1d03-52d9-4002-8cd5-bb781c71e29eAmpFootLink,2fef85f7-4ad3-4fc6-9365-2dc0690ba849AmpFootLink,52144255-5a6b-43d8-a46e-4ed67cbca39eAmpFootLink,c1a4d74e-34a0-4fba-96d2-c7a8c2d92fdfAmpFootLink,8a1e25a5-4ffb-4d75-a480-3f02ada9d2e9AmpFootLink,b922174d-55a9-40c5-b3bb-17ce86915b2cAmpFootLink,f40225a5-fee9-425d-bc1f-40e423b6204aAmpFootLink,0faf1d0e-2c27-405d-9230-5d09250123b5AmpFootLink,e84faf94-2a9f-4530-9008-9298f70a3c44AmpFootLink";
		 * 
		 * test = test.replaceAll(AMP_CAT_LINK, ""); System.out.print("CMS Links" +
		 * test); String[] catids1 = test.split(","); String response5 =
		 * getCmsLinkComponents(catids1, AMP_CAT_LINK);
		 */
		Map<String, List<String>> headers = httpRequest.getHeaders();
		List<String> authValues = headers.get("Authorization");
		if (authValues != null && authValues.size() > 0) {
			setLoggedInreuest(true);
		}
		System.out.println("Test flag :--> " + isLoggedInreuest);
		Map<String, List<String>> params = httpRequest.getQueryParameters();
		String response = null;
		if (params != null && params.containsKey("componentIds")) {
			String param = null;
			List<String> idsList = params.get("componentIds");
			for (String s : idsList) {
				param = s;
			}
			if (param != null && param.indexOf("mbBanner1") != -1) {
				System.out.print("Banner : " + param);
				response = getBannerComponents(httpRequest, httpResponse);
			} else if (param != null && param.indexOf(AMP_CAT_LINK) != -1) {
				param = param.replaceAll(AMP_CAT_LINK, "");
				System.out.print("CMS Links" + param);
				String[] catids = param.split(",");
				response = getCmsLinkComponents(catids, AMP_CAT_LINK, httpRequest);
			} else if (param != null && param.indexOf(AMP_FOOT_LINK) != -1) {
				param = param.replaceAll(AMP_FOOT_LINK, "");
				System.out.print("CMS Foot Links" + param);
				String[] catids = param.split(",");
				response = getCmsLinkComponents(catids, AMP_FOOT_LINK, httpRequest);
			} else if (param != null && param.indexOf(AMP_NAV_LINK) != -1) {
				param = param.replaceAll(AMP_NAV_LINK, "");
				System.out.print("CMS User Nav Links" + param);
				String[] catids = param.split(",");
				response = getCmsLinkComponents(catids, AMP_NAV_LINK, httpRequest);
			}
		} else {
			response = getHomePageDetails(httpRequest, httpResponse);
		}
		httpResponse.appendHeader("Content-Type", "application/json");
		httpResponse.getWriter().write(response);

	}

//changes Made by Rishabh to add httpRequest element in the argument to fetch the locale Url

	private String getCmsLinkComponents(String[] catids, String linkid, HttpRequest httpRequest) throws Exception {
		Gson gson = new Gson();

		// new params added by Rishabh to get Request params

		Map<String, List<String>> params = httpRequest.getQueryParameters();

		List<com.mb.spartacus.Component> cmsComponentsList = new ArrayList<>();
		if (catids != null) {
			for (String catLink : catids) {
				String url = "https://capgsandbox.cdn.content.amplience.net/content/id/" + catLink
						+ "?depth=all&format=inlined";
				// changes Made by Rishabh to Get the LocaleUrl
				String localeUrl = getLocaleUrl(url, params);

				java.net.http.HttpRequest request = java.net.http.HttpRequest.newBuilder().uri(URI.create(localeUrl))
						.timeout(Duration.ofMinutes(2)).header("Content-Type", "application/json").build();
				HttpClient client = HttpClient.newBuilder().version(HttpClient.Version.HTTP_1_1)
						.followRedirects(HttpClient.Redirect.NORMAL).connectTimeout(Duration.ofMinutes(2)).build();
				java.net.http.HttpResponse<String> response1 = client.send(request,
						java.net.http.HttpResponse.BodyHandlers.ofString());
				CmsLink response = gson.fromJson(response1.body(), CmsLink.class);
				getCMSLinks(catLink, linkid, cmsComponentsList, response);// Populate page details
			}
		}
		Components sparCMSReponseResponse = new Components();
		sparCMSReponseResponse.setComponent(cmsComponentsList);
		String response = gson.toJson(sparCMSReponseResponse);
		System.out.println(sparCMSReponseResponse);
		return response;

	}

	private String getBannerComponents(HttpRequest httpRequest, HttpResponse httpResponse) throws Exception {
		Map<String, List<String>> params = httpRequest.getQueryParameters();
		String url = "https://capgsandbox.cdn.content.amplience.net/content/id/05e315f7-3fcd-492a-b619-4e65b216efd1?depth=all&format=inlined";

//		Changes made By Rishabh created a localeurlfunction to fetch the localeurl 
		String localeUrl = getLocaleUrl(url, params);

		Gson gson = new Gson();
		java.net.http.HttpRequest request = java.net.http.HttpRequest.newBuilder().uri(URI.create(localeUrl))
				.timeout(Duration.ofMinutes(2)).header("Content-Type", "application/json").build();
		HttpClient client = HttpClient.newBuilder().version(HttpClient.Version.HTTP_1_1)
				.followRedirects(HttpClient.Redirect.NORMAL).connectTimeout(Duration.ofMinutes(2)).build();
		java.net.http.HttpResponse<String> response1 = client.send(request,
				java.net.http.HttpResponse.BodyHandlers.ofString());
		AmplienceBannerResponse amplienceResponse = gson.fromJson(response1.body(), AmplienceBannerResponse.class);
		Components bannerResponse = new Components();
		getBannerComponnts(bannerResponse, amplienceResponse);// Populate page details
		String response = gson.toJson(bannerResponse);
		System.out.println(response);
		return response;

	}

	private String getHomePageDetails(HttpRequest httpRequest, HttpResponse httpResponse) throws Exception {
		Map<String, List<String>> params = httpRequest.getQueryParameters();
		String url = "https://capgsandbox.cdn.content.amplience.net/content/id/2c7a985e-0bcf-4cee-abf0-c0018e4ee4ef?depth=all&format=inlined";

//		Changes made By Rishabh created a localeurlfunction to fetch the localeurl 
		String localeUrl = getLocaleUrl(url, params);

		Gson gson = new Gson();
		java.net.http.HttpRequest request = java.net.http.HttpRequest.newBuilder().uri(URI.create(localeUrl))
				.timeout(Duration.ofMinutes(2)).header("Content-Type", "application/json").build();
		HttpClient client = HttpClient.newBuilder().version(HttpClient.Version.HTTP_1_1)
				.followRedirects(HttpClient.Redirect.NORMAL).connectTimeout(Duration.ofMinutes(2)).build();
		java.net.http.HttpResponse<String> response1 = client.send(request,
				java.net.http.HttpResponse.BodyHandlers.ofString());
		AmplienceResponse amplienceResponse = gson.fromJson(response1.body(), AmplienceResponse.class);
		Content ampContent = amplienceResponse.getContent();
		SpartacusResponse spartacusResponse = new SpartacusResponse();
		getPageDetails(spartacusResponse, ampContent);// Populate page details
		String response = gson.toJson(spartacusResponse);
		System.out.println(response);
		return response;
	}

	private void getBannerComponnts(Components components, AmplienceBannerResponse amplienceResponse) {
		List<com.mb.spartacus.Component> bannerComponents = new ArrayList<>();
		List<com.mb.ampliencebanner.Banner> bannerList = amplienceResponse.getContent().getBanners();
		for (com.mb.ampliencebanner.Banner banner : bannerList) {
			com.mb.spartacus.Component spaBannerComp = new com.mb.spartacus.Component();
			spaBannerComp.setUid(banner.getUid());
			spaBannerComp.setUuid(banner.getMeta().getDeliveryId());
			spaBannerComp.setTypeCode("BannerComponent");
			spaBannerComp.setModifiedtime(null);
			spaBannerComp.setRotatingComponent("mercedesbenzHomepageCarouselComponent");
			spaBannerComp.setName(banner.getName());
			spaBannerComp.setContainer(banner.getContainer().toString());
			spaBannerComp.setExternal(banner.getExternal().toString());
			spaBannerComp.setUrlLink(banner.getUrlLink());
			spaBannerComp.setSynchronizationBlocked(banner.getSynchronizationBlocked());
			spaBannerComp.getMedia();
			com.mb.ampliencebanner.Media ampMedia = banner.getMedia();
			if (ampMedia != null) {
				Media media = new Media();
				media.setCode(ampMedia.getMedia().getId());
				media.setMime("image/png");
				media.setAltText(ampMedia.getAltText());
				media.setUrl("https://" + ampMedia.getMedia().getDefaultHost() + "/i/"
						+ ampMedia.getMedia().getEndpoint() + "/" + ampMedia.getMedia().getName());
				spaBannerComp.setMedia(media);
			}
			bannerComponents.add(spaBannerComp);
		}
		components.setComponent(bannerComponents);
	}

	private void getCMSLinks(String catLink, String linkid, List<com.mb.spartacus.Component> cmsComponentsList,
			CmsLink response) {
		com.mb.amplience.cmslink.Entry cmslink = response.getContent().getEntries().get(0);
		com.mb.spartacus.Component cmslinkcomponent = new com.mb.spartacus.Component();
		cmslinkcomponent.setUid(catLink + linkid);
		cmslinkcomponent.setUuid(cmslink.getMeta().getDeliveryId());
		cmslinkcomponent.setTypeCode(cmslink.getTypeCode());
		cmslinkcomponent.setModifiedtime(null);
		cmslinkcomponent.setName(cmslink.getName());
		cmslinkcomponent.setContainer(cmslink.getContainer().toString());
		cmslinkcomponent.setExternal(cmslink.getExternal().toString());
		cmslinkcomponent.setSynchronizationBlocked(cmslink.getSynchronizationBlocked().toString());
		cmslinkcomponent.setLinkName(cmslink.getLinkName());
		cmslinkcomponent.setUrl(cmslink.getUrl());
		cmslinkcomponent.setTarget(cmslink.getTarget().toString());
		cmsComponentsList.add(cmslinkcomponent);
	}

	private void getPageDetails(SpartacusResponse spartacusResponse, Content ampContent) {
		spartacusResponse.setUid(ampContent.getUid());
		spartacusResponse.setUuid(ampContent.getMeta().getDeliveryId());
		spartacusResponse.setTitle(ampContent.getTitle());
		spartacusResponse.setTemplate(ampContent.getTemplate());
		spartacusResponse.setTypeCode(ampContent.getTypeCode());
		spartacusResponse.setName(ampContent.getName());
		spartacusResponse.setRobotTag("INDEX_FOLLOW");
		getContentSlots(spartacusResponse, ampContent);
		spartacusResponse.setLabel(ampContent.getLabel());

	}

	private void getContentSlots(SpartacusResponse spartacusResponse, Content ampContent) {
		List<com.mb.spartacus.ContentSlot> contentSlotList = new ArrayList<>();
		List<ContentSlot> ampContentSlots = ampContent.getContentSlots();
		for (ContentSlot ampslot : ampContentSlots) {
			com.mb.spartacus.ContentSlot sprContentSlot = new com.mb.spartacus.ContentSlot();
			sprContentSlot.setSlotId(ampslot.getSlotId());
			sprContentSlot.setSlotUuid(ampslot.getMeta().getDeliveryId());
			sprContentSlot.setPosition(ampslot.getPosition());
			sprContentSlot.setName(ampslot.getName());
			sprContentSlot.setSlotShared(ampslot.getSlotShared());
			getComponentsForSlots(sprContentSlot, ampslot);
			contentSlotList.add(sprContentSlot);
		}
		ContentSlots sparContentSlots = new ContentSlots();
		sparContentSlots.setContentSlot(contentSlotList);
		spartacusResponse.setContentSlots(sparContentSlots);
	}

	private void getComponentsForSlots(com.mb.spartacus.ContentSlot sprContentSlot, ContentSlot ampslot) {
		List<com.mb.spartacus.Component> spaComponenetList = new ArrayList<>();
		List<Component> ampComponenets = ampslot.getComponents();
		for (Component ampComponent : ampComponenets) {
			if (ampComponent != null && (!isLoggedInreuest()
					|| (isLoggedInreuest() && ampComponent.getUid().indexOf(RESTRICT_COMPONENTS) == -1))) {
				com.mb.spartacus.Component sprComponent = new com.mb.spartacus.Component();
				if ("RotatingImagesComponent".equalsIgnoreCase(ampComponent.getTypeCode())) {
					buildRotatingImagesComponent(ampComponent, sprComponent);
				} else if ("ProductCarouselComponent".equalsIgnoreCase(ampComponent.getTypeCode())) {
					buildProductCarouselComponent(ampComponent, sprComponent);
				} else if ("SimpleBannerComponent".equalsIgnoreCase(ampComponent.getTypeCode())) {
					buildSimpleBannerComponent(ampComponent, sprComponent);
				} else if ("CMSParagraphComponent".equalsIgnoreCase(ampComponent.getTypeCode())) {
					buildCMSParagraphComponent(ampComponent, sprComponent);
				} else if ("CMSFlexComponent".equalsIgnoreCase(ampComponent.getTypeCode())) {
					buildCMSFlexComponent(ampComponent, sprComponent);
				} else if ("MiniCartComponent".equalsIgnoreCase(ampComponent.getTypeCode())) {
					buildMiniCartComponent(ampComponent, sprComponent);
				} else if ("CMSSiteContextComponent".equalsIgnoreCase(ampComponent.getTypeCode())) {
					buildCMSSiteContextComponent(ampComponent, sprComponent);
				} else if ("SiteLogoComponent".equalsIgnoreCase(ampComponent.getTypeCode())) {
					buildSiteLogoComponent(ampComponent, sprComponent);
				} else if ("CMSLinkComponent".equalsIgnoreCase(ampComponent.getTypeCode())) {
					buildCMSLinkComponent(ampComponent, sprComponent);
				} else if ("SearchBoxComponent".equalsIgnoreCase(ampComponent.getTypeCode())) {
					buildSearchBoxComponent(ampComponent, sprComponent);
				} else if ("CategoryNavigationComponent".equalsIgnoreCase(ampComponent.getTypeCode())) {
					buildCategoryNavigationComponent(ampComponent, sprComponent);
				} else if ("NavigationComponent".equalsIgnoreCase(ampComponent.getTypeCode())) {
					buildNavigationComponent(ampComponent, sprComponent);
				} else if ("FooterNavigationComponent".equalsIgnoreCase(ampComponent.getTypeCode())) {
					buildFooterNavigationComponent(ampComponent, sprComponent);
				}
				spaComponenetList.add(sprComponent);
			}
		}
		Components spacomponents = new Components();
		spacomponents.setComponent(spaComponenetList);
		sprContentSlot.setComponents(spacomponents);
	}

	private void populateBasicCompoent(Component ampComponent, com.mb.spartacus.Component sprComponent) {
		sprComponent.setUid(ampComponent.getUid());
		sprComponent.setUuid(ampComponent.getMeta().getDeliveryId());
		sprComponent.setTypeCode(ampComponent.getTypeCode());
		sprComponent.setModifiedtime(null);
		sprComponent.setName(ampComponent.getName());
		if (ampComponent.getContainer() != null) {
			sprComponent.setContainer(ampComponent.getContainer().toString());
		}
		if (ampComponent.getSynchronizationBlocked() != null) {
			sprComponent.setSynchronizationBlocked(ampComponent.getSynchronizationBlocked());
		}
	}

	private void buildRotatingImagesComponent(Component ampComponent, com.mb.spartacus.Component sprComponent) {
		populateBasicCompoent(ampComponent, sprComponent);
		sprComponent.setEffect(ampComponent.getEffect());
		StringBuffer bannerStr = new StringBuffer();
		if (ampComponent.getBanners() != null) {
			List<Banner> banners = ampComponent.getBanners();
			for (Banner banner : banners) {
				bannerStr.append(banner.getUid()).append(" ");
			}
		}
		sprComponent.setBanners(bannerStr.toString().trim());
	}

	private void buildProductCarouselComponent(Component ampComponent, com.mb.spartacus.Component sprComponent) {
		populateBasicCompoent(ampComponent, sprComponent);
		sprComponent.setPopup(ampComponent.getPopup().toString());
		// sprComponent.setScroll(ampComponent.getScroll());
		sprComponent.setScroll("ALLVISIBLE");
		sprComponent.setProductCodes(ampComponent.getProductCodes());
	}

	private void buildSimpleBannerComponent(Component ampComponent, com.mb.spartacus.Component sprComponent) {
		populateBasicCompoent(ampComponent, sprComponent);
		sprComponent.setExternal(ampComponent.getExternal().toString());
		com.mb.amplience.Media ampMedia = ampComponent.getMedia();
		if (ampMedia != null) {
			Media media = new Media();
			media.setCode(ampMedia.getMedia().getId());
			media.setMime("image/png");
			media.setAltText(ampMedia.getAltText());
			media.setUrl("https://" + ampMedia.getMedia().getDefaultHost() + "/i/" + ampMedia.getMedia().getEndpoint()
					+ "/" + ampMedia.getMedia().getName());
			sprComponent.setMedia(media);
		}
	}

	private void buildCMSFlexComponent(Component ampComponent, com.mb.spartacus.Component sprComponent) {
		populateBasicCompoent(ampComponent, sprComponent);
		sprComponent.setFlexType(ampComponent.getUid());
	}

	private void buildCMSParagraphComponent(Component ampComponent, com.mb.spartacus.Component sprComponent) {
		populateBasicCompoent(ampComponent, sprComponent);
		sprComponent.setContent(ampComponent.getContent());
	}

	private void buildMiniCartComponent(Component ampComponent, com.mb.spartacus.Component sprComponent) {
		populateBasicCompoent(ampComponent, sprComponent);
		sprComponent.setShownProductCount(ampComponent.getShownProductCount().toString());
		sprComponent.setTotalDisplay(ampComponent.getTotalDisplay());
		LightboxBannerComponent light = new LightboxBannerComponent();
		light.setContainer("false");
		light.setUid("LightboxHomeDeliveryBannerComponent");
		light.setExternal("false");
		light.setName("Lightbox Home Delivery Banner Component");
		light.setSynchronizationBlocked("false");
		light.setUuid(
				"eyJpdGVtSWQiOiJMaWdodGJveEhvbWVEZWxpdmVyeUJhbm5lckNvbXBvbmVudCIsImNhdGFsb2dJZCI6Im1lcmNlZGVzYmVuei1zcGFDb250ZW50Q2F0YWxvZyIsImNhdGFsb2dWZXJzaW9uIjoiT25saW5lIn0");
		light.setTypeCode("SimpleBannerComponent");
		sprComponent.setLightboxBannerComponent(light);
		sprComponent.setTitle(ampComponent.getTitle());
	}

	private void buildCMSSiteContextComponent(Component ampComponent, com.mb.spartacus.Component sprComponent) {
		populateBasicCompoent(ampComponent, sprComponent);
		sprComponent.setContext(ampComponent.getContext());
	}

	private void buildSiteLogoComponent(Component ampComponent, com.mb.spartacus.Component sprComponent) {
		populateBasicCompoent(ampComponent, sprComponent);
		sprComponent.setExternal(ampComponent.getExternal().toString());
		com.mb.amplience.Media ampMedia = ampComponent.getMedia();
		if (ampMedia != null) {
			Media media = new Media();
			media.setCode(ampMedia.getMedia().getId());
			media.setMime("image/png");
			media.setAltText(ampMedia.getAltText());
			media.setUrl("https://" + ampMedia.getMedia().getDefaultHost() + "/i/" + ampMedia.getMedia().getEndpoint()
					+ "/" + ampMedia.getMedia().getName());
			sprComponent.setMedia(media);
		}
		;
		sprComponent.setUrlLink(ampComponent.getUrlLink());
	}

	private void buildCMSLinkComponent(Component ampComponent, com.mb.spartacus.Component sprComponent) {
		populateBasicCompoent(ampComponent, sprComponent);
		sprComponent.setUrl(ampComponent.getUrl());
		sprComponent.setLinkName(ampComponent.getLinkName());
		sprComponent.setTarget(ampComponent.getTarget());
	}

	private void buildSearchBoxComponent(Component ampComponent, com.mb.spartacus.Component sprComponent) {
		populateBasicCompoent(ampComponent, sprComponent);
		sprComponent.setMaxSuggestions(ampComponent.getMaxSuggestions().toString());
		sprComponent.setMaxProducts(ampComponent.getMaxProducts().toString());
		sprComponent.setDisplaySuggestions(ampComponent.getDisplaySuggestions().toString());
		sprComponent.setDisplayProducts(ampComponent.getDisplayProducts().toString());
		sprComponent.setDisplayProductImages(ampComponent.getDisplayProductImages().toString());
		sprComponent.setWaitTimeBeforeRequest(ampComponent.getWaitTimeBeforeRequest().toString());
		sprComponent.setMinCharactersBeforeRequest(ampComponent.getMinCharactersBeforeRequest().toString());
	}

	private void buildCategoryNavigationComponent(Component ampComponent, com.mb.spartacus.Component sprComponent) {
		populateBasicCompoent(ampComponent, sprComponent);
		sprComponent.setWrapAfter("10");
		NavigationNode parentNavNode = new NavigationNode();
		parentNavNode.setUid(ampComponent.getNavigationNode().get(0).getUid());
		parentNavNode.setUuid(ampComponent.getNavigationNode().get(0).getMeta().getDeliveryId());
		parentNavNode.setEntries(null);
		// API call to fetch navigation details
		List<NavigationNode> navNodes = new ArrayList<>();
		try {
			Category category = getCategoryNavNodeDetails(
					ampComponent.getNavigationNode().get(0).getMeta().getDeliveryId());
			for (Response res : category.getResponses()) {
				NavigationNode navNode1 = new NavigationNode();
				navNode1.setUid(res.getContent().getUid());
				navNode1.setUuid(res.getContent().getMeta().getDeliveryId());
				Entry nav1Entry = new Entry();
				if (!"7774976d-7fb0-4347-a65b-8140776fe5d7".equals(res.getContent().getMeta().getDeliveryId())) {
					nav1Entry.setItemId(res.getContent().getMeta().getDeliveryId() + AMP_CAT_LINK);
					nav1Entry.setItemSuperType("AbstractCMSComponent");
					nav1Entry.setItemType("CMSLinkComponent");
				}
				List<Entry> entryList = new ArrayList<>();
				entryList.add(nav1Entry);
				navNode1.setEntries(entryList);
				List<NavigationNode> emptyChildrenList = new ArrayList<>();
				// navNode1.setTitle(res.getContent().getUid());
				navNode1.setChildren(emptyChildrenList);
				navNodes.add(navNode1);
			}
			parentNavNode.setChildren(navNodes);
			sprComponent.setNavigationNode(parentNavNode);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private Category getCategoryNavNodeDetails(String id) throws Exception {
		String localvalue = getGlobalLocal() != null ? getGlobalLocal() : "de-DE";
		String body = "{\r\n" + "   \"filterBy\":[\r\n" + "      {\r\n"
				+ "         \"path\":\"/_meta/hierarchy/parentId\",\r\n" + "         \"value\":\"" + id + "\"\r\n"
				+ "      }\r\n" + "   ],\r\n" + "   \"parameters\":{\r\n" + "      \"locale\":\"" + localvalue
				+ "\"\r\n" + "   }\r\n" + "}";
		Gson gson = new Gson();
		java.net.http.HttpRequest request = java.net.http.HttpRequest.newBuilder()
				.uri(URI.create("https://capgsandbox.cdn.content.amplience.net/content/filter"))
				.POST(BodyPublishers.ofString(body)).timeout(Duration.ofMinutes(2))
				.header("Content-Type", "application/json").build();
		HttpClient client = HttpClient.newBuilder().version(HttpClient.Version.HTTP_1_1)
				.followRedirects(HttpClient.Redirect.NORMAL).connectTimeout(Duration.ofMinutes(2)).build();
		java.net.http.HttpResponse<String> response1 = client.send(request,
				java.net.http.HttpResponse.BodyHandlers.ofString());
		Category response = gson.fromJson(response1.body(), Category.class);
		return response;
	}

	private void buildNavigationComponent(Component ampComponent, com.mb.spartacus.Component sprComponent) {
		populateBasicCompoent(ampComponent, sprComponent);

		NavigationNode parentNavNode = new NavigationNode();
		parentNavNode.setUid(ampComponent.getNavigationNode().get(0).getUid());
		parentNavNode.setUuid(ampComponent.getNavigationNode().get(0).getMeta().getDeliveryId());
		parentNavNode.setEntries(null);
		// API call to fetch navigation details
		List<NavigationNode> navNodes = new ArrayList<>();
		try {
			Category category = getCategoryNavNodeDetails(
					ampComponent.getNavigationNode().get(0).getMeta().getDeliveryId());
			for (Response res : category.getResponses()) {
				NavigationNode navNode1 = new NavigationNode();
				navNode1.setUid(res.getContent().getUid());
				navNode1.setUuid(res.getContent().getMeta().getDeliveryId());
				Entry nav1Entry = new Entry();
				nav1Entry.setItemId(res.getContent().getMeta().getDeliveryId() + AMP_NAV_LINK);
				nav1Entry.setItemSuperType("AbstractCMSComponent");
				nav1Entry.setItemType("CMSLinkComponent");
				List<Entry> entryList = new ArrayList<>();
				entryList.add(nav1Entry);
				navNode1.setEntries(entryList);
				List<NavigationNode> emptyChildrenList = new ArrayList<>();
				navNode1.setTitle(res.getContent().getTitle());
				navNode1.setChildren(emptyChildrenList);
				navNodes.add(navNode1);
			}
			parentNavNode.setChildren(navNodes);
			parentNavNode.setTitle(ampComponent.getNavigationNode().get(0).getTitle());
			sprComponent.setNavigationNode(parentNavNode);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void buildFooterNavigationComponent(Component ampComponent, com.mb.spartacus.Component sprComponent) {
		populateBasicCompoent(ampComponent, sprComponent);
		sprComponent.setWrapAfter(ampComponent.getWrapAfter().toString());
		NavigationNode parentNavNode = new NavigationNode();
		parentNavNode.setUid(ampComponent.getNavigationNode().get(0).getUid());
		parentNavNode.setUuid(ampComponent.getNavigationNode().get(0).getMeta().getDeliveryId());
		List<Entry> emptyEntries = new ArrayList<>();
		parentNavNode.setEntries(emptyEntries);
		// API call to fetch navigation details
		List<NavigationNode> navNodes = new ArrayList<>();
		try {
			Category category = getCategoryNavNodeDetails(
					ampComponent.getNavigationNode().get(0).getMeta().getDeliveryId());
			for (Response res : category.getResponses()) {
				NavigationNode navNode1 = new NavigationNode();
				navNode1.setUid(res.getContent().getUid());
				navNode1.setUuid(res.getContent().getMeta().getDeliveryId());
				Entry nav1Entry = new Entry();
				List<Entry> entryList = new ArrayList<>();
				entryList.add(nav1Entry);
				navNode1.setEntries(entryList);
				navNode1.setTitle(res.getContent().getTitle());
				List<NavigationNode> childrenList = new ArrayList<>();
				Category childerenCategory = getCategoryNavNodeDetails(res.getContent().getMeta().getDeliveryId());
				for (Response res1 : childerenCategory.getResponses()) {
					NavigationNode childNode1 = new NavigationNode();
					childNode1.setUid(res1.getContent().getUid());
					childNode1.setUuid(res1.getContent().getMeta().getDeliveryId());
					Entry childnav1Entry = new Entry();
					childnav1Entry.setItemId(res1.getContent().getMeta().getDeliveryId() + AMP_FOOT_LINK);
					childnav1Entry.setItemSuperType("AbstractCMSComponent");
					childnav1Entry.setItemType("CMSLinkComponent");
					List<Entry> childentryList = new ArrayList<>();
					childentryList.add(childnav1Entry);
					childNode1.setEntries(childentryList);
					// childNode1.setTitle(res1.getContent().getUid());
					childrenList.add(childNode1);
				}
				navNode1.setChildren(childrenList);

				navNodes.add(navNode1);
			}
			parentNavNode.setChildren(navNodes);
			sprComponent.setNavigationNode(parentNavNode);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean isLoggedInreuest() {
		return isLoggedInreuest;
	}

	public void setLoggedInreuest(boolean isLoggedInreuest) {
		this.isLoggedInreuest = isLoggedInreuest;
	}
}
