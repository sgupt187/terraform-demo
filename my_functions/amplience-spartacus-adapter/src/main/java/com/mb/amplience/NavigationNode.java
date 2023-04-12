
package com.mb.amplience;

import javax.annotation.processing.Generated;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class NavigationNode {

    @SerializedName("_meta")
    @Expose
    private Meta__8 meta;
    @SerializedName("uid")
    @Expose
    private String uid;
    @SerializedName("title")
    @Expose
    private String title;
    
    public Meta__8 getMeta() {
        return meta;
    }

    public void setMeta(Meta__8 meta) {
        this.meta = meta;
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
