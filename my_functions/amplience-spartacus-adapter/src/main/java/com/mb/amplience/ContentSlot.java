
package com.mb.amplience;

import java.util.List;

import javax.annotation.processing.Generated;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class ContentSlot {

    @SerializedName("_meta")
    @Expose
    private Meta__1 meta;
    @SerializedName("slotShared")
    @Expose
    private Boolean slotShared;
    @SerializedName("components")
    @Expose
    private List<Component> components = null;
    @SerializedName("slotId")
    @Expose
    private String slotId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("position")
    @Expose
    private String position;

    public Meta__1 getMeta() {
        return meta;
    }

    public void setMeta(Meta__1 meta) {
        this.meta = meta;
    }

    public Boolean getSlotShared() {
        return slotShared;
    }

    public void setSlotShared(Boolean slotShared) {
        this.slotShared = slotShared;
    }

    public List<Component> getComponents() {
        return components;
    }

    public void setComponents(List<Component> components) {
        this.components = components;
    }

    public String getSlotId() {
        return slotId;
    }

    public void setSlotId(String slotId) {
        this.slotId = slotId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

}
