package com.virtualslime.Entity;

import java.util.Date;
import com.virtualslime.Utils.DateProcessing;

enum UserState{
    NORMAL, RESTRICTED, BANNED, LOGOFF
}

enum UserSex{
    UNDEFINED(0), MALE(1), FEMALE(2), SECRET(3);

    private int sexCode;

    UserSex(int newSexCode){
        sexCode = newSexCode;
    }

    public int getSexCode() {
        return sexCode;
    }

    public void setSexCode(int sexCode) {
        this.sexCode = sexCode;
    }
}

public class User {
    private int uid;//user_info.uid unsigned int
    private String userName;//user_info.name varchar(20)
    private String userEmail;//user_info.email varchar(20)
    private String userPassword;//user_info.password  tinytext (<= 255)
    private long createdAt;//user_info.created_at timestamp
    private long lastLogin;//user_info.last_login timestamp
    private short totalLogin;//user_info.total_login unsigned smallint
    private Date userBirthday;//user_info.birthday date
    private UserSex userSex;//user_info.sex unsigned tinyint
    private boolean userShowBirthday;//user_info.show_birthday boolean
    private boolean userShowLike;//user_info.show_like boolean
    private boolean userShowComment;//user_info.show_comment boolean
    private boolean userIsMerchant;//user_info.is_merchant boolean
    private UserState userState;//user_info.state unsigned tinyint

    public User() {
        this.uid = 0;
        this.userName = "null";
        this.userEmail = "null";
        this.userPassword = "null";
        this.createdAt = new Date().getTime();
        this.lastLogin = -1;
        this.totalLogin = 0;
        this.userBirthday = DateProcessing.getInitialTime();
        this.userSex = UserSex.UNDEFINED;
        this.userShowBirthday = false;
        this.userShowLike = false;
        this.userShowComment = false;
        this.userIsMerchant = false;
        this.userState = UserState.NORMAL;
    }

    public User(String userName, String userEmail, String userPassword, long createdAt, Date userBirthday,
                UserSex userSex, boolean userShowBirthday, boolean userShowLike, boolean userShowComment,
                boolean userIsMerchant) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.createdAt = createdAt;
        this.userBirthday = userBirthday;
        this.userSex = userSex;
        this.userShowBirthday = userShowBirthday;
        this.userShowLike = userShowLike;
        this.userShowComment = userShowComment;
        this.userIsMerchant = userIsMerchant;
        this.userState = UserState.NORMAL;
    }

    public User(int uid, String userName, String userEmail, String userPassword, long createdAt, long lastLogin,
                short totalLogin, Date userBirthday, UserSex userSex, boolean userShowBirthday, boolean userShowLike,
                boolean userShowComment, boolean userIsMerchant, UserState userState) {
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
        this.userShowLike = userShowLike;
        this.userShowComment = userShowComment;
        this.userIsMerchant = userIsMerchant;
        this.userState = userState;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
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

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public long getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(long lastLogin) {
        this.lastLogin = lastLogin;
    }

    public short getTotalLogin() {
        return totalLogin;
    }

    public void setTotalLogin(short totalLogin) {
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

    public boolean isUserShowBirthday() {
        return userShowBirthday;
    }

    public void setUserShowBirthday(boolean userShowBirthday) {
        this.userShowBirthday = userShowBirthday;
    }

    public boolean isUserShowLike() {
        return userShowLike;
    }

    public void setUserShowLike(boolean userShowLike) {
        this.userShowLike = userShowLike;
    }

    public boolean isUserShowComment() {
        return userShowComment;
    }

    public void setUserShowComment(boolean userShowComment) {
        this.userShowComment = userShowComment;
    }

    public boolean isUserIsMerchant() {
        return userIsMerchant;
    }

    public void setUserIsMerchant(boolean userIsMerchant) {
        this.userIsMerchant = userIsMerchant;
    }

    public UserState getUserState() {
        return userState;
    }

    public void setUserState(UserState userState) {
        this.userState = userState;
    }

}
