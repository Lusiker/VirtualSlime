package com.virtualSlime.Utils.InfoWrapper;

import com.virtualSlime.Entity.User;
import com.virtualSlime.Utils.DateProcessor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FollowerInfoWrapper {
    private Integer uid;
    private String userName;
    private String userIntro;
    private String lastLogin;

    public FollowerInfoWrapper(User user){
        this.uid = user.getUid();
        this.userName = user.getUserName();
        this.userIntro = user.getUserIntroduction();
        this.lastLogin = DateProcessor.getDateStringFromTimestamp(user.getLastLogin());
    }
}
