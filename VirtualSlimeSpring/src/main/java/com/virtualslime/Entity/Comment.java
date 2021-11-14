package com.virtualslime.Entity;

import java.util.Date;

enum CommentType{
    UNDEFINED, RATING, REPLY
}

enum CommentState{
    NORMAL, RESTRICTED, HIDDEN
}

public class Comment {
    private int cid;//user_comment.cid unsigned int
    private int uid;//user_comment.uid unsigned int
    private int iid;//user_comment.iid unsigned int
    private long createdAt;//user_comment.created_at timestamp
    private int uidReply;//user_comment.reply_uid unsigned int
    private CommentType commentType;//user_comment.type unsigned tinyint
    private String commentContent;//user_comment.content varchar(200)
    private short commentRating;//user_comment.rate unsigned tinyint
    private CommentState commentState;//user_comment.state unsigned tinyint

    public Comment() {
        this.cid = 0;
        this.uid = 0;
        this.iid = 0;
        this.createdAt = new Date().getTime();
        this.uidReply = 0;
        this.commentType = CommentType.UNDEFINED;
        this.commentContent = "null";
        this.commentRating = 0;
        this.commentState = CommentState.NORMAL;
    }

    public Comment(int uid, int iid, long createdAt, int uidReply, CommentType commentType,
                   String commentContent, short commentRating) {
        this.uid = uid;
        this.iid = iid;
        this.createdAt = createdAt;
        this.uidReply = uidReply;
        this.commentType = commentType;
        this.commentContent = commentContent;
        this.commentRating = commentRating;
    }

    public Comment(int cid, int uid, int iid, long createdAt, int uidReply, CommentType commentType,
                   String commentContent, short commentRating, CommentState commentState) {
        this.cid = cid;
        this.uid = uid;
        this.iid = iid;
        this.createdAt = createdAt;
        this.uidReply = uidReply;
        this.commentType = commentType;
        this.commentContent = commentContent;
        this.commentRating = commentRating;
        this.commentState = commentState;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getIid() {
        return iid;
    }

    public void setIid(int iid) {
        this.iid = iid;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public int getUidReply() {
        return uidReply;
    }

    public void setUidReply(int uidReply) {
        this.uidReply = uidReply;
    }

    public CommentType getCommentType() {
        return commentType;
    }

    public void setCommentType(CommentType commentType) {
        this.commentType = commentType;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public short getCommentRating() {
        return commentRating;
    }

    public void setCommentRating(short commentRating) {
        this.commentRating = commentRating;
    }

    public CommentState getCommentState() {
        return commentState;
    }

    public void setCommentState(CommentState commentState) {
        this.commentState = commentState;
    }
}
