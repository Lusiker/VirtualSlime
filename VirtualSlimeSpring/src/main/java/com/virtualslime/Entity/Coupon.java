package com.virtualslime.Entity;

enum CouponType{
    NOT_DEFINED, MINUS, MULTIPLE
}

public class Coupon {
    private int cid;//user_coupon.cid unsigned smallint
    private String couponName;//user_coupon.name varchar(20)
    private String couponBrief;//user_coupon.brief varchar(50)
    private CouponType couponType;//user_coupon.type unsigned tinyint
    private int useMinRequire;//user_coupon.use_min_price unsigned int
    private double minusCount;//user_coupon.reduced_price decimal(12,2):parse needed
    private short multiplePercentage;//user_coupon.discount_percentage unsigned tinyint: [0,100]

    public Coupon() {
        this.cid = 0;
        this.couponName = "null";
        this.couponBrief = "null";
        this.couponType = CouponType.NOT_DEFINED;
        this.useMinRequire = 0;
        this.minusCount = 0;
        this.multiplePercentage = 0;
    }

    public Coupon(String couponName, String couponBrief, CouponType couponType,
                  int useMinRequire, double minusCount, short multiplePercentage) {
        this.couponName = couponName;
        this.couponBrief = couponBrief;
        this.couponType = couponType;
        this.useMinRequire = useMinRequire;
        this.minusCount = minusCount;
        this.multiplePercentage = multiplePercentage;
    }

    public Coupon(int cid, String couponName, String couponBrief, CouponType couponType,
                  int useMinRequire, double minusCount, short multiplePercentage) {
        this.cid = cid;
        this.couponName = couponName;
        this.couponBrief = couponBrief;
        this.couponType = couponType;
        this.useMinRequire = useMinRequire;
        this.minusCount = minusCount;
        this.multiplePercentage = multiplePercentage;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
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

    public int getUseMinRequire() {
        return useMinRequire;
    }

    public void setUseMinRequire(int useMinRequire) {
        this.useMinRequire = useMinRequire;
    }

    public double getMinusCount() {
        return minusCount;
    }

    public void setMinusCount(double minusCount) {
        this.minusCount = minusCount;
    }

    public short getMultiplePercentage() {
        return multiplePercentage;
    }

    public void setMultiplePercentage(short multiplePercentage) {
        this.multiplePercentage = multiplePercentage;
    }
}
