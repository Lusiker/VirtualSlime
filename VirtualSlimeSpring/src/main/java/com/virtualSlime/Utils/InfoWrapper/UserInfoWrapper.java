package com.virtualSlime.Utils.InfoWrapper;

import com.virtualSlime.Entity.User;
import com.virtualSlime.Enum.EntityType.UserSex;
import com.virtualSlime.Enum.UserState;
import com.virtualSlime.Utils.DateProcessor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

@Getter
@Setter
@ToString
public class UserInfoWrapper {
    private Integer uid;
    private String userName;//<= 20
    private String userEmail;//<= 50
    private String userIntroduction;//<= 50
    private Timestamp lastLogin;
    private String lastLoginString;
    private Date userBirthday;
    private String userBirthdayString;
    private UserSex userSex;
    private Boolean userShowBirthday;
    private Boolean userShowDynamic;
    private Boolean userIsMerchant;
    private Boolean userHasActivated;
    private UserState userState;
    private Integer userPoint;
    private BigDecimal userCurrency;
    private Integer followerCount;
    private Integer followingCount;
    private Integer couponCount;
    private Boolean hasFollowed;

    public UserInfoWrapper(UserInfoWrapperBuilder builder){
        this.uid = builder.getBUid();
        this.userName = builder.getBUserName();
        this.userEmail = builder.getBUserEmail();
        this.userIntroduction = builder.getBUserIntroduction();
        this.lastLogin = builder.getBLastLogin();
        this.lastLoginString = builder.getBLastLoginString();
        this.userBirthday = builder.getBUserBirthday();
        this.userBirthdayString = builder.getBUserBirthdayString();
        this.userSex = builder.getBUserSex();
        this.userShowBirthday = builder.getBUserShowBirthday();
        this.userShowDynamic = builder.getBUserShowDynamic();
        this.userIsMerchant = builder.getBUserIsMerchant();
        this.userHasActivated = builder.getBUserHasActivated();
        this.userState = builder.getBUserState();
        this.userPoint = builder.getBUserPoint();
        this.userCurrency = builder.getBUserCurrency();
        this.followerCount = builder.getBFollowerCount();
        this.followingCount = builder.getBFollowingCount();
        this.couponCount = builder.getBCouponCount();
        this.hasFollowed = builder.getBHasFollowed();
    }

    @Getter
    @Setter
    public static class UserInfoWrapperBuilder{
        private Integer bUid;
        private String bUserName;//<= 20
        private String bUserEmail;//<= 50
        private String bUserIntroduction;//<= 50
        private Timestamp bLastLogin;
        private String bLastLoginString;
        private Date bUserBirthday;
        private String bUserBirthdayString;
        private UserSex bUserSex;
        private Boolean bUserShowBirthday;
        private Boolean bUserShowDynamic;
        private Boolean bUserIsMerchant;
        private Boolean bUserHasActivated;
        private UserState bUserState;
        private Integer bUserPoint;
        private BigDecimal bUserCurrency;
        private Integer bFollowerCount;
        private Integer bFollowingCount;
        private Integer bCouponCount;
        private Boolean bHasFollowed;

        public UserInfoWrapperBuilder setUserCommonInfo(User user){
            this.bUserName = user.getUserName();
            this.bUserEmail = user.getUserEmail();
            this.bUserIntroduction = user.getUserIntroduction();
            this.bUserSex = user.getUserSex();
            this.bUserState = user.getUserState();
            this.bLastLogin = user.getLastLogin();
            this.bLastLoginString = DateProcessor.getDateStringFromTimestamp(this.bLastLogin);
            this.bUserIsMerchant = user.getUserIsMerchant();
            this.bUserShowDynamic = user.getUserShowDynamic();
            this.bUserShowBirthday = user.getUserShowBirthday();

            return this;
        }

        public UserInfoWrapperBuilder setLesserInfo(User user){
            this.bUid = user.getUid();
            this.bUserName = user.getUserName();
            this.bUserIntroduction = user.getUserIntroduction();
            this.bLastLogin = user.getLastLogin();
            this.bLastLoginString = DateProcessor.getDateStringFromTimestamp(this.bLastLogin);

            return this;
        }

        public UserInfoWrapperBuilder setUserInfoAsMaster(User user){
            this.bUserHasActivated = user.getUserHasActivated();
            this.bUserCurrency = user.getUserCurrency();
            this.bUserPoint = user.getUserPoint();
            this.bUserBirthday = user.getUserBirthday();
            this.bUserBirthdayString = DateProcessor.getDateStringFromTimestamp(this.bUserBirthday);

            return this;
        }

        public UserInfoWrapperBuilder setUserInfoAsGuest(User user){
            if(this.bUserShowBirthday){
                this.bUserBirthday = user.getUserBirthday();
                this.bUserBirthdayString = DateProcessor.getDateStringFromTimestamp(this.bUserBirthday);
            }

            return this;
        }

        public UserInfoWrapperBuilder setUserFollowerInfo(int followerCount){
            this.bFollowerCount = followerCount;

            return this;
        }

        public UserInfoWrapperBuilder setUserFollowingInfo(int followingCount){
            this.bFollowingCount = followingCount;

            return this;
        }

        public UserInfoWrapperBuilder setUserCouponInfo(int couponCount){
            this.bCouponCount = couponCount;

            return this;
        }

        public UserInfoWrapperBuilder setUserHasFollowed(boolean state){
            this.bHasFollowed = state;

            return this;
        }

        public UserInfoWrapper build(){
            return new UserInfoWrapper(this);
        }
    }
}
