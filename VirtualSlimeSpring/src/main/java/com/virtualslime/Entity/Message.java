package com.virtualslime.Entity;

enum MessageType{
    UNDEFINED, TEXT, PICTURE
}

enum MessageState{
    UNDEFINED, SUCCESS, FAILED, HIDDEN
}

public class Message {
    private int mid;//user_message.mid unsigned int
    private int cid;//user_message.cid unsigned int
    private int uid;//user_message.uid unsigned int
    private MessageType messageType;//user_message.type unsigned tinyint
    private String messageContent;//user_message.content varchar(200)
    private long createdAt;//user_message.created_at timestamp
    private MessageState messageState;//user_message.state unsigned tinyint

    public Message() {
        this.mid = 0;
        this.cid = 0;
        this.uid = 0;
        this.messageType = MessageType.UNDEFINED;
        this.messageContent = "null";
        this.messageState = MessageState.UNDEFINED;
    }

    public Message(int cid, int uid, MessageType messageType, String messageContent,
                   long createdAt, MessageState messageState) {
        this.cid = cid;
        this.uid = uid;
        this.messageType = messageType;
        this.messageContent = messageContent;
        this.createdAt = createdAt;
        this.messageState = messageState;
    }

    public Message(int mid, int cid, int uid, MessageType messageType,
                   String messageContent, long createdAt, MessageState messageState) {
        this.mid = mid;
        this.cid = cid;
        this.uid = uid;
        this.messageType = messageType;
        this.messageContent = messageContent;
        this.createdAt = createdAt;
        this.messageState = messageState;
    }

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
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

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public MessageState getMessageState() {
        return messageState;
    }

    public void setMessageState(MessageState messageState) {
        this.messageState = messageState;
    }
}
