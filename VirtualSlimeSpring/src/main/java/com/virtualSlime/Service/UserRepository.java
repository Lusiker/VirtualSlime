package com.virtualSlime.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.virtualSlime.Entity.User;
import com.virtualSlime.Enum.UserSex;
import com.virtualSlime.Enum.UserState;
import com.virtualSlime.Mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class UserRepository {
    @Resource
    UserMapper userMapper;

    public boolean insertUser(User user){
        return userMapper.insert(user) == 1;
    }

    public boolean updateUser(User user){
        UpdateWrapper<User> wrapper = new UpdateWrapper<User>();
        wrapper.eq("uid",user.getUid());

        return userMapper.update(user, wrapper) == 1;
    }

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
