package com.virtualSlime.Utils;

import com.virtualSlime.Entity.Item;
import com.virtualSlime.Entity.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.parameters.P;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
public class ItemInfoWrapper {
    private Integer iid;
    private Integer uid;
    private String userName;
    private String itemName;
    private String itemBrief;
    private BigDecimal itemPrice;
    private Boolean isDiscounting;
    private BigDecimal itemPriceDiscounted;
    private String categoryName;

    public ItemInfoWrapper(Item item,User user, GlobalCategoryCache categoryCache){
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

        if(user == null){
            this.userName = "undefined";
        }else {
            this.userName = user.getUserName();
        }
    }
}
