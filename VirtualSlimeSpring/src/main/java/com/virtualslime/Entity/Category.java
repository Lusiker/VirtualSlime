package com.virtualslime.Entity;

public class Category {
    private short cid;//item_category.cid unsigned smallint
    private short cidUpper;//item_category.belongs_to unsigned smallint
    private String catName;//item_category.name varchar(20)
    private String catBrief;//item_category.brief varchar(50)

    public Category() {
        this.cid = 0;
        this.cidUpper = 0;
        this.catName = "null";
        this.catBrief = "null";
    }

    public Category(short cidUpper, String catName, String catBrief) {
        this.cidUpper = cidUpper;
        this.catName = catName;
        this.catBrief = catBrief;
    }

    public Category(short cid, short cidUpper, String catName, String catBrief) {
        this.cid = cid;
        this.cidUpper = cidUpper;
        this.catName = catName;
        this.catBrief = catBrief;
    }

    public short getCid() {
        return cid;
    }

    public void setCid(short cid) {
        this.cid = cid;
    }

    public short getCidUpper() {
        return cidUpper;
    }

    public void setCidUpper(short cidUpper) {
        this.cidUpper = cidUpper;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public String getCatBrief() {
        return catBrief;
    }

    public void setCatBrief(String catBrief) {
        this.catBrief = catBrief;
    }
}
