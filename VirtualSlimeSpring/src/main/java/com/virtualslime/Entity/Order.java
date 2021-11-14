package com.virtualslime.Entity;

import java.util.Date;

enum OrderState{
    UNDEFINED, CREATED, FINISHED, CLOSED
}

public class Order {
    private int oid;//user_order.oid unsigned int
    private int uid;//user_order.uid unsigned int
    private int iid;//user_order.iid unsigned int
    private long createAt;//user_order_created_at timestamp
    private long paidAt;//user_order.paid_time timestamp
    private double paidPrice;//user_order.pay_exact_time decimal(12,2):parse needed!
    private OrderState orderState;//user_order.state unsigned tinyint

    public Order() {
        this.oid = 0;
        this.uid = 0;
        this.iid = 0;
        this.createAt = new Date().getTime();
        this.paidAt = 0;
        this.paidPrice = 0.0;
        this.orderState = OrderState.UNDEFINED;
    }

    public Order(int uid, int iid, long createAt) {
        this.uid = uid;
        this.iid = iid;
        this.createAt = createAt;
        this.paidAt = 0;
        this.paidPrice = 0.0;
        this.orderState = OrderState.CREATED;
    }

    public Order(int oid, int uid, int iid, long createAt, long paidAt,
                 double paidPrice, OrderState orderState) {
        this.oid = oid;
        this.uid = uid;
        this.iid = iid;
        this.createAt = createAt;
        this.paidAt = paidAt;
        this.paidPrice = paidPrice;
        this.orderState = orderState;
    }

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getIid() {
        return iid;
    }

    public void setIid(int iid) {
        this.iid = iid;
    }

    public long getCreateAt() {
        return createAt;
    }

    public void setCreateAt(long createAt) {
        this.createAt = createAt;
    }

    public long getPaidAt() {
        return paidAt;
    }

    public void setPaidAt(long paidAt) {
        this.paidAt = paidAt;
    }

    public double getPaidPrice() {
        return paidPrice;
    }

    public void setPaidPrice(double paidPrice) {
        this.paidPrice = paidPrice;
    }

    public OrderState getOrderState() {
        return orderState;
    }

    public void setOrderState(OrderState orderState) {
        this.orderState = orderState;
    }
}
