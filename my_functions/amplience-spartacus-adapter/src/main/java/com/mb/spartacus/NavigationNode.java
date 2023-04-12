
package com.mb.spartacus;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.processing.Generated;
import java.util.List;

@Generated("jsonschema2pojo")
public class NavigationNode {

    @SerializedName("uid")
    @Expose
    private String uid;
    @SerializedName("uuid")
    @Expose
    private String uuid;
    @SerializedName("entries")
    @Expose
    private List<Entry> entries = null;
    @SerializedName("children")
    @Expose
    private List<NavigationNode> children = null;
    @SerializedName("title")
    @Expose
    private String title;

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

    public List<Entry> getEntries() {
        return entries;
    }

    public void setEntries(List<Entry> entries) {
        this.entries = entries;
    }

    public List<NavigationNode> getChildren() {
        return children;
    }

    public void setChildren(List<NavigationNode> children) {
        this.children = children;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
