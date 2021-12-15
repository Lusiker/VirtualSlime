package com.virtualSlime.Entity.Relation;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@TableName(schema = "virtual_slime", value = "r_user_have_coupon")
public class UserCoupon {
    @TableId(type = IdType.AUTO)
    private Integer uid;
    private Integer cid;
}
