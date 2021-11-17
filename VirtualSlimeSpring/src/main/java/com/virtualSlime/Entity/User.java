package com.virtualSlime.Entity;

import java.sql.Date;
import java.sql.Timestamp;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.virtualSlime.Enum.UserSex;
import com.virtualSlime.Enum.UserState;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@TableName(schema = "virtual_slime", value = "user_info")
public class User {
    @TableId(type = IdType.AUTO)
    private Integer uid;
    private String userName;//<= 20
    private String userEmail;//<= 50
    private String userIntroduction;//<= 255
    private String userPassword;
    private Timestamp createdAt;
    private Timestamp lastLogin;
    private Integer totalLogin;
    private Date userBirthday;
    private UserSex userSex;
    private Boolean userShowBirthday;
    private Boolean userShowDynamic;
    private Boolean userIsMerchant;
    private Boolean userHasActivated;
    private UserState userState;
    private Integer userPoint;
    private Double userCurrency;

    public User(String userName, String userEmail, String userPassword) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.userIntroduction = "undefined";
        this.userPassword = userPassword;
        this.userState = UserState.RESTRICTED;
    }
}
