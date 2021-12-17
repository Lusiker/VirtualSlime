package com.virtualSlime.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.virtualSlime.Entity.Item;
import com.virtualSlime.Entity.Relation.UserBought;
import com.virtualSlime.Entity.User;
import com.virtualSlime.Enum.PageState.ProfilePageState;
import com.virtualSlime.Enum.EntityType.UserSex;
import com.virtualSlime.Service.ItemRepository;
import com.virtualSlime.Utils.InfoWrapper.FollowerInfoWrapper;
import com.virtualSlime.Utils.PasswordSimplicityChecker;
import com.virtualSlime.Service.UserRepository;
import com.virtualSlime.Utils.*;
import com.virtualSlime.Utils.InfoWrapper.ItemInfoWrapper;
import com.virtualSlime.Utils.InfoWrapper.Result;
import com.virtualSlime.Utils.InfoWrapper.UserInfoWrapper;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@RestController
public class ProfileController {
    @Resource
    UserRepository userRepository;
    @Resource
    ItemRepository itemRepository;
    @Resource
    GlobalCategoryCache categoryCache;
    @Resource
    ObjectMapper objectMapper;

    private final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    private int checkUidValid(String newUid){
        int uid;
        try{
            uid = Integer.parseInt(newUid);
        }catch (Exception e){
            return -1;
        }

        return uid;
    }

    private String asGuest(User fromUser, User targetUser) throws JsonProcessingException {
        if(fromUser == null){
            return objectMapper.writeValueAsString(new Result(ProfilePageState.INTERNAL_ERROR,null));
        }

        if(targetUser == null){
            return objectMapper.writeValueAsString(new Result(ProfilePageState.FAILED,"Target Not Exist"));
        }

        if(fromUser.getUid().equals(targetUser.getUid())){
            //trying to access user' own homepage, return AS_MASTER
            UserInfoWrapper infoWrapper = new UserInfoWrapper(targetUser,0);
            infoWrapper.setFollowerCount(userRepository.selectUserFollowerCount(targetUser));
            infoWrapper.setFollowingCount(userRepository.selectUserFollowingCount(targetUser));
            infoWrapper.setCouponCount(userRepository.selectUserCouponCount(targetUser));

            return objectMapper.writeValueAsString(new Result(ProfilePageState.AS_MASTER,infoWrapper));
        }else{
            //entering other's homepage, return AS_GUEST
            UserInfoWrapper infoWrapper = new UserInfoWrapper(targetUser,1);
            infoWrapper.setFollowerCount(userRepository.selectUserFollowerCount(targetUser));
            infoWrapper.setFollowingCount(userRepository.selectUserFollowingCount(targetUser));
            infoWrapper.setCouponCount(null);

            return objectMapper.writeValueAsString(new Result(ProfilePageState.AS_GUEST,infoWrapper));
        }
    }

    //access profile api
    /**
     * uid2 trying to access userName1
     * @param newUserName1 target username
     * @param newUid2 requesting user id
     * @return result
     */
    @RequestMapping("/user/name/{userName1}:{uid2}")
    public String userProfileByUserName(@PathVariable(value = "userName1")String newUserName1,
                                        @PathVariable(value = "uid2")String newUid2) throws JsonProcessingException {
        int uid2 = checkUidValid(newUid2);
        if(uid2 == -1){
            return objectMapper.writeValueAsString(new Result(ProfilePageState.FAILED,"Wrong info:" + uid2));
        }
        User fromUser = userRepository.selectUserByUid(uid2);
        User targetUser = userRepository.selectUserByUserName(newUserName1);

        return asGuest(fromUser,targetUser);
    }

    /**
     * uid2 trying to access uid1
     * @param newUid1 target user id
     * @param newUid2 requesting user id
     * @return result
     */
    @RequestMapping("/user/{uid1}:{uid2}")
    public String userProfileByUid(@PathVariable(value = "uid1")String newUid1,
                                   @PathVariable(value = "uid2")String newUid2) throws JsonProcessingException {
        int uid1 = checkUidValid(newUid1);
        if(uid1 == -1) {
            return objectMapper.writeValueAsString(new Result(ProfilePageState.FAILED,"Wrong info:" + newUid1));
        }
        User targetUser = userRepository.selectUserByUid(uid1);

        int uid2 = checkUidValid(newUid2);
        if(uid2 == -1){
            return objectMapper.writeValueAsString(new Result(ProfilePageState.FAILED,"Wrong info:" + newUid2));
        }
        User fromUser = userRepository.selectUserByUid(uid2);

        return asGuest(fromUser,targetUser);
    }

