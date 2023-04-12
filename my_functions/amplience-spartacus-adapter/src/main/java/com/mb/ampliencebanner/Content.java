
package com.mb.ampliencebanner;

import java.util.List;

import javax.annotation.processing.Generated;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Content {

    @SerializedName("_meta")
    @Expose
    private Meta meta;
    @SerializedName("banners")
    @Expose
    private List<Banner> banners = null;
    @SerializedName("loop")
    @Expose
    private Boolean loop;
    @SerializedName("navigationDots")
    @Expose
    private Boolean navigationDots;
    @SerializedName("container")
    @Expose
    private Boolean container;
    @SerializedName("typeCode")
    @Expose
    private String typeCode;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("uid")
    @Expose
    private String uid;
    @SerializedName("effect")
    @Expose
    private String effect;
    @SerializedName("synchronizationBlocked")
    @Expose
    private String synchronizationBlocked;

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
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

    public Boolean getContainer() {
        return container;
    }

    public void setContainer(Boolean container) {
        this.container = container;
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

    public String getEffect() {
        return effect;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }

    public String getSynchronizationBlocked() {
        return synchronizationBlocked;
    }

    public void setSynchronizationBlocked(String synchronizationBlocked) {
        this.synchronizationBlocked = synchronizationBlocked;
    }

}
