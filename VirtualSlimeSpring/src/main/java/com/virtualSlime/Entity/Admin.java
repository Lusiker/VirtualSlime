package com.virtualSlime.Entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.virtualSlime.Enum.AdminState;

@TableName(schema = "virtual_slime", value = "admin")
public class Admin {
    @TableId(type = IdType.AUTO)
    private final Integer aid;
    private String adminName;
    private AdminState adminState;

    public Admin(Integer aid, String adminName, AdminState adminState) {
        this.aid = aid;
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

    public AdminState getAdminState() {
        return adminState;
    }

    public void setAdminState(AdminState adminState) {
        this.adminState = adminState;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "aid=" + aid +
                ", adminName='" + adminName + '\'' +
                ", adminState=" + adminState +
                '}';
    }
}
