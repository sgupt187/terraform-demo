
package com.mb.amplience;

import java.util.List;

import javax.annotation.processing.Generated;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Component {

    @SerializedName("_meta")
    @Expose
    private Meta__2 meta;
    @SerializedName("container")
    @Expose
    private Boolean container;
    @SerializedName("external")
    @Expose
    private Boolean external;
    @SerializedName("typeCode")
    @Expose
    private String typeCode;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("uid")
    @Expose
    private String uid;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("target")
    @Expose
    private String target;
    @SerializedName("synchronizationBlocked")
    @Expose
    private String synchronizationBlocked;
    @SerializedName("linkName")
    @Expose
    private String linkName;
    @SerializedName("media")
    @Expose
    private Media media;
    @SerializedName("urlLink")
    @Expose
    private String urlLink;
    @SerializedName("displayProductImages")
    @Expose
    private Boolean displayProductImages;
    @SerializedName("displayProducts")
    @Expose
    private Boolean displayProducts;
    @SerializedName("displaySuggestions")
    @Expose
    private Boolean displaySuggestions;
    @SerializedName("maxProducts")
    @Expose
    private Integer maxProducts;
    @SerializedName("maxSuggestions")
    @Expose
    private Integer maxSuggestions;
    @SerializedName("minCharactersBeforeRequest")
    @Expose
    private Integer minCharactersBeforeRequest;
    @SerializedName("waitTimeBeforeRequest")
    @Expose
    private Integer waitTimeBeforeRequest;
    @SerializedName("shownProductCount")
    @Expose
    private Integer shownProductCount;
    @SerializedName("totalDisplay")
    @Expose
    private String totalDisplay;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("banners")
    @Expose
    private List<Banner> banners = null;
    @SerializedName("loop")
    @Expose
    private Boolean loop;
    @SerializedName("navigationDots")
    @Expose
    private Boolean navigationDots;
    @SerializedName("effect")
    @Expose
    private String effect;
    @SerializedName("popup")
    @Expose
    private Boolean popup;
    @SerializedName("productCodes")
    @Expose
    private String productCodes;
    @SerializedName("scroll")
    @Expose
    private String scroll;
    @SerializedName("navigationNode")
    @Expose
    private List<NavigationNode> navigationNode = null;
    @SerializedName("wrapAfter")
    @Expose
    private Integer wrapAfter;
    @SerializedName("context")
    @Expose
    private String context;
    @SerializedName("showLanguageCurrency")
    @Expose
    private String showLanguageCurrency;
    @SerializedName("notice")
    @Expose
    private String notice;
    @SerializedName("flexType")
    @Expose
    private String flexType;
    @SerializedName("content")
    @Expose
    private String content;

    public Meta__2 getMeta() {
        return meta;
    }

    public void setMeta(Meta__2 meta) {
        this.meta = meta;
    }

    public Boolean getContainer() {
        return container;
    }

    public void setContainer(Boolean container) {
        this.container = container;
    }

    public Boolean getExternal() {
        return external;
    }

    public void setExternal(Boolean external) {
        this.external = external;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getSynchronizationBlocked() {
        return synchronizationBlocked;
    }

    public void setSynchronizationBlocked(String synchronizationBlocked) {
        this.synchronizationBlocked = synchronizationBlocked;
    }

    public String getLinkName() {
        return linkName;
    }

    public void setLinkName(String linkName) {
        this.linkName = linkName;
    }

    public Media getMedia() {
        return media;
    }

    public void setMedia(Media media) {
        this.media = media;
    }

    public String getUrlLink() {
        return urlLink;
    }

    public void setUrlLink(String urlLink) {
        this.urlLink = urlLink;
    }

    public Boolean getDisplayProductImages() {
        return displayProductImages;
    }

    public void setDisplayProductImages(Boolean displayProductImages) {
        this.displayProductImages = displayProductImages;
    }

    public Boolean getDisplayProducts() {
        return displayProducts;
    }

    public void setDisplayProducts(Boolean displayProducts) {
        this.displayProducts = displayProducts;
    }

    public Boolean getDisplaySuggestions() {
        return displaySuggestions;
    }

    public void setDisplaySuggestions(Boolean displaySuggestions) {
        this.displaySuggestions = displaySuggestions;
    }

    public Integer getMaxProducts() {
        return maxProducts;
    }

    public void setMaxProducts(Integer maxProducts) {
        this.maxProducts = maxProducts;
    }

    public Integer getMaxSuggestions() {
        return maxSuggestions;
    }

    public void setMaxSuggestions(Integer maxSuggestions) {
        this.maxSuggestions = maxSuggestions;
    }

    public Integer getMinCharactersBeforeRequest() {
        return minCharactersBeforeRequest;
    }

    public void setMinCharactersBeforeRequest(Integer minCharactersBeforeRequest) {
        this.minCharactersBeforeRequest = minCharactersBeforeRequest;
    }

    public Integer getWaitTimeBeforeRequest() {
        return waitTimeBeforeRequest;
    }

    public void setWaitTimeBeforeRequest(Integer waitTimeBeforeRequest) {
        this.waitTimeBeforeRequest = waitTimeBeforeRequest;
    }

    public Integer getShownProductCount() {
        return shownProductCount;
    }

    public void setShownProductCount(Integer shownProductCount) {
        this.shownProductCount = shownProductCount;
    }

    public String getTotalDisplay() {
        return totalDisplay;
    }

    public void setTotalDisplay(String totalDisplay) {
        this.totalDisplay = totalDisplay;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Banner> getBanners() {
        return banners;
    }

    public void setBanners(List<Banner> banners) {
        this.banners = banners;
    }

    public Boolean getLoop() {
        return loop;
    }

    public void setLoop(Boolean loop) {
        this.loop = loop;
    }

    public Boolean getNavigationDots() {
        return navigationDots;
    }

    public void setNavigationDots(Boolean navigationDots) {
        this.navigationDots = navigationDots;
    }

    public String getEffect() {
        return effect;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }

    public Boolean getPopup() {
        return popup;
    }

    public void setPopup(Boolean popup) {
        this.popup = popup;
    }

    public String getProductCodes() {
        return productCodes;
    }

    public void setProductCodes(String productCodes) {
        this.productCodes = productCodes;
    }

    public String getScroll() {
        return scroll;
    }

    public void setScroll(String scroll) {
        this.scroll = scroll;
    }

    public List<NavigationNode> getNavigationNode() {
        return navigationNode;
    }

    public void setNavigationNode(List<NavigationNode> navigationNode) {
        this.navigationNode = navigationNode;
    }

    public Integer getWrapAfter() {
        return wrapAfter;
    }

    public void setWrapAfter(Integer wrapAfter) {
        this.wrapAfter = wrapAfter;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getShowLanguageCurrency() {
        return showLanguageCurrency;
    }

    public void setShowLanguageCurrency(String showLanguageCurrency) {
        this.showLanguageCurrency = showLanguageCurrency;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public String getFlexType() {
        return flexType;
    }

    public void setFlexType(String flexType) {
        this.flexType = flexType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
