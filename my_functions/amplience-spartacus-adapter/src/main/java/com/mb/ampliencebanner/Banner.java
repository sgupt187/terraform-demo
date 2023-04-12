
package com.mb.ampliencebanner;

import javax.annotation.processing.Generated;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Banner {

    @SerializedName("_meta")
    @Expose
    private Meta__1 meta;
    @SerializedName("container")
    @Expose
    private Boolean container;
    @SerializedName("external")
    @Expose
    private Boolean external;
    @SerializedName("media")
    @Expose
    private Media media;
    @SerializedName("uid")
    @Expose
    private String uid;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("typeCode")
    @Expose
    private String typeCode;
    @SerializedName("synchronizationBlocked")
    @Expose
    private String synchronizationBlocked;
    @SerializedName("urlLink")
    @Expose
    private String urlLink;

    public Meta__1 getMeta() {
        return meta;
    }

    public void setMeta(Meta__1 meta) {
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

    public Media getMedia() {
        return media;
    }

    public void setMedia(Media media) {
        this.media = media;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getSynchronizationBlocked() {
        return synchronizationBlocked;
    }

    public void setSynchronizationBlocked(String synchronizationBlocked) {
        this.synchronizationBlocked = synchronizationBlocked;
    }

    public String getUrlLink() {
        return urlLink;
    }

    public void setUrlLink(String urlLink) {
        this.urlLink = urlLink;
    }

}
