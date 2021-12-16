package com.virtualSlime.Utils.InfoWrapper;

import com.virtualSlime.Entity.User;
import com.virtualSlime.Enum.EntityType.UserSex;
import com.virtualSlime.Enum.UserState;
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
    private String userName;//<= 20
    private String userEmail;//<= 50
    private String userIntroduction;//<= 50
    private Timestamp lastLogin;
    private Date userBirthday;
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
    private Integer CouponCount;

    public UserInfoWrapper(User user,int type) {
        this.userName = user.getUserName();
        this.userEmail = user.getUserEmail();
        this.userIntroduction = user.getUserIntroduction();
        this.userSex = user.getUserSex();
        this.userState = user.getUserState();
        this.lastLogin = user.getLastLogin();
        this.userIsMerchant = user.getUserIsMerchant();
        this.userShowDynamic = user.getUserShowDynamic();
        this.userShowBirthday = user.getUserShowBirthday();

        if(type == 0){
            //as master
            this.userHasActivated = user.getUserHasActivated();
            this.userCurrency = user.getUserCurrency();
            this.userPoint = user.getUserPoint();
            this.userBirthday = user.getUserBirthday();
        }else if(type == 1){
            //as visitor
            if(user.getUserShowBirthday()){
                this.userBirthday = user.getUserBirthday();
            }else{
                this.userBirthday = null;
            }
            this.userHasActivated = null;
            this.userCurrency = null;
            this.userPoint = null;
        }
    }
}
