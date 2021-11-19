package com.virtualSlime.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.virtualSlime.Entity.User;
import com.virtualSlime.Enum.UserState;
import com.virtualSlime.Mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.Date;

@Service
public class UserRepository {
    @Resource
    UserMapper userMapper;

    public User selectUserByEmail(String userEmail){
        //select a user by given email
        QueryWrapper<User> wrapper = new QueryWrapper<User>().eq("user_email", userEmail);
        return userMapper.selectOne(wrapper);
    }

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

    public boolean updateUserHasActivatedTrue(User user){
        //set user's hasActivated to true and state to NORMAL
        UpdateWrapper<User> wrapper = new UpdateWrapper<User>();
        wrapper.eq("uid",user.getUid());

        user.setUserHasActivated(true);
        user.setUserState(UserState.NORMAL);

        return userMapper.update(user, wrapper) == 1;
    }

    public boolean updateUserResetEmail(User user, String newEmail){
        //set user's email to new email, state to restricted and hasActivated to false
        UpdateWrapper<User> wrapper = new UpdateWrapper<User>();
        wrapper.eq("uid",user.getUid());

        user.setUserEmail(newEmail);
        user.setUserHasActivated(false);
        user.setUserState(UserState.RESTRICTED);

        return userMapper.update(user, wrapper) == 1;
    }
}