    //check complex info
    @RequestMapping("/user/{uid}/cart")
    public String userProfileShowCart(@PathVariable(value = "uid")String newUid) throws JsonProcessingException {
        int uid = checkUidValid(newUid);
        if(uid == -1) {
            return objectMapper.writeValueAsString(new Result(ProfilePageState.FAILED,"Wrong info:" + newUid));
        }

        User user = userRepository.selectUserByUid(uid);
        if(user == null){
            return objectMapper.writeValueAsString(new Result(ProfilePageState.INTERNAL_ERROR,null));
        }

        List<Item> list = itemRepository.selectUserCartAsItemList(user);
        List<ItemInfoWrapper> result = new ArrayList<ItemInfoWrapper>();
        for(Item i : list){
            User newUser = userRepository.selectUserByUid(i.getUid());
            ItemInfoWrapper infoWrapper = new ItemInfoWrapper.ItemInfoWrapperBuilder()
                    .setItem(i,categoryCache)
                    .setSeller(newUser)
                    .build();
            result.add(infoWrapper);
        }

        //return (3,show_cart),list of item in cart
        return objectMapper.writeValueAsString(new Result(ProfilePageState.SHOW_CART,result));
    }

    @RequestMapping("user/{uid}/bought")
    public String userProfileShowAllBought(@PathVariable(value = "uid")String newUid) throws JsonProcessingException{
        int uid = checkUidValid(newUid);
        if(uid == -1) {
            return objectMapper.writeValueAsString(new Result(ProfilePageState.FAILED,"Wrong info:" + newUid));
        }

        User user = userRepository.selectUserByUid(uid);
        if(user == null){
            return objectMapper.writeValueAsString(new Result(ProfilePageState.INTERNAL_ERROR,null));
        }

        List<UserBought> list = itemRepository.selectUserBought(user);
        list.sort(Comparator.comparing(UserBought::getCreatedTime).reversed());

        List<ItemInfoWrapper> result = new ArrayList<ItemInfoWrapper>();
        for(UserBought b : list){
            Item newItem = itemRepository.selectItemByIid(b.getIid());
            User newUser = userRepository.selectUserByUid(b.getUid());
            ItemInfoWrapper infoWrapper = new ItemInfoWrapper.ItemInfoWrapperBuilder()
                    .setItem(newItem,categoryCache)
                    .setSeller(newUser)
                    .setBoughtTime(b)
                    .build();
            result.add(infoWrapper);
        }

        //return (4,show_bought),list of item in cart
        return objectMapper.writeValueAsString(new Result(ProfilePageState.SHOW_BOUGHT,result));
    }

    @RequestMapping("/user/{uid}/follower")
    public String userProfileShowFollowers(@PathVariable(value = "uid")String newUid) throws JsonProcessingException{
        int uid = checkUidValid(newUid);
        if(uid == -1) {
            return objectMapper.writeValueAsString(new Result(ProfilePageState.FAILED,"Wrong Info:" + newUid));
        }

        User user = userRepository.selectUserByUid(uid);
        if(user == null){
            return objectMapper.writeValueAsString(new Result(ProfilePageState.INTERNAL_ERROR,null));
        }

        List<User> followers = userRepository.selectUserFollowers(user);
        List<FollowerInfoWrapper> result = new ArrayList<FollowerInfoWrapper>();
        for(User f : followers){
            FollowerInfoWrapper wrapper = new FollowerInfoWrapper(f);
            result.add(wrapper);
        }

        //return (5,show_bought),list of item in followers
        return objectMapper.writeValueAsString(new Result(ProfilePageState.SHOW_FOLLOWER,result));
    }

