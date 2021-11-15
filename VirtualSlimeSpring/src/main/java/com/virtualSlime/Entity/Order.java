package com.virtualSlime.Entity;

import com.virtualSlime.Enum.OrderState;

import java.util.Date;

public class Order {
    private Integer oid;//user_order.oid unsigned int
    private Integer uid;//user_order.uid unsigned int
    private Integer iid;//user_order.iid unsigned int
    private Long createdAt;//user_order_created_at timestamp
    private Long paidAt;//user_order.paid_time timestamp
    private Double paidPrice;//decimal(12,2):parse needed!
    private OrderState orderState;//user_order.state unsigned tinyint

    public Order(Integer uid, Integer iid, Long createAt) {
        this.uid = uid;
        this.iid = iid;
        this.createdAt = createAt;
    }

    public Order(Integer oid, Integer uid, Integer iid, Long createdAt,
                 Long paidAt, Double paidPrice, OrderState orderState) {
        this.oid = oid;
        this.uid = uid;
        this.iid = iid;
        this.createdAt = createdAt;
        this.paidAt = paidAt;
        this.paidPrice = paidPrice;
        this.orderState = orderState;
    }

    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getIid() {
        return iid;
    }

    public void setIid(Integer iid) {
        this.iid = iid;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    public Long getPaidAt() {
        return paidAt;
    }

    public void setPaidAt(Long paidAt) {
        this.paidAt = paidAt;
    }

    public Double getPaidPrice() {
        return paidPrice;
    }

    public void setPaidPrice(Double paidPrice) {
        this.paidPrice = paidPrice;
    }

    public OrderState getOrderState() {
        return orderState;
    }

    public void setOrderState(OrderState orderState) {
        this.orderState = orderState;
    }

    @Override
    public String toString() {
        return "Order{" +
                "oid=" + oid +
                ", uid=" + uid +
                ", iid=" + iid +
                ", createAt=" + createdAt +
                ", paidAt=" + paidAt +
                ", paidPrice=" + paidPrice +
                ", orderState=" + orderState +
                '}';
    }
}
