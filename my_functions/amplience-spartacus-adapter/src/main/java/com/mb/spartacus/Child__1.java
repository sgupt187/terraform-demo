
package com.mb.spartacus;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.processing.Generated;
import java.util.List;

@Generated("jsonschema2pojo")
public class Child__1 {

    @SerializedName("uid")
    @Expose
    private String uid;
    @SerializedName("uuid")
    @Expose
    private String uuid;
    @SerializedName("entries")
    @Expose
    private List<Entry__1> entries = null;
    @SerializedName("children")
    @Expose
    private List<Object> children = null;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public List<Entry__1> getEntries() {
        return entries;
    }

    public void setEntries(List<Entry__1> entries) {
        this.entries = entries;
    }

    public List<Object> getChildren() {
        return children;
    }

    public void setChildren(List<Object> children) {
        this.children = children;
    }

}
