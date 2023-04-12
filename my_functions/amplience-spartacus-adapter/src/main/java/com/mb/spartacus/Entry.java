
package com.mb.spartacus;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.processing.Generated;

@Generated("jsonschema2pojo")
public class Entry {

    @SerializedName("itemId")
    @Expose
    private String itemId;
    @SerializedName("itemSuperType")
    @Expose
    private String itemSuperType;
    @SerializedName("itemType")
    @Expose
    private String itemType;

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemSuperType() {
        return itemSuperType;
    }

    public void setItemSuperType(String itemSuperType) {
        this.itemSuperType = itemSuperType;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

}
