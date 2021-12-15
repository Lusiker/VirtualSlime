package com.virtualSlime.Entity.Relation;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@TableName(schema = "virtual_slime",value = "r_user_cart_item")
public class UserCart {
    @TableId(type = IdType.AUTO)
    private Integer uid;
    private Integer iid;
}
