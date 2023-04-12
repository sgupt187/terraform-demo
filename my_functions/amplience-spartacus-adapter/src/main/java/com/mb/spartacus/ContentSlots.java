
package com.mb.spartacus;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.processing.Generated;
import java.util.List;

@Generated("jsonschema2pojo")
public class ContentSlots {

    @SerializedName("contentSlot")
    @Expose
    private List<ContentSlot> contentSlot = null;

    public List<ContentSlot> getContentSlot() {
        return contentSlot;
    }

    public void setContentSlot(List<ContentSlot> contentSlot) {
        this.contentSlot = contentSlot;
    }

}
