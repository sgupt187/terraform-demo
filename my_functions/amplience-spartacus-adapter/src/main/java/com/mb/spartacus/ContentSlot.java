
package com.mb.spartacus;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.processing.Generated;

@Generated("jsonschema2pojo")
public class ContentSlot {

    @SerializedName("slotId")
    @Expose
    private String slotId;
    @SerializedName("slotUuid")
    @Expose
    private String slotUuid;
    @SerializedName("position")
    @Expose
    private String position;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("slotShared")
    @Expose
    private Boolean slotShared;
    @SerializedName("components")
    @Expose
    private Components components;

    public String getSlotId() {
        return slotId;
    }

    public void setSlotId(String slotId) {
        this.slotId = slotId;
    }

    public String getSlotUuid() {
        return slotUuid;
    }

    public void setSlotUuid(String slotUuid) {
        this.slotUuid = slotUuid;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getSlotShared() {
        return slotShared;
    }

    public void setSlotShared(Boolean slotShared) {
        this.slotShared = slotShared;
    }

    public Components getComponents() {
        return components;
    }

    public void setComponents(Components components) {
        this.components = components;
    }

}
