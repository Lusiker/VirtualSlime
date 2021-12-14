package com.virtualSlime.Entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@TableName(schema = "virtual_slime", value = "item_category")
public class Category {
    @TableId(type = IdType.AUTO)
    private Short cid;
    private Short belongsTo;
    private String name;//<= 20
    private String brief;//<= 50
}
