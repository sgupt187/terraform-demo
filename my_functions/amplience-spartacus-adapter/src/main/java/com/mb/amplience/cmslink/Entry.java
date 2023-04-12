
package com.mb.amplience.cmslink;

import javax.annotation.processing.Generated;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Entry {

    @SerializedName("_meta")
    @Expose
    private Meta__1 meta;
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

}
