package com.virtualSlime.Entity;

import java.sql.Date;
import java.sql.Timestamp;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.virtualSlime.Enum.UserSex;
import com.virtualSlime.Enum.UserState;

@TableName(schema = "virtual_slime", value = "user_info")
public class User {
    @TableId(type = IdType.AUTO)
    private final Integer uid;
    private String userName;//<= 20
    private String userEmail;//<= 50
    private String userPassword;
    private Timestamp createdAt;
    private Timestamp lastLogin;
    private Integer totalLogin;
    private Date userBirthday;
    private UserSex userSex;
    private Boolean userShowBirthday;
    private Boolean userShowDynamic;
    private Boolean userIsMerchant;
    private UserState userState;
    private Integer userPoint;
    private Integer userCurrency;

    public User(String userName, String userEmail, String userPassword) {
        this.uid = null;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
    }

    public User(Integer uid, String userName, String userEmail, String userPassword, Timestamp createdAt, Timestamp lastLogin, Integer totalLogin, Date userBirthday, UserSex userSex, Boolean userShowBirthday, Boolean userShowDynamic, Boolean userIsMerchant, UserState userState, Integer userPoint, Integer userCurrency) {
        this.uid = uid;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.createdAt = createdAt;
        this.lastLogin = lastLogin;
        this.totalLogin = totalLogin;
        this.userBirthday = userBirthday;
        this.userSex = userSex;
        this.userShowBirthday = userShowBirthday;
        this.userShowDynamic = userShowDynamic;
        this.userIsMerchant = userIsMerchant;
        this.userState = userState;
        this.userPoint = userPoint;
        this.userCurrency = userCurrency;
    }

    public Integer getUid() {
        return uid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Timestamp lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Integer getTotalLogin() {
        return totalLogin;
    }

    public void setTotalLogin(Integer totalLogin) {
        this.totalLogin = totalLogin;
    }

    public Date getUserBirthday() {
        return userBirthday;
    }

    public void setUserBirthday(Date userBirthday) {
        this.userBirthday = userBirthday;
    }

    public UserSex getUserSex() {
        return userSex;
    }

    public void setUserSex(UserSex userSex) {
        this.userSex = userSex;
    }

    public Boolean getUserShowBirthday() {
        return userShowBirthday;
    }

    public void setUserShowBirthday(Boolean userShowBirthday) {
        this.userShowBirthday = userShowBirthday;
    }

    public Boolean getUserShowDynamic() {
        return userShowDynamic;
    }

    public void setUserShowDynamic(Boolean userShowDynamic) {
        this.userShowDynamic = userShowDynamic;
    }

    public Boolean getUserIsMerchant() {
        return userIsMerchant;
    }

    public void setUserIsMerchant(Boolean userIsMerchant) {
        this.userIsMerchant = userIsMerchant;
    }

    public UserState getUserState() {
        return userState;
    }

    public void setUserState(UserState userState) {
        this.userState = userState;
    }

    public Integer getUserPoint() {
        return userPoint;
    }

    public void setUserPoint(Integer userPoint) {
        this.userPoint = userPoint;
    }

    public Integer getUserCurrency() {
        return userCurrency;
    }

    public void setUserCurrency(Integer userCurrency) {
        this.userCurrency = userCurrency;
    }
}
