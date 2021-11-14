package com.virtualslime.Entity;

enum ItemState{
    NORMAL, PROMOTED, HIDDEN
}

public class Item {
    private int iid;//item_info.iid unsigned int
    private int uid;//item_info.uid unsigned int
    private String itemName;//item_info.name varchar(50)
    private String itemBrief;//item_info.brief text
    private double itemPrice;//item_info.price decimal(12,2):parse needed
    private boolean isDiscounting;//item_info.is_discounting boolean
    private double itemPriceDiscounted;//item_info.price_discounted decimal(12,2):parse needed
    private short cid;//item_info.cid unsigned smallint
    private int visitCount;//item_info.visit_count unsigned int
    private ItemState itemState;//item_info.state unsigned tinyint

    public Item() {
        this.iid = 0;
        this.uid = 0;
        this.itemName = "null";
        this.itemBrief = "null";
        this.itemPrice = 0;
        this.isDiscounting = false;
        this.itemPriceDiscounted = 0;
        this.cid = 0;
        this.visitCount = 0;
        this.itemState = ItemState.NORMAL;
    }

    public Item(int uid, String itemName, String itemBrief, double itemPrice, short cid) {
        this.uid = uid;
        this.itemName = itemName;
        this.itemBrief = itemBrief;
        this.itemPrice = itemPrice;
        this.isDiscounting = false;
        this.itemPriceDiscounted = 0;
        this.cid = cid;
        this.visitCount = 0;
        this.itemState = ItemState.NORMAL;
    }

    public Item(int iid, int uid, String itemName, String itemBrief, double itemPrice,
                boolean isDiscounting, double itemPriceDiscounted, short cid,
                int visitCount, ItemState itemState) {
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

    public int getIid() {
        return iid;
    }

    public void setIid(int iid) {
        this.iid = iid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
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

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public boolean isDiscounting() {
        return isDiscounting;
    }

    public void setDiscounting(boolean discounting) {
        isDiscounting = discounting;
    }

    public double getItemPriceDiscounted() {
        return itemPriceDiscounted;
    }

    public void setItemPriceDiscounted(double itemPriceDiscounted) {
        this.itemPriceDiscounted = itemPriceDiscounted;
    }

    public short getCid() {
        return cid;
    }

    public void setCid(short cid) {
        this.cid = cid;
    }

    public int getVisitCount() {
        return visitCount;
    }

    public void setVisitCount(int visitCount) {
        this.visitCount = visitCount;
    }

    public ItemState getItemState() {
        return itemState;
    }

    public void setItemState(ItemState itemState) {
        this.itemState = itemState;
    }
}
