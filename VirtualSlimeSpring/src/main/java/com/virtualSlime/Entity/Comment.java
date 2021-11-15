package com.virtualSlime.Entity;

import com.virtualSlime.Enum.CommentState;
import com.virtualSlime.Enum.CommentType;

public class Comment {
    private Integer cid;
    private Integer uid;
    private Integer iid;
    private Long createdAt;
    private Integer uidReply;
    private CommentType commentType;
    private String commentContent;
    private Byte commentRating;
    private CommentState commentState;

    public Comment(Integer uid, Integer iid, Integer uidReply, CommentType commentType,
                   String commentContent, Byte commentRating) {
        this.uid = uid;
        this.iid = iid;
        this.uidReply = uidReply;
        this.commentType = commentType;
        this.commentContent = commentContent;
        this.commentRating = commentRating;
        this.commentState = CommentState.NORMAL;
    }

    public Comment(Integer cid, Integer uid, Integer iid, Long createdAt,
                   Integer uidReply, CommentType commentType, String commentContent,
                   Byte commentRating, CommentState commentState) {
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

    public Integer getCid() {
        return cid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getIid() {
        return iid;
    }

    public void setIid(Integer iid) {
        this.iid = iid;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getUidReply() {
        return uidReply;
    }

    public void setUidReply(Integer uidReply) {
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

    public Byte getCommentRating() {
        return commentRating;
    }

    public void setCommentRating(Byte commentRating) {
        this.commentRating = commentRating;
    }

    public CommentState getCommentState() {
        return commentState;
    }

    public void setCommentState(CommentState commentState) {
        this.commentState = commentState;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "cid=" + cid +
                ", uid=" + uid +
                ", iid=" + iid +
                ", createdAt=" + createdAt +
                ", uidReply=" + uidReply +
                ", commentType=" + commentType +
                ", commentContent='" + commentContent + '\'' +
                ", commentRating=" + commentRating +
                ", commentState=" + commentState +
                '}';
    }
}
