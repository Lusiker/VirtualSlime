package com.virtualSlime.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.virtualSlime.Entity.Item;
import com.virtualSlime.Entity.Relation.UserFollow;
import com.virtualSlime.Entity.User;
import com.virtualSlime.Entity.Relation.UserCoupon;
import com.virtualSlime.Enum.EntityType.UserSex;
import com.virtualSlime.Enum.ItemState;
import com.virtualSlime.Enum.UserState;
import com.virtualSlime.Mapper.UserCouponMapper;
import com.virtualSlime.Mapper.UserFollowMapper;
import com.virtualSlime.Mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.sql.Wrapper;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserRepository {
    @Resource
    private UserMapper userMapper;
    @Resource
    private UserCouponMapper userCouponMapper;
    @Resource
    private UserFollowMapper userFollowMapper;

    //basic insert
    public boolean insertUser(User user){
        return userMapper.insert(user) == 1;
    }

    public boolean insertFollow(User userFrom,User userTo,Date now){
        UserFollow follow = new UserFollow(userFrom.getUid(),userTo.getUid(),false,now);

        return userFollowMapper.insert(follow) == 1;
    }

    //basic update
    public boolean updateUser(User user){
        UpdateWrapper<User> wrapper = new UpdateWrapper<>();
        wrapper.eq("uid",user.getUid());

        return userMapper.update(user, wrapper) == 1;
    }

    public boolean updateFollow(UserFollow record){
        UpdateWrapper<UserFollow> wrapper = new UpdateWrapper<UserFollow>().eq("uid_from",record.getUidFrom())
                .and(w -> w.eq("uid_to",record.getUidTo()));

        return userFollowMapper.update(record,wrapper) == 1;
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

    public List<User> selectAvailableUsersLikeKeywordByPage(String keyword,int currentPage,int itemCount){
        QueryWrapper<User> wrapper = new QueryWrapper<User>().like("user_name",keyword)
                .and(w -> w.eq("user_state", UserState.NORMAL));

        Page<User> pageInfo = new Page<>(currentPage,itemCount,false);
        IPage<User> page = userMapper.selectPage(pageInfo,wrapper);

        return page.getRecords();
    }

    public int selectUserCouponCount(User user){
        QueryWrapper<UserCoupon> wrapper = new QueryWrapper<UserCoupon>().eq("uid",user.getUid());

        Long newCount = userCouponMapper.selectCount(wrapper);
        int couponCount = 0;
        if(newCount == null){
            return couponCount;
        }else{
            return (int) (couponCount + newCount);
        }
    }

    public UserFollow selectUserFollow(User userFrom,User userTo){
        QueryWrapper<UserFollow> wrapper = new QueryWrapper<UserFollow>().eq("uid_from",userFrom.getUid())
                .and(w -> w.eq("uid_to",userTo.getUid()));

        return userFollowMapper.selectOne(wrapper);
    }

    public boolean checkUserHasFollowed(User userFrom,User userTo){
        UserFollow result = selectUserFollow(userFrom,userTo);

        return result != null;
    }

    public int selectUserFollowerCount(User user){
        QueryWrapper<UserFollow> wrapper = new QueryWrapper<UserFollow>().eq("uid_to",user.getUid());

        Long listSize = userFollowMapper.selectCount(wrapper);
        int followerCount = 0;
        if(listSize == null){
            return 0;
        }

        return (int) (followerCount + listSize);
    }

    public List<User> selectUserFollowers(User user){
        QueryWrapper<UserFollow> wrapper = new QueryWrapper<UserFollow>().eq("uid_to",user.getUid());
        List<UserFollow> list = userFollowMapper.selectList(wrapper);

        List<User> result = new ArrayList<>();
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        for(UserFollow i : list){
            userQueryWrapper.eq("uid",i.getUidFrom());
            User newFollower = userMapper.selectOne(userQueryWrapper);
            result.add(newFollower);
            userQueryWrapper.clear();
        }

        return result;
    }

    public int selectUserFollowingCount(User user){
        QueryWrapper<UserFollow> wrapper = new QueryWrapper<UserFollow>().eq("uid_from",user.getUid());

        Long listSize = userFollowMapper.selectCount(wrapper);
        int followerCount = 0;
        if(listSize == null){
            return 0;
        }

        return (int) (followerCount + listSize);
    }

    public List<User> selectUserFollowings(User user){
        QueryWrapper<UserFollow> wrapper = new QueryWrapper<UserFollow>().eq("uid_from",user.getUid());
        List<UserFollow> list = userFollowMapper.selectList(wrapper);

        List<User> result = new ArrayList<>();
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        for(UserFollow i : list){
            userQueryWrapper.eq("uid",i.getUidFrom());
            User newFollower = userMapper.selectOne(userQueryWrapper);
            result.add(newFollower);
            userQueryWrapper.clear();
        }

        return result;
    }

    //update queries
    public boolean updateUserLogin(User user, Date now){
        //update user's lastLogin and totalLogin
        UpdateWrapper<User> wrapper = new UpdateWrapper<>();
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

    public boolean updateUserIsMerchant(User user){
        //update user's merchant state
        user.setUserIsMerchant(true);

        return updateUser(user);
    }

    public boolean updateUserPassword(User user,String password){
        //update user's password
        user.setUserPassword(password);

        return updateUser(user);
    }

    public int updateUserCurrencyBuy(User user, Item item){
        BigDecimal userCurrency = user.getUserCurrency();
        userCurrency = userCurrency.subtract(item.getItemPrice());
        if(item.getIsDiscounting()){
            userCurrency = userCurrency.add(item.getItemPriceDiscounted());
        }

        if(userCurrency.compareTo(new BigDecimal(0)) < 0){
            //not enough currency
            return -1;
        }

        user.setUserCurrency(userCurrency);
        if(updateUser(user)){
            //successfully update user
            return 1;
        }

        //failed to update user
        return 0;
    }

    public boolean updateUserCurrencyAdd(User user, BigDecimal count){
        //add count to user's currency
        BigDecimal userCurrency = user.getUserCurrency();
        BigDecimal newCount = userCurrency.add(count);
        user.setUserCurrency(newCount);

        return updateUser(user);
    }

    public boolean updateUserHasActivatedTrue(User user){
        //set user's hasActivated to true and state to NORMAL
        user.setUserHasActivated(true);
        user.setUserState(UserState.NORMAL);

        return updateUser(user);
    }

    public boolean updateUserHasChangedAvatarTrue(User user){
        //set user's hasChangedAvatar to true
        if(user.getUserHasChangedAvatar()){
            return true;
        }

        user.setUserHasChangedAvatar(true);

        return updateUser(user);
    }

    public int updateUserResetEmail(User user, String newEmail){
        //set user's email to new email, state to restricted and hasActivated to false
        //if email has not changed, return -1
        if(user.getUserEmail().equals(newEmail)) {
            return -1;
        }

        user.setUserEmail(newEmail);
        user.setUserHasActivated(false);
        user.setUserState(UserState.RESTRICTED);

        boolean state = updateUser(user);
        if(state){
            return 1;
        }

        return 0;
    }

    public boolean updateFollowSpecialized(UserFollow record){
        record.setIsSpecialized(!record.getIsSpecialized());

        return updateFollow(record);
    }


    public boolean deleteUser(User user){
        //set's user's state to UserState.LOGOFF
        user.setUserState(UserState.LOGOFF);

        return updateUser(user);
    }

    public boolean unfollowUser(User userFrom,User userTo){
        QueryWrapper<UserFollow> wrapper = new QueryWrapper<UserFollow>().eq("uid_from",userFrom.getUid())
                .and(w -> w.eq("uid_to",userTo.getUid()));

        return userFollowMapper.delete(wrapper) == 1;
    }
}
