package com.virtualSlime.Entity;

import com.virtualSlime.Enum.ItemState;

public class Item {
    private Integer iid;
    private Integer uid;
    private String itemName;//<= 50
    private String itemBrief;
    private Double itemPrice;//decimal(12,2):parse needed
    private Boolean isDiscounting;
    private Double itemPriceDiscounted;//decimal(12,2):parse needed
    private Short cid;
    private Integer visitCount;
    private ItemState itemState;

    public Item(Integer uid, String itemName, String itemBrief, Double itemPrice, Short cid) {
        this.uid = uid;
        this.itemName = itemName;
        this.itemBrief = itemBrief;
        this.itemPrice = itemPrice;
        this.cid = cid;
    }

    public Item(Integer iid, Integer uid, String itemName, String itemBrief,
                Double itemPrice, Boolean isDiscounting, Double itemPriceDiscounted,
                Short cid, Integer visitCount, ItemState itemState) {
        this.iid = iid;
        this.uid = uid;
        this.itemName = itemName;
        this.itemBrief = itemBrief;
        this.itemPrice = itemPrice;
        this.isDiscounting = isDiscounting;
        this.itemPriceDiscounted = itemPriceDiscounted;
        this.cid = cid;
        this.visitCount = visitCount;
        this.itemState = itemState;
    }

    public Integer getIid() {
        return iid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemBrief() {
        return itemBrief;
    }

    public void setItemBrief(String itemBrief) {
        this.itemBrief = itemBrief;
    }

    public Double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(Double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public Boolean getDiscounting() {
        return isDiscounting;
    }

    public void setDiscounting(Boolean discounting) {
        isDiscounting = discounting;
    }

    public Double getItemPriceDiscounted() {
        return itemPriceDiscounted;
    }

    public void setItemPriceDiscounted(Double itemPriceDiscounted) {
        this.itemPriceDiscounted = itemPriceDiscounted;
    }

    public Short getCid() {
        return cid;
    }

    public void setCid(Short cid) {
        this.cid = cid;
    }

    public Integer getVisitCount() {
        return visitCount;
    }

    public void setVisitCount(Integer visitCount) {
        this.visitCount = visitCount;
    }

    public ItemState getItemState() {
        return itemState;
    }

    public void setItemState(ItemState itemState) {
        this.itemState = itemState;
    }
}
