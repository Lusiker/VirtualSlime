package com.virtualSlime.Utils;

import com.virtualSlime.Entity.Item;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
public class ItemInfoWrapper {
    private Integer iid;
    private Integer uid;
    private String itemName;//<= 50
    private String itemBrief;
    private BigDecimal itemPrice;//decimal(12,2):parse needed
    private Boolean isDiscounting;
    private BigDecimal itemPriceDiscounted;//decimal(12,2):parse needed
    private String categoryName;

    public ItemInfoWrapper(Item item,GlobalCategoryCache categoryCache){
        this.iid = item.getIid();
        this.uid = item.getUid();
        this.itemName = item.getItemName();
        this.itemBrief = item.getItemBrief();
        this.itemPrice = item.getItemPrice();
        this.isDiscounting = item.getIsDiscounting();
        if(this.isDiscounting){
            this.itemPriceDiscounted = item.getItemPriceDiscounted();
        }else{
            this.itemPriceDiscounted = null;
        }

        this.categoryName = categoryCache.getCategoryNameFromCid(item.getCid());
    }
}
