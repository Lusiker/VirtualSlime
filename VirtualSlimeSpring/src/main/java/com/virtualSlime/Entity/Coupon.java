package com.virtualSlime.Entity;

import com.virtualSlime.Enum.CouponType;

public class Coupon {
    private Integer cid;//smallint
    private String couponName;//<= 20
    private String couponBrief;//<= 50
    private CouponType couponType;
    private Integer useMinRequire;
    private Double minusCount;
    private Short multiplePercentage;//[0,100]

    public Coupon(String couponName, String couponBrief, CouponType couponType, Integer useMinRequire,
                  Double minusCount, Short multiplePercentage) {
        this.couponName = couponName;
        this.couponBrief = couponBrief;
        this.couponType = couponType;
        this.useMinRequire = useMinRequire;
        this.minusCount = minusCount;
        this.multiplePercentage = multiplePercentage;
    }

    public Coupon(Integer cid, String couponName, String couponBrief, CouponType couponType,
                  Integer useMinRequire, Double minusCount, Short multiplePercentage) {
        this.cid = cid;
        this.couponName = couponName;
        this.couponBrief = couponBrief;
        this.couponType = couponType;
        this.useMinRequire = useMinRequire;
        this.minusCount = minusCount;
        this.multiplePercentage = multiplePercentage;
    }

    public Integer getCid() {
        return cid;
    }

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName;
    }

    public String getCouponBrief() {
        return couponBrief;
    }

    public void setCouponBrief(String couponBrief) {
        this.couponBrief = couponBrief;
    }

    public CouponType getCouponType() {
        return couponType;
    }

    public void setCouponType(CouponType couponType) {
        this.couponType = couponType;
    }

    public Integer getUseMinRequire() {
        return useMinRequire;
    }

    public void setUseMinRequire(Integer useMinRequire) {
        this.useMinRequire = useMinRequire;
    }

    public Double getMinusCount() {
        return minusCount;
    }

    public void setMinusCount(Double minusCount) {
        this.minusCount = minusCount;
    }

    public Short getMultiplePercentage() {
        return multiplePercentage;
    }

    public void setMultiplePercentage(Short multiplePercentage) {
        this.multiplePercentage = multiplePercentage;
    }

    @Override
    public String toString() {
        return "Coupon{" +
                "cid=" + cid +
                ", couponName='" + couponName + '\'' +
                ", couponBrief='" + couponBrief + '\'' +
                ", couponType=" + couponType +
                ", useMinRequire=" + useMinRequire +
                ", minusCount=" + minusCount +
                ", multiplePercentage=" + multiplePercentage +
                '}';
    }
}
