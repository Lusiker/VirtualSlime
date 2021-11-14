package com.virtualslime.Entity;

enum TagState{
    NORMAL, PROMOTED, UNAVAILABLE, HIDDEN
}

public class Tag {
    private short tid;//item_tag.tid unsigned smallint
    private String tagName;//item_tag.name varchar(20)
    private TagState tagState;//item_tag.state unsigned tinyint

    public Tag() {
        this.tid = 0;
        this.tagName = "null";
        this.tagState = TagState.NORMAL;
    }

    public Tag(String tagName) {
        this.tagName = tagName;
        this.tagState = TagState.NORMAL;
    }

    public Tag(short tid, String tagName, TagState tagState) {
        this.tid = tid;
        this.tagName = tagName;
        this.tagState = tagState;
    }

    public short getTid() {
        return tid;
    }

    public void setTid(short tid) {
        this.tid = tid;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public TagState getTagState() {
        return tagState;
    }

    public void setTagState(TagState tagState) {
        this.tagState = tagState;
    }
}
