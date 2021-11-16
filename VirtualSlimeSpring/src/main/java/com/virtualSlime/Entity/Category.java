package com.virtualSlime.Entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName(schema = "virtual_slime", value = "admin")
public class Category {
    @TableId(type = IdType.AUTO)
    private final Short cid;
    private Short cidUpper;
    private String catName;//<= 20
    private String catBrief;//<= 50

    public Category(Short cidUpper, String catName, String catBrief) {
        this.cid = null;
        this.cidUpper = cidUpper;
        this.catName = catName;
        this.catBrief = catBrief;
    }

    public Category(Short cid, Short cidUpper, String catName, String catBrief) {
        this.cid = cid;
        this.cidUpper = cidUpper;
        this.catName = catName;
        this.catBrief = catBrief;
    }

    public Short getCid() {
        return cid;
    }

    public Short getCidUpper() {
        return cidUpper;
    }

    public void setCidUpper(Short cidUpper) {
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
