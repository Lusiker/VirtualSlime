package com.virtualSlime.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.virtualSlime.Entity.User;
import com.virtualSlime.Enum.ProfileState;
import com.virtualSlime.Enum.UserSex;
import com.virtualSlime.Service.UserRepository;
import com.virtualSlime.Utils.Result;
import com.virtualSlime.Utils.UserInfoWrapper;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class ProfileController {
    @Resource
    UserRepository userRepository;
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
            return objectMapper.writeValueAsString(new Result(ProfileState.INTERNAL_ERROR,null));
        }

        if(targetUser == null){
            return objectMapper.writeValueAsString(new Result(ProfileState.FAILED,"Target Not Exist"));
        }

        if(fromUser.getUid().equals(targetUser.getUid())){
            //trying to access user' own homepage, return AS_MASTER
            UserInfoWrapper infoWrapper = new UserInfoWrapper(targetUser,0);
            infoWrapper.setFollowerCount(userRepository.selectUserFollowerCount(targetUser.getUid()));
            infoWrapper.setFollowingCount(userRepository.selectUserFollowingCount(targetUser.getUid()));
            //infoWrapper.setCouponCount(userRepository.selectUserCouponCount(targetUser.getUid()));

            return objectMapper.writeValueAsString(new Result(ProfileState.AS_MASTER,infoWrapper));
        }else{
            //entering other's homepage, return AS_GUEST
            UserInfoWrapper infoWrapper = new UserInfoWrapper(targetUser,1);
            infoWrapper.setFollowerCount(userRepository.selectUserFollowerCount(targetUser.getUid()));
            infoWrapper.setFollowingCount(userRepository.selectUserFollowingCount(targetUser.getUid()));
            //infoWrapper.setCouponCount(userRepository.selectUserCouponCount(targetUser.getUid()));

            return objectMapper.writeValueAsString(new Result(ProfileState.AS_GUEST,infoWrapper));
        }
    }

    @RequestMapping("/user/name/{userName1}:{uid2}")
    public String userProfileByUserName(@PathVariable(value = "userName1")String newUserName1,
                                        @PathVariable(value = "uid2")String newUid2) throws JsonProcessingException {
        int uid2 = checkUidValid(newUid2);
        if(uid2 == -1){
            return objectMapper.writeValueAsString(new Result(ProfileState.FAILED,"Wrong info:" + uid2));
        }
        User fromUser = userRepository.selectUserByUid(uid2);
        User targetUser = userRepository.selectUserByUserName(newUserName1);

        return asGuest(fromUser,targetUser);
    }

    @RequestMapping("/user/{uid1}:{uid2}")
    public String userProfileByUid(@PathVariable(value = "uid1")String newUid1,
                                   @PathVariable(value = "uid2")String newUid2) throws JsonProcessingException {
        int uid1 = checkUidValid(newUid1);
        if(uid1 == -1) {
            return objectMapper.writeValueAsString(new Result(ProfileState.FAILED,"Wrong info:" + newUid1));
        }
        User targetUser = userRepository.selectUserByUid(uid1);

        int uid2 = checkUidValid(newUid2);
        if(uid2 == -1){
            return objectMapper.writeValueAsString(new Result(ProfileState.FAILED,"Wrong info:" + newUid2));
        }
        User fromUser = userRepository.selectUserByUid(uid2);

        return asGuest(fromUser,targetUser);
    }

    @RequestMapping("/user/{uid}/update/name={newName}")
    public String userProfileUpdateName(@PathVariable(value = "uid")String newUid,
                                        @PathVariable(value = "newName")String newName) throws JsonProcessingException{
        int uid = checkUidValid(newUid);
        if(uid == -1) {
            return objectMapper.writeValueAsString(new Result(ProfileState.FAILED,"Wrong info:" + newUid));
        }

        User user = userRepository.selectUserByUid(uid);
        if(user == null){
            return objectMapper.writeValueAsString(new Result(ProfileState.INTERNAL_ERROR,null));
        }

        if(!userRepository.updateUserName(user,newName)){
            return objectMapper.writeValueAsString(new Result(ProfileState.UPDATE_FAILED,"duplicated:" + newName));
        }

        return objectMapper.writeValueAsString(new Result(ProfileState.UPDATE_SUCCESSFUL,newName));
    }

    @RequestMapping("user/{uid}/update/intro={newIntro}")
    public String userProfileUpdateIntroduction(@PathVariable(value = "uid")String newUid,
                                                @PathVariable(value = "newIntro")String newIntro) throws JsonProcessingException{
        int uid = checkUidValid(newUid);
        if(uid == -1) {
            return objectMapper.writeValueAsString(new Result(ProfileState.FAILED,"Wrong info:" + newUid));
        }

        User user = userRepository.selectUserByUid(uid);
        if(user == null){
            return objectMapper.writeValueAsString(new Result(ProfileState.INTERNAL_ERROR,null));
        }

        if(!userRepository.updateUserIntroduction(user,newIntro)){
            return objectMapper.writeValueAsString(new Result(ProfileState.UPDATE_FAILED,newIntro));
        }

        return objectMapper.writeValueAsString(new Result(ProfileState.UPDATE_SUCCESSFUL,newIntro));
    }

    @RequestMapping("user/{uid}/update/sex={newSex}")
    public String userProfileUpdateSex(@PathVariable(value = "uid")String newUid,
                                       @PathVariable(value = "newSex")String newSex) throws JsonProcessingException{
        int uid = checkUidValid(newUid);
        if(uid == -1) {
            return objectMapper.writeValueAsString(new Result(ProfileState.FAILED, newUid));
        }

        int newUserSexInt;
        try{
            newUserSexInt = Integer.parseInt(newSex);
        }catch (Exception e){
            return objectMapper.writeValueAsString(new Result(ProfileState.FAILED,newSex));
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
            return objectMapper.writeValueAsString(new Result(ProfileState.INTERNAL_ERROR,null));
        }

        if(!userRepository.updateUserSex(user,userSex)){
            return objectMapper.writeValueAsString(new Result(ProfileState.INTERNAL_ERROR,null));
        }

        return objectMapper.writeValueAsString(new Result(ProfileState.UPDATE_SUCCESSFUL,userSex.getValue()));
    }

    @RequestMapping("/user/{uid}/update/birthday={newBirthday}")
    public String userProfileUpdateBirthday(@PathVariable(value = "uid")String newUid,
                                        @PathVariable(value = "newBirthday")String newBirthday) throws JsonProcessingException, ParseException {
        int uid = checkUidValid(newUid);
        if(uid == -1) {
            return objectMapper.writeValueAsString(new Result(ProfileState.FAILED,"Wrong info:" + newUid));
        }

        User user = userRepository.selectUserByUid(uid);
        if(user == null){
            return objectMapper.writeValueAsString(new Result(ProfileState.INTERNAL_ERROR,null));
        }

        Date birthday;
        try {
            birthday = format.parse(newBirthday);
        }catch (ParseException e){
            return objectMapper.writeValueAsString(new Result(ProfileState.FAILED,"Wrong info:" + newBirthday));
        }

        if(!userRepository.updateUserBirthday(user,birthday)){
            return objectMapper.writeValueAsString(new Result(ProfileState.UPDATE_FAILED,null));
        }

        return objectMapper.writeValueAsString(new Result(ProfileState.UPDATE_SUCCESSFUL,newBirthday));
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
            return objectMapper.writeValueAsString(new Result(ProfileState.FAILED,"Wrong info:" + newUid));
        }

        int showBirthday;
        try{
            showBirthday = Integer.parseInt(newState);
        }catch (Exception e){
            return objectMapper.writeValueAsString(new Result(ProfileState.FAILED,"Wrong info:" + newState));
        }

        boolean state;
        state = showBirthday == 1;

        User user = userRepository.selectUserByUid(uid);
        if(user == null){
            return objectMapper.writeValueAsString(new Result(ProfileState.INTERNAL_ERROR,null));
        }

        if(!userRepository.updateUserShowBirthday(user,state)){
            return objectMapper.writeValueAsString(new Result(ProfileState.UPDATE_FAILED,newState));
        }

        return objectMapper.writeValueAsString(new Result(ProfileState.UPDATE_SUCCESSFUL,newState));
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
            return objectMapper.writeValueAsString(new Result(ProfileState.FAILED,"Wrong info:" + newUid));
        }

        int showDynamic;
        try{
            showDynamic = Integer.parseInt(newState);
        }catch (Exception e){
            return objectMapper.writeValueAsString(new Result(ProfileState.FAILED,"Wrong info:" + newState));
        }

        boolean state;
        state = showDynamic == 1;

        User user = userRepository.selectUserByUid(uid);
        if(user == null){
            return objectMapper.writeValueAsString(new Result(ProfileState.INTERNAL_ERROR,null));
        }

        if(!userRepository.updateUserShowDynamic(user,state)){
            return objectMapper.writeValueAsString(new Result(ProfileState.UPDATE_FAILED,newState));
        }

        return objectMapper.writeValueAsString(new Result(ProfileState.UPDATE_SUCCESSFUL,newState));
    }

    @RequestMapping("user/{uid}/update/password={newPassword}")
    public String userProfileUpdatePassword(@PathVariable(value = "uid")String newUid,
                                            @PathVariable(value = "newPassword")String newPassword) throws JsonProcessingException{
        int uid = checkUidValid(newUid);
        if(uid == -1) {
            return objectMapper.writeValueAsString(new Result(ProfileState.FAILED, newUid));
        }

        User user = userRepository.selectUserByUid(uid);
        if(user == null){
            return objectMapper.writeValueAsString(new Result(ProfileState.INTERNAL_ERROR,null));
        }

        return newPassword;
    }
}