    @RequestMapping("/user/{uid}/following")
    public String userProfileShowFollowings(@PathVariable(value = "uid")String newUid) throws JsonProcessingException{
        int uid = checkUidValid(newUid);
        if(uid == -1) {
            return objectMapper.writeValueAsString(new Result(ProfilePageState.FAILED,"Wrong Info:" + newUid));
        }

        User user = userRepository.selectUserByUid(uid);
        if(user == null){
            return objectMapper.writeValueAsString(new Result(ProfilePageState.INTERNAL_ERROR,null));
        }

        List<User> followers = userRepository.selectUserFollowings(user);
        List<FollowerInfoWrapper> result = new ArrayList<FollowerInfoWrapper>();
        for(User f : followers){
            FollowerInfoWrapper wrapper = new FollowerInfoWrapper(f);
            result.add(wrapper);
        }

        //return (6,show_bought),list of item in followings
        return objectMapper.writeValueAsString(new Result(ProfilePageState.SHOW_FOLLOWER,result));
    }

    //update api
    /**
     * Make given user a merchant
     * @param newUid uid
     * @return update result
     */
    @RequestMapping("user/{uid}/toMerchant")
    public String userProfileToMerchant(@PathVariable(value = "uid")String newUid) throws JsonProcessingException{
        int uid = checkUidValid(newUid);
        if(uid == -1) {
            return objectMapper.writeValueAsString(new Result(ProfilePageState.FAILED,"Wrong Info:" + newUid));
        }

        User user = userRepository.selectUserByUid(uid);
        if(user == null){
            return objectMapper.writeValueAsString(new Result(ProfilePageState.INTERNAL_ERROR,null));
        }

        if(user.getUserIsMerchant()){
            return objectMapper.writeValueAsString(new Result(ProfilePageState.FAILED,"Already Merchant"));
        }

        if(!userRepository.updateUserIsMerchant(user)){
            return objectMapper.writeValueAsString(new Result(ProfilePageState.INTERNAL_ERROR,null));
        }

        return objectMapper.writeValueAsString(new Result(ProfilePageState.UPDATE_SUCCESSFUL,null));
    }

    /**
     * @param newUid uid
     * @param newName a String no longer than 20
     * @return update result
     */
    @RequestMapping("/user/{uid}/update/name={newName}")
    public String userProfileUpdateName(@PathVariable(value = "uid")String newUid,
                                        @PathVariable(value = "newName")String newName) throws JsonProcessingException{
        int uid = checkUidValid(newUid);
        if(uid == -1) {
            return objectMapper.writeValueAsString(new Result(ProfilePageState.FAILED,"Wrong Info:" + newUid));
        }

        if(newName.length() == 0){
            return objectMapper.writeValueAsString(new Result(ProfilePageState.FAILED,"Wrong Input"));
        }

        if(newName.length() > 20){
            return objectMapper.writeValueAsString(new Result(ProfilePageState.FAILED,"Input Too Long"));
        }

        User user = userRepository.selectUserByUid(uid);
        if(user == null){
            return objectMapper.writeValueAsString(new Result(ProfilePageState.INTERNAL_ERROR,null));
        }

        if(newName.equals(user.getUserName())){
            return objectMapper.writeValueAsString(new Result(ProfilePageState.UPDATE_FAILED,"Not Changed:" + newName));
        }

        if(!userRepository.updateUserName(user,newName)){
            return objectMapper.writeValueAsString(new Result(ProfilePageState.UPDATE_FAILED,"Duplicated:" + newName));
        }

        return objectMapper.writeValueAsString(new Result(ProfilePageState.UPDATE_SUCCESSFUL,newName));
    }

    /**
     * @param newUid uid
     * @param newIntro a String no longer than 50
     * @return update result
     */
    @RequestMapping("user/{uid}/update/intro={newIntro}")
    public String userProfileUpdateIntroduction(@PathVariable(value = "uid")String newUid,
                                                @PathVariable(value = "newIntro")String newIntro) throws JsonProcessingException{
        int uid = checkUidValid(newUid);
        if(uid == -1) {
            return objectMapper.writeValueAsString(new Result(ProfilePageState.FAILED,"Wrong Info:" + newUid));
        }

        if(newIntro.length() > 50){
            return objectMapper.writeValueAsString(new Result(ProfilePageState.FAILED,"Input Too Long"));
        }

        User user = userRepository.selectUserByUid(uid);
        if(user == null){
            return objectMapper.writeValueAsString(new Result(ProfilePageState.INTERNAL_ERROR,null));
        }

        if(!userRepository.updateUserIntroduction(user,newIntro)){
            return objectMapper.writeValueAsString(new Result(ProfilePageState.UPDATE_FAILED,newIntro));
        }

        return objectMapper.writeValueAsString(new Result(ProfilePageState.UPDATE_SUCCESSFUL,newIntro));
    }

