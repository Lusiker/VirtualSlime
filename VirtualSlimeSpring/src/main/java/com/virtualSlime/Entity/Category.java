package com.virtualSlime.Entity;

public class Category {
    private Byte cid;
    private Byte cidUpper;
    private String catName;//<= 20
    private String catBrief;//<= 50

    public Category(Byte cidUpper, String catName, String catBrief) {
        this.cidUpper = cidUpper;
        this.catName = catName;
        this.catBrief = catBrief;
    }

    public Category(Byte cid, Byte cidUpper, String catName, String catBrief) {
        this.cid = cid;
        this.cidUpper = cidUpper;
        this.catName = catName;
        this.catBrief = catBrief;
    }

    public Byte getCid() {
        return cid;
    }

    public void setCid(Byte cid) {
        this.cid = cid;
    }

    public Byte getCidUpper() {
        return cidUpper;
    }

    public void setCidUpper(Byte cidUpper) {
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

    @Override
    public String toString() {
        return "Category{" +
                "cid=" + cid +
                ", cidUpper=" + cidUpper +
                ", catName='" + catName + '\'' +
                ", catBrief='" + catBrief + '\'' +
                '}';
    }
}
