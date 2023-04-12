
package com.mb.spartacus;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.processing.Generated;

@Generated("jsonschema2pojo")
public class SpartacusResponse {

    @SerializedName("uid")
    @Expose
    private String uid;
    @SerializedName("uuid")
    @Expose
    private String uuid;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("template")
    @Expose
    private String template;
    @SerializedName("typeCode")
    @Expose
    private String typeCode;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("robotTag")
    @Expose
    private String robotTag;
    @SerializedName("contentSlots")
    @Expose
    private ContentSlots contentSlots;
    @SerializedName("label")
    @Expose
    private String label;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRobotTag() {
        return robotTag;
    }

    public void setRobotTag(String robotTag) {
        this.robotTag = robotTag;
    }

    public ContentSlots getContentSlots() {
        return contentSlots;
    }

    public void setContentSlots(ContentSlots contentSlots) {
        this.contentSlots = contentSlots;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

}
