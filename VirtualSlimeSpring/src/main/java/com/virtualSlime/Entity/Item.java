package com.virtualSlime.Entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.virtualSlime.Enum.ItemState;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@TableName(schema = "virtual_slime", value = "item_info")
public class Item {
    @TableId(type = IdType.AUTO)
    private Integer iid;
    private Integer uid;
    private String itemName;//<= 50
    private String itemBrief;
    private BigDecimal itemPrice;//decimal(12,2):parse needed
    private Boolean isDiscounting;
    private BigDecimal itemPriceDiscounted;//decimal(12,2):parse needed
    private Short cid;
    private Integer visitCount;
    private ItemState itemState;
}
