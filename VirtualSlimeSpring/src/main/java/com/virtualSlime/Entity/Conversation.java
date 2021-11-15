package com.virtualSlime.Entity;

public class Conversation {
    private Integer cid;
    private Integer uid1;
    private Integer uid2;
    private Long lastUpdated;

    public Conversation(Integer uid1, Integer uid2) {
        this.uid1 = uid1;
        this.uid2 = uid2;
        this.lastUpdated = 0L;
    }

    public Conversation(Integer cid, Integer uid1, Integer uid2,
                        Long lastUpdated) {
        this.cid = cid;
        this.uid1 = uid1;
        this.uid2 = uid2;
        this.lastUpdated = lastUpdated;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getUid1() {
        return uid1;
    }

    public void setUid1(Integer uid1) {
        this.uid1 = uid1;
    }

    public Integer getUid2() {
        return uid2;
    }

    public void setUid2(Integer uid2) {
        this.uid2 = uid2;
    }

    public Long getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Long lastUpdated) {
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
