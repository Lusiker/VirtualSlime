package com.virtualSlime.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.virtualSlime.Entity.User;
import com.virtualSlime.Enum.ActivateState;
import com.virtualSlime.Enum.ProfileState;
import com.virtualSlime.Enum.UserSex;
import com.virtualSlime.Service.UserRepository;
import com.virtualSlime.Utils.Result;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@RestController
public class ProfileController {
    @Resource
    UserRepository userRepository;
    @Resource
    ObjectMapper objectMapper;

    private int checkUidValid(String newUid){
        int uid;
        try{
            uid = Integer.parseInt(newUid);
        }catch (Exception e){
            return -1;
        }

        return uid;
    }

    private String asGuest(User loginUser, User targetUser) throws JsonProcessingException {
        if(loginUser == null){
            //not logged in but trying to access other's homepage, return AS_GUEST
            return objectMapper.writeValueAsString(new Result(ProfileState.AS_GUEST,targetUser));
        }

        if(!targetUser.getUid().equals(loginUser.getUid())){
            //logged in but entering other's homepage, return AS_GUEST
            return objectMapper.writeValueAsString(new Result(ProfileState.AS_GUEST,targetUser));
        }

        return null;
    }

    @RequestMapping("/user/name/{userName}")
    public String userProfileByUserName(@PathVariable(value = "userName")String userName,
                              HttpSession session) throws JsonProcessingException {
        User loginUser = (User) session.getAttribute("loginUser");
        User targetUser = userRepository.selectUserByUserName(userName);

        String asGuestResult = asGuest(loginUser,targetUser);
        if(asGuestResult != null){
            return asGuestResult;
        }

        //visiting homepage of the logged-in user
        return objectMapper.writeValueAsString(new Result(ProfileState.AS_MASTER,loginUser));
    }

    @RequestMapping("/user/{uid}")
    public String userProfileByUid(@PathVariable(value = "uid")String newUid,
                              HttpSession session) throws JsonProcessingException {
        int uid = checkUidValid(newUid);
        if(uid == -1) {
            return objectMapper.writeValueAsString(new Result(ProfileState.FAILED, newUid));
        }

        User loginUser = (User) session.getAttribute("loginUser");
        User targetUser = userRepository.selectUserByUid(uid);

        String asGuestResult = asGuest(loginUser,targetUser);
        if(asGuestResult != null){
            return asGuestResult;
        }

        //visiting homepage of the logged-in user
        return objectMapper.writeValueAsString(new Result(ProfileState.AS_MASTER,loginUser));
    }

    @RequestMapping("/user/{uid}/update/name={newName}")
    public String userProfileUpdateName(@PathVariable(value = "uid")String newUid,
                                    @PathVariable(value = "newName")String newName,
                                    HttpSession session) throws JsonProcessingException{
        int uid = checkUidValid(newUid);
        if(uid == -1) {
            return objectMapper.writeValueAsString(new Result(ProfileState.FAILED, newUid));
        }

        User loginUser = (User) session.getAttribute("loginUser");
        if(loginUser.getUid() != uid){
            return objectMapper.writeValueAsString(new Result(ProfileState.ACCESS_DENIED,newUid));
        }

        if(userRepository.updateUserName(loginUser,newName)){
            return objectMapper.writeValueAsString(new Result(ProfileState.UPDATE_FAILED,"duplicated:" + newName));
        }

        return objectMapper.writeValueAsString(new Result(ProfileState.UPDATE_SUCCESSFUL,newName));
    }

    //under development
    /*@RequestMapping("user/{uid}/update/sex={newSex}")
    public String userProfileUpdateSex(@PathVariable(value = "uid")String newUid,
                                       @PathVariable(value = "newSex")String newSex,
                                       HttpSession session) throws JsonProcessingException{
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

        UserSex newUserSex;
        switch (newUserSexInt){
            case 0:
                newUserSex = UserSex.UNDEFINED;
                break;
                case
        }


        User loginUser = (User) session.getAttribute("loginUser");
        if(loginUser.getUid() != uid){
            return objectMapper.writeValueAsString(new Result(ProfileState.ACCESS_DENIED,newUid));
        }

        if(userRepository.updateUserSex(loginUser,))

    }*/
}