    /**
     * @param newUid uid
     * @param newSex 0/1/2/3 -> undefined/male/female/secret
     * @return update result
     */
    @RequestMapping("user/{uid}/update/sex={newSex}")
    public String userProfileUpdateSex(@PathVariable(value = "uid")String newUid,
                                       @PathVariable(value = "newSex")String newSex) throws JsonProcessingException{
        int uid = checkUidValid(newUid);
        if(uid == -1) {
            return objectMapper.writeValueAsString(new Result(ProfilePageState.FAILED, newUid));
        }

        int newUserSexInt;
        try{
            newUserSexInt = Integer.parseInt(newSex);
        }catch (Exception e){
            return objectMapper.writeValueAsString(new Result(ProfilePageState.FAILED,newSex));
        }

        UserSex userSex;
        switch (newUserSexInt) {
            case 1 -> userSex = UserSex.MALE;
            case 2 -> userSex = UserSex.FEMALE;
            case 3 -> userSex = UserSex.SECRET;
            default -> userSex = UserSex.UNDEFINED;
        }

        User user = userRepository.selectUserByUid(uid);
        if(user == null){
            return objectMapper.writeValueAsString(new Result(ProfilePageState.INTERNAL_ERROR,null));
        }

        if(!userRepository.updateUserSex(user,userSex)){
            return objectMapper.writeValueAsString(new Result(ProfilePageState.INTERNAL_ERROR,null));
        }

        return objectMapper.writeValueAsString(new Result(ProfilePageState.UPDATE_SUCCESSFUL,userSex.getValue()));
    }

    /**
     *
     * @param newUid uid
     * @param newBirthday yyyy-MM-dd. Notice, input like 2000-13-99 will NOT cause crash, instead, a wrong result will
     *                    be calculated.
     * @return update result
     */
    @RequestMapping("/user/{uid}/update/birthday={newBirthday}")
    public String userProfileUpdateBirthday(@PathVariable(value = "uid")String newUid,
                                        @PathVariable(value = "newBirthday")String newBirthday) throws JsonProcessingException{
        int uid = checkUidValid(newUid);
        if(uid == -1) {
            return objectMapper.writeValueAsString(new Result(ProfilePageState.FAILED,"Wrong info:" + newUid));
        }

        User user = userRepository.selectUserByUid(uid);
        if(user == null){
            return objectMapper.writeValueAsString(new Result(ProfilePageState.INTERNAL_ERROR,null));
        }

        Date birthday;
        try {
            birthday = format.parse(newBirthday);
        }catch (ParseException e){
            return objectMapper.writeValueAsString(new Result(ProfilePageState.FAILED,"Wrong info:" + newBirthday));
        }

        if(!userRepository.updateUserBirthday(user,birthday)){
            return objectMapper.writeValueAsString(new Result(ProfilePageState.UPDATE_FAILED,null));
        }

        return objectMapper.writeValueAsString(new Result(ProfilePageState.UPDATE_SUCCESSFUL,newBirthday));
    }

    /**
     * @param newUid uid
     * @param newState 0/1 -> true/false
     * @return update result
     */
    @RequestMapping("user/{uid}/update/showBirthday={state}")
    public String userProfileUpdateShowBirthday(@PathVariable(value = "uid")String newUid,
                                                @PathVariable(value = "state")String newState) throws JsonProcessingException{
        int uid = checkUidValid(newUid);
        if(uid == -1) {
            return objectMapper.writeValueAsString(new Result(ProfilePageState.FAILED,"Wrong info:" + newUid));
        }

        int showBirthday;
        try{
            showBirthday = Integer.parseInt(newState);
        }catch (Exception e){
            return objectMapper.writeValueAsString(new Result(ProfilePageState.FAILED,"Wrong info:" + newState));
        }

        boolean state;
        state = showBirthday == 1;

        User user = userRepository.selectUserByUid(uid);
        if(user == null){
            return objectMapper.writeValueAsString(new Result(ProfilePageState.INTERNAL_ERROR,null));
        }

        if(!userRepository.updateUserShowBirthday(user,state)){
            return objectMapper.writeValueAsString(new Result(ProfilePageState.UPDATE_FAILED,newState));
        }

        return objectMapper.writeValueAsString(new Result(ProfilePageState.UPDATE_SUCCESSFUL,newState));
    }

