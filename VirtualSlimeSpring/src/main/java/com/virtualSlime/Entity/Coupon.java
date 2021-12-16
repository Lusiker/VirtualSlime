package com.virtualSlime.Entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.virtualSlime.Enum.EntityType.CouponType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@TableName(schema = "virtual_slime", value = "user_coupon")
public class Coupon {
    @TableId(type = IdType.AUTO)
    private Integer cid;//smallint
    private String couponName;//<= 20
    private String couponBrief;//<= 50
    private CouponType couponType;
    private Integer useMinRequire;
    private Double minusCount;
    private Short multiplePercentage;//[0,100]
}
