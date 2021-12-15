package com.virtualSlime.Entity.Relation;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
@TableName(schema = "virtual_slime",value = "r_user_bought_item")
public class UserBought {
    @TableId(type = IdType.AUTO)
    private Integer uid;
    private Integer iid;
    private Date createdTime;
}
