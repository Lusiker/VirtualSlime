package com.virtualSlime.Utils.InfoWrapper;

import com.virtualSlime.Entity.Comment;
import com.virtualSlime.Entity.Item;
import com.virtualSlime.Entity.Relation.UserBought;
import com.virtualSlime.Entity.User;
import com.virtualSlime.Utils.DateProcessor;
import com.virtualSlime.Utils.GlobalCategoryCache;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;


/**
 * ItemInfoWrapper
 * Difference between Item
 *  - category id will be replaced with category name
 *  - username added
 *  - for most of the time bought time is null, in /user/n/bought it will be replaced by
 *      the bought time in String
 *
 * use Builder to build a wrapper!
 */
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
    private String boughtTime;
    private List<Comment> comments;
    private Double rating;
    private String ratingSting;

    public ItemInfoWrapper(ItemInfoWrapperBuilder builder){
        this.iid = builder.getBIid();
        this.uid = builder.getBIid();
        this.userName = builder.getBUserName();
        this.itemName = builder.getBItemName();
        this.itemBrief = builder.getBItemBrief();
        this.itemPrice = builder.getBItemPrice();
        this.isDiscounting = builder.getBIsDiscounting();
        this.itemPriceDiscounted = builder.getBItemPriceDiscounted();
        this.categoryName = builder.getBCategoryName();
        this.boughtTime = builder.getBBoughtTime();
        this.comments = builder.getBComments();
        this.rating = builder.getBRating();
        this.ratingSting = builder.getBRatingString();
    }

    @Getter
    @Setter
    public static class ItemInfoWrapperBuilder{
        private Integer bIid;
        private Integer bUid;
        private String bUserName;
        private String bItemName;
        private String bItemBrief;
        private BigDecimal bItemPrice;
        private Boolean bIsDiscounting;
        private BigDecimal bItemPriceDiscounted;
        private String bCategoryName;
        private String bBoughtTime;
        private List<Comment> bComments;
        private Double bRating;
        private String bRatingString;

        public ItemInfoWrapperBuilder setItem(Item item,GlobalCategoryCache categoryCache){
            this.bIid = item.getIid();
            this.bItemName = item.getItemName();
            this.bItemBrief = item.getItemBrief();
            this.bItemPrice = item.getItemPrice();
            this.bIsDiscounting = item.getIsDiscounting();
            if(this.bIsDiscounting){
                this.bItemPriceDiscounted = item.getItemPriceDiscounted();
            }

            this.bCategoryName = categoryCache.getCategoryNameFromCid(item.getCid());

            return this;
        }

        public ItemInfoWrapperBuilder setSeller(User user){
            this.bUid = user.getUid();
            this.bUserName = user.getUserName();

            return this;
        }

        public ItemInfoWrapperBuilder setBoughtTime(UserBought record){
            this.bBoughtTime = DateProcessor.getDateStringFromTimestamp(record.getCreatedTime());

            return this;
        }

        public ItemInfoWrapperBuilder setCommentList(List<Comment> list){
            this.bComments = list;

            return this;
        }

        public ItemInfoWrapperBuilder setRating(){
            if(this.bComments != null){
                double rating = 0.0;

                for (Comment c : this.bComments){
                    rating += c.getCommentRating();
                }

                this.bRating = rating / this.bComments.size();
            }else{
                this.bRating = 0.0;
            }

            return this;
        }

        public ItemInfoWrapperBuilder setRatingString(){
            if(this.bRating != null){
                this.bRatingString = String.format("%.2f",this.bRating);
            }else{
                this.bRatingString = "N/A";
            }

            return this;
        }

        public ItemInfoWrapper build(){
            return new ItemInfoWrapper(this);
        }
    }
}
