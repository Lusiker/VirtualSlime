package com.virtualSlime.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.virtualSlime.Entity.User;
import com.virtualSlime.Enum.UserSex;
import com.virtualSlime.Enum.UserState;
import com.virtualSlime.Mapper.IntegerMapper;
import com.virtualSlime.Mapper.UserMapper;
import com.virtualSlime.Utils.IntegerWrapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class UserRepository {
    @Resource
    UserMapper userMapper;
    @Resource
    IntegerMapper integerMapper;

    //basic insert
    public boolean insertUser(User user){
        return userMapper.insert(user) == 1;
    }

    //basic update
    public boolean updateUser(User user){
        UpdateWrapper<User> wrapper = new UpdateWrapper<User>();
        wrapper.eq("uid",user.getUid());

        return userMapper.update(user, wrapper) == 1;
    }

    //select queries
    public User selectUserByEmail(String userEmail){
        //select a user by given email
        QueryWrapper<User> wrapper = new QueryWrapper<User>().eq("user_email", userEmail);
        return userMapper.selectOne(wrapper);
    }

    public User selectUserByUid(int uid){
        //select a user by given uid
        QueryWrapper<User> wrapper = new QueryWrapper<User>().eq("uid", uid);
        return userMapper.selectOne(wrapper);
    }

    public User selectUserByUserName(String userName){
        //select a user by given username
        QueryWrapper<User> wrapper = new QueryWrapper<User>().eq("user_name", userName);
        return userMapper.selectOne(wrapper);
    }

    public int selectUserCouponCount(int uid){
        QueryWrapper<IntegerWrapper> wrapper = new QueryWrapper<IntegerWrapper>().inSql("count",
                "select count from virtual_slime.r_user_have_coupon where uid = " + uid);

        List<IntegerWrapper> list = integerMapper.selectList(wrapper);
        int couponCount = 0;
        for(IntegerWrapper i : list){
            couponCount += i.getValue();
        }

        return couponCount;
    }

    public int selectUserFollowerCount(int uid){
        QueryWrapper<User> wrapper = new QueryWrapper<User>().inSql("uid",
                "select uid_from from virtual_slime.r_user_follow where uid_to = " + uid);

        List<User> list = userMapper.selectList(wrapper);
        int followerCount = 0;
        followerCount += list.size();

        return followerCount;
    }

    public int selectUserFollowingCount(int uid){
        QueryWrapper<User> wrapper = new QueryWrapper<User>().inSql("uid",
                "select uid_to from virtual_slime.r_user_follow where uid_from = " + uid);

        List<User> list = userMapper.selectList(wrapper);
        int followingCount = 0;
        followingCount += list.size();

        return followingCount;
    }

    //update queries
    public boolean updateUserLogin(User user, Date now){
        //update user's lastLogin and totalLogin
        UpdateWrapper<User> wrapper = new UpdateWrapper<User>();
        wrapper.eq("uid",user.getUid());

        user.setLastLogin(new Timestamp(now.getTime()));

        Integer userTotalLogin = user.getTotalLogin();
        if(userTotalLogin == null){
            user.setTotalLogin(1);
        }else{
            user.setTotalLogin(userTotalLogin + 1);
        }

        return userMapper.update(user, wrapper) == 1;
    }

    public boolean updateUserName(User user, String newName){
        //update user's name
        //make sure that user's new name has not been used
        User nameDuplicated = selectUserByUserName(newName);
        if(nameDuplicated != null){
            return false;
        }

        user.setUserName(newName);

        return updateUser(user);
    }

    public boolean updateUserSex(User user, UserSex newSex){
        //update user's sex
        user.setUserSex(newSex);

        return updateUser(user);
    }

    public boolean updateUserBirthday(User user,Date birthday){
        //update user's birthday
        java.sql.Date d = new java.sql.Date(birthday.getTime());
        user.setUserBirthday(d);

        return updateUser(user);
    }

    public boolean updateUserIntroduction(User user,String newIntro){
        //update user's introduction
        user.setUserIntroduction(newIntro);

        return updateUser(user);
    }

    public boolean updateUserShowBirthday(User user,boolean showBirthday){
        //update user's show birthday
        user.setUserShowBirthday(showBirthday);

        return updateUser(user);
    }

    public boolean updateUserShowDynamic(User user,boolean showDynamic){
        //update user's show birthday
        user.setUserShowDynamic(showDynamic);

        return updateUser(user);
    }

    public boolean updateUserHasActivatedTrue(User user){
        //set user's hasActivated to true and state to NORMAL
        user.setUserHasActivated(true);
        user.setUserState(UserState.NORMAL);

        return updateUser(user);
    }

    public boolean updateUserResetEmail(User user, String newEmail){
        //set user's email to new email, state to restricted and hasActivated to false
        user.setUserEmail(newEmail);
        user.setUserHasActivated(false);
        user.setUserState(UserState.RESTRICTED);

        return updateUser(user);
    }

    public boolean deleteUser(User user){
        //set's user's state to UserState.LOGOFF
        user.setUserState(UserState.LOGOFF);

        return updateUser(user);
    }
}
