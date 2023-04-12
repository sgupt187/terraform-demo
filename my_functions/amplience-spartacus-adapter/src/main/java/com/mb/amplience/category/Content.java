
package com.mb.amplience.category;

import java.util.List;

import javax.annotation.processing.Generated;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Content {

    @SerializedName("_meta")
    @Expose
    private Meta meta;
    @SerializedName("entries")
    @Expose
    private List<Entry> entries = null;
    @SerializedName("uid")
    @Expose
    private String uid;
    
    @SerializedName("title")
    @Expose
    private String title;

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public List<Entry> getEntries() {
        return entries;
    }

    public void setEntries(List<Entry> entries) {
        this.entries = entries;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