    /**
     * @param newUid uid
     * @param newState 0/1 -> true/false
     * @return update result
     */
    @RequestMapping("user/{uid}/update/showDynamic={state}")
    public String userProfileUpdateShowDynamic(@PathVariable(value = "uid")String newUid,
                                               @PathVariable(value = "state")String newState) throws JsonProcessingException{
        int uid = checkUidValid(newUid);
        if(uid == -1) {
            return objectMapper.writeValueAsString(new Result(ProfilePageState.FAILED,"Wrong info:" + newUid));
        }

        int showDynamic;
        try{
            showDynamic = Integer.parseInt(newState);
        }catch (Exception e){
            return objectMapper.writeValueAsString(new Result(ProfilePageState.FAILED,"Wrong info:" + newState));
        }

        boolean state;
        state = showDynamic == 1;

        User user = userRepository.selectUserByUid(uid);
        if(user == null){
            return objectMapper.writeValueAsString(new Result(ProfilePageState.INTERNAL_ERROR,null));
        }

        if(!userRepository.updateUserShowDynamic(user,state)){
            return objectMapper.writeValueAsString(new Result(ProfilePageState.UPDATE_FAILED,newState));
        }

        return objectMapper.writeValueAsString(new Result(ProfilePageState.UPDATE_SUCCESSFUL,newState));
    }

    /**
     * @param newUid uid
     * @param newPassword new password - this will also receive a simplicity check
     * @return result
     */
    @RequestMapping("user/{uid}/update/password={newPassword}")
    public String userProfileUpdatePassword(@PathVariable(value = "uid")String newUid,
                                            @PathVariable(value = "newPassword")String newPassword) throws JsonProcessingException{
        int uid = checkUidValid(newUid);
        if(uid == -1) {
            return objectMapper.writeValueAsString(new Result(ProfilePageState.FAILED, newUid));
        }

        User user = userRepository.selectUserByUid(uid);
        if(user == null){
            return objectMapper.writeValueAsString(new Result(ProfilePageState.INTERNAL_ERROR,null));
        }

        if(newPassword.length() != 0){
            if(PasswordSimplicityChecker.checkPasswordSimplicity(newPassword)){
                return objectMapper.writeValueAsString(new Result(ProfilePageState.FAILED,"New Password Too Simple"));
            }

            String encodedUserPassword = StringEncoder.userPasswordEncode(newPassword);
            if(!userRepository.updateUserPassword(user,encodedUserPassword)){
                return objectMapper.writeValueAsString(new Result(ProfilePageState.UPDATE_FAILED,null));
            }

            return objectMapper.writeValueAsString(new Result(ProfilePageState.UPDATE_SUCCESSFUL,null));
        }else{
            return objectMapper.writeValueAsString(new Result(ProfilePageState.FAILED,"Empty Input"));
        }
    }

    /**
     * @param newUid uid
     * @param newAddCount a String-styled float number with length <= 12 and precision = 2
     * @return updated user currency or errors
     */
    @RequestMapping("user/{uid}/update/addCurrency={addCount}")
    public String userProfileAddCurrency(@PathVariable(value = "uid")String newUid,
                                         @PathVariable(value = "addCount")String newAddCount) throws JsonProcessingException{
        int uid = checkUidValid(newUid);
        if(uid == -1) {
            return objectMapper.writeValueAsString(new Result(ProfilePageState.FAILED, newUid));
        }

        User user = userRepository.selectUserByUid(uid);
        if(user == null){
            return objectMapper.writeValueAsString(new Result(ProfilePageState.INTERNAL_ERROR,null));
        }

        BigDecimal addCount;
        try{
            double addCountLiteral = Double.parseDouble(newAddCount);
            addCount = BigDecimal.valueOf(addCountLiteral);
        }catch (Exception e){
            return objectMapper.writeValueAsString(new Result(ProfilePageState.FAILED, newAddCount));
        }

        if(!userRepository.updateUserCurrencyAdd(user,addCount)){
            return objectMapper.writeValueAsString(new Result(ProfilePageState.UPDATE_FAILED,null));
        }

        return objectMapper.writeValueAsString(new Result(ProfilePageState.UPDATE_SUCCESSFUL,user.getUserCurrency()));
    }
}
