
package com.mb.amplience;

import javax.annotation.processing.Generated;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Media {

    @SerializedName("media")
    @Expose
    private Media__1 media;
    @SerializedName("altText")
    @Expose
    private String altText;
    @SerializedName("_meta")
    @Expose
    private Meta__4 meta;

    public Media__1 getMedia() {
        return media;
    }

    public void setMedia(Media__1 media) {
        this.media = media;
    }

    public String getAltText() {
        return altText;
    }

    public void setAltText(String altText) {
        this.altText = altText;
    }

    public Meta__4 getMeta() {
        return meta;
    }

    public void setMeta(Meta__4 meta) {
        this.meta = meta;
    }

}
