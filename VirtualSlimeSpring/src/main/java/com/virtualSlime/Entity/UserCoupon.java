package com.virtualSlime.Entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@TableName(schema = "virtual_slime", value = "r_user_have_coupon")
public class UserCoupon {
    private Integer uid;
    private Integer cid;
}
