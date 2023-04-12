
package com.mb.amplience;

import java.util.List;

import javax.annotation.processing.Generated;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Content {

    @SerializedName("_meta")
    @Expose
    private Meta meta;
    @SerializedName("contentSlots")
    @Expose
    private List<ContentSlot> contentSlots = null;
    @SerializedName("uid")
    @Expose
    private String uid;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("label")
    @Expose
    private String label;
    @SerializedName("typeCode")
    @Expose
    private String typeCode;
    @SerializedName("Template")
    @Expose
    private String template;

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public List<ContentSlot> getContentSlots() {
        return contentSlots;
    }

    public void setContentSlots(List<ContentSlot> contentSlots) {
        this.contentSlots = contentSlots;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

}
