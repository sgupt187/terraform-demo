
package com.mb.amplience;

import javax.annotation.processing.Generated;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Media__2 {

    @SerializedName("media")
    @Expose
    private Media__3 media;
    @SerializedName("altText")
    @Expose
    private String altText;
    @SerializedName("_meta")
    @Expose
    private Meta__7 meta;

    public Media__3 getMedia() {
        return media;
    }

    public void setMedia(Media__3 media) {
        this.media = media;
    }

    public String getAltText() {
        return altText;
    }

    public void setAltText(String altText) {
        this.altText = altText;
    }

    public Meta__7 getMeta() {
        return meta;
    }

    public void setMeta(Meta__7 meta) {
        this.meta = meta;
    }

}
