package com.virtualSlime.Entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.virtualSlime.Enum.OrderState;

import java.math.BigDecimal;
import java.sql.Timestamp;

@TableName(schema = "virtual_slime", value = "user_order")
public class Order {
    @TableId(type = IdType.AUTO)
    private final Integer oid;
    private Integer uid;
    private Timestamp createdAt;
    private Timestamp paidAt;
    private BigDecimal paidPrice;
    private OrderState orderState;

    public Order(Integer uid){
        this.oid = null;
        this.uid = uid;
    }

    public Order(Integer oid, Integer uid, Timestamp createdAt, Timestamp paidAt, BigDecimal paidPrice, OrderState orderState) {
        this.oid = oid;
        this.uid = uid;
        this.createdAt = createdAt;
        this.paidAt = paidAt;
        this.paidPrice = paidPrice;
        this.orderState = orderState;
    }

    public Integer getOid() {
        return oid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getPaidAt() {
        return paidAt;
    }

    public void setPaidAt(Timestamp paidAt) {
        this.paidAt = paidAt;
    }

    public BigDecimal getPaidPrice() {
        return paidPrice;
    }

    public void setPaidPrice(BigDecimal paidPrice) {
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
                ", createdAt=" + createdAt +
                ", paidAt=" + paidAt +
                ", paidPrice=" + paidPrice +
                ", orderState=" + orderState +
                '}';
    }
}
