package com.virtualSlime.Entity;

public class Conversation {
    private int cid;//user_conversation.cid unsigned int
    private int uid1;//user_conversation.uid1 unsigned int
    private int uid2;//user_conversation.uid2 unsigned int
    private long lastUpdated;//user_conversation.last_updated timestamp

    public Conversation(int uid1, int uid2) {
        this.uid1 = uid1;
        this.uid2 = uid2;
        this.lastUpdated = 0;
    }

    public Conversation(int cid, int uid1, int uid2,
                        long lastUpdated) {
        this.cid = cid;
        this.uid1 = uid1;
        this.uid2 = uid2;
        this.lastUpdated = lastUpdated;
    }

    public int getCid() {
        return cid;
    }

    public int getUid1() {
        return uid1;
    }

    public void setUid1(int uid1) {
        this.uid1 = uid1;
    }

    public int getUid2() {
        return uid2;
    }

    public void setUid2(int uid2) {
        this.uid2 = uid2;
    }

    public long getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(long lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    @Override
    public String toString() {
        return "Conversation{" +
                "cid=" + cid +
                ", uid1=" + uid1 +
                ", uid2=" + uid2 +
                ", lastUpdated=" + lastUpdated +
                '}';
    }
}
