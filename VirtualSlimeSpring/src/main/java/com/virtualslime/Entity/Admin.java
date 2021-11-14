package com.virtualslime.Entity;

enum AdminState{
    NORMAL, RESTRICTED, BANNED, LOGOFF, SUPREME
}

public class Admin {
    private int aid;//admin.aid unsigned int
    private String adminName;//admin.name  varchar(20)
    private AdminState adminState;//admin.state unsigned tinyint

    public Admin() {
        this.aid = 0;
        this.adminName = "null";
        this.adminState = AdminState.NORMAL;
    }

    public Admin(String adminName) {
        this.adminName = adminName;
        this.adminState = AdminState.NORMAL;
    }

    public Admin(int aid, String adminName, AdminState adminState) {
        this.aid = aid;
        this.adminName = adminName;
        this.adminState = adminState;
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
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
}
