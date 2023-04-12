
package com.mb.amplience;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.processing.Generated;

@Generated("jsonschema2pojo")
public class Banner {

    @SerializedName("_meta")
    @Expose
    private Meta__5 meta;
    @SerializedName("container")
    @Expose
    private Boolean container;
    @SerializedName("external")
    @Expose
    private Boolean external;
    @SerializedName("media")
    @Expose
    private Media__2 media;
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

    public Meta__5 getMeta() {
        return meta;
    }

    public void setMeta(Meta__5 meta) {
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

    public Media__2 getMedia() {
        return media;
    }

    public void setMedia(Media__2 media) {
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
