package com.virtualSlime.Utils.InfoWrapper;

import com.virtualSlime.Entity.Comment;
import com.virtualSlime.Entity.Item;
import com.virtualSlime.Entity.User;
import com.virtualSlime.Utils.DateProcessor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class CommentInfoWrapper {
    private Integer cid;
    private Integer uid;
    private Integer iid;
    private String userName;
    private String itemName;
    private Date createdAt;
    private String createdAtString;
    private String content;
    private Byte rating;

    public CommentInfoWrapper(CommentInfoWrapperBuilder builder){
        this.cid = builder.getBCid();
        this.uid = builder.getBUid();
        this.iid = builder.getBIid();
        this.userName = builder.getBUserName();
        this.itemName = builder.getBItemName();
        this.createdAt = builder.getBCreatedAt();
        this.createdAtString = builder.getBCreatedAtString();
        this.content = builder.getBContent();
        this.rating = builder.getBRating();
    }

    @Getter
    @Setter
    public static class CommentInfoWrapperBuilder {
        private Integer bCid;
        private Integer bUid;
        private Integer bIid;
        private String bUserName;
        private String bItemName;
        private Date bCreatedAt;
        private String bCreatedAtString;
        private String bContent;
        private Byte bRating;

        public CommentInfoWrapperBuilder setComment(Comment comment){
            this.bCid = comment.getCid();
            this.bCreatedAt = comment.getCreatedAt();
            this.bCreatedAtString = DateProcessor.getDateStringFromTimestamp(this.bCreatedAt);
            this.bContent = comment.getCommentContent();
            this.bRating = comment.getCommentRating();

            return this;
        }

        public CommentInfoWrapperBuilder setUser(User user){
            if(user == null){
                this.bUid = 0;
                this.bUserName = "undefined";
            }else {
                this.bUid = user.getUid();
                this.bUserName = user.getUserName();
            }

            return this;
        }

        public CommentInfoWrapperBuilder setItem(Item item){
            this.bIid = item.getIid();
            this.bItemName = item.getItemName();

            return this;
        }

        public CommentInfoWrapper build() {
            return new CommentInfoWrapper(this);
        }
    }
}
