
package com.mb.spartacus;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.processing.Generated;

@Generated("jsonschema2pojo")
public class LightboxBannerComponent {

    @SerializedName("container")
    @Expose
    private String container;
    @SerializedName("uid")
    @Expose
    private String uid;
    @SerializedName("external")
    @Expose
    private String external;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("synchronizationBlocked")
    @Expose
    private String synchronizationBlocked;
    @SerializedName("uuid")
    @Expose
    private String uuid;
    @SerializedName("typeCode")
    @Expose
    private String typeCode;

    public String getContainer() {
        return container;
    }

    public void setContainer(String container) {
        this.container = container;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getExternal() {
        return external;
    }

    public void setExternal(String external) {
        this.external = external;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSynchronizationBlocked() {
        return synchronizationBlocked;
    }

    public void setSynchronizationBlocked(String synchronizationBlocked) {
        this.synchronizationBlocked = synchronizationBlocked;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

}
