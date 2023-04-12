
package com.mb.amplience.category;

import javax.annotation.processing.Generated;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Entry {

    @SerializedName("_meta")
    @Expose
    private Meta__1 meta;
    @SerializedName("contentType")
    @Expose
    private String contentType;
    @SerializedName("id")
    @Expose
    private String id;

    public Meta__1 getMeta() {
        return meta;
    }

    public void setMeta(Meta__1 meta) {
        this.meta = meta;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
