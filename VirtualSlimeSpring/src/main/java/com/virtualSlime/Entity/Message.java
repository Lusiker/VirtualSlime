package com.virtualSlime.Entity;

import com.virtualSlime.Enum.MessageState;
import com.virtualSlime.Enum.MessageType;

public class Message {
    private Integer mid;
    private Integer cid;
    private Integer uid;
    private MessageType messageType;
    private String messageContent;//<= 200
    private Long createdAt;
    private MessageState messageState;

    public Message(Integer cid, Integer uid, MessageType messageType, String messageContent) {
        this.cid = cid;
        this.uid = uid;
        this.messageType = messageType;
        this.messageContent = messageContent;
    }

    public Message(Integer mid, Integer cid, Integer uid, MessageType messageType,
                   String messageContent, Long createdAt, MessageState messageState) {
        this.mid = mid;
        this.cid = cid;
        this.uid = uid;
        this.messageType = messageType;
        this.messageContent = messageContent;
        this.createdAt = createdAt;
        this.messageState = messageState;
    }

    public Integer getMid() {
        return mid;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    public MessageState getMessageState() {
        return messageState;
    }

    public void setMessageState(MessageState messageState) {
        this.messageState = messageState;
    }
}
