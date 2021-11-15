package com.virtualslime.Entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

enum AdminState{
    NORMAL, RESTRICTED, BANNED, LOGOFF, SUPREME
}

@TableName(schema = "virtual_slime", value = "admin")
public class Admin {
    @TableId(type = IdType.AUTO)
    private final Integer aid;
    private String adminName;
    private Byte adminState;

    public Admin(String adminName, Byte adminState) {
        this.aid = null;
        this.adminName = adminName;
        this.adminState = adminState;
    }

    public Integer getAid() {
        return aid;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public Byte getAdminState() {
        return adminState;
    }

    public void setAdminState(Byte adminState) {
        this.adminState = adminState;
    }
}
