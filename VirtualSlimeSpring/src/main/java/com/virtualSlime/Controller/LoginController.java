package com.virtualSlime.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.virtualSlime.Entity.User;
import com.virtualSlime.Enum.LoginState;
import com.virtualSlime.Enum.LogoutState;
import com.virtualSlime.Enum.UserState;
import com.virtualSlime.Service.UserRepository;
import com.virtualSlime.Utils.Result;
import com.virtualSlime.Utils.StringEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;

@RestController
public class LoginController {
    @Resource
    private UserRepository userRepository;
    @Resource
    private ObjectMapper objectMapper;

    private String checkInvalidity(User user) throws JsonProcessingException {
        //if user is null, return WRONG_INFO
        //which means the user does not exist
        if(user == null){
            return objectMapper.writeValueAsString(new Result(LoginState.WRONG_INFO,null));
        }

        if(user.getUserState() == UserState.LOGOFF){
            //return UserState.LOGOFF to logged off user
            return objectMapper.writeValueAsString(new Result(LoginState.ACCESS_DENIED,null));
        }

        return null;
    }

    private String login(String userPassword,User user,HttpSession session) throws JsonProcessingException {
            //match the given password with the saved encoded password
            if(StringEncoder.matchPassword(userPassword,user.getUserPassword())){
                //if matching is successful, add the user into session
                session.setAttribute("loginUser",user);

                //update user's lastLogin and totalLogin in the database
                Date now = new Date();
                if(!userRepository.updateUserLogin(user,now)){
                    //if the update process fails, return INTERNAL_ERROR
                    return objectMapper.writeValueAsString(new Result(LoginState.INTERNAL_ERROR,null));
                }

                //return SUCCESSFUL json↓
                //{
                //  "stateEnum" : {
                //      "state" : 0,
                //      "value" : "login_success"
                //  },
                //  "returnValue" : {
                //      "uid" : ...,
                //      ... : ...,
                //      "userCurrency" : ...
                //  }
                //}
                return objectMapper.writeValueAsString(new Result(LoginState.SUCCESSFUL,user.getUid()));
            }else{
                //password wrong, return WRONG_INFO
                return objectMapper.writeValueAsString(new Result(LoginState.WRONG_INFO,null));
            }
    }

    @RequestMapping("/login/email")
    public String loginByEmail(@RequestParam(value = "useremail",defaultValue = "")String userEmail,
                            @RequestParam(value = "password",defaultValue = "")String userPassword,
                            HttpSession session) throws JsonProcessingException {
        //If the user has logged in, return state HAS_LOGIN
        User currentUser = (User) session.getAttribute("loginUser");
        if(currentUser != null){
            return objectMapper.writeValueAsString(new Result(LoginState.HAS_LOGIN,currentUser.getUid()));
        }

        //find the user by the given email address
        User user = userRepository.selectUserByEmail(userEmail);

        String invalidity = checkInvalidity(user);
        if(invalidity != null){
            return invalidity;
        }

        if(userEmail.length() != 0 && userPassword.length() != 0){
            return login(userPassword,user,session);
        }else{
            //any empty input will cause INPUT_ERROR
            return objectMapper.writeValueAsString(new Result(LoginState.INPUT_ERROR,null));
        }
    }

    @RequestMapping("/login/name")
    public String loginByUserName(@RequestParam(value = "username",defaultValue = "")String userName,
                                  @RequestParam(value = "password",defaultValue = "")String userPassword,
                                  HttpSession session) throws JsonProcessingException {
        //If the user has logged in, return state HAS_LOGIN
        User currentUser = (User) session.getAttribute("loginUser");
        if(currentUser != null){
            return objectMapper.writeValueAsString(new Result(LoginState.HAS_LOGIN,currentUser.getUid()));
        }

        //find the user by the given username
        User user = userRepository.selectUserByUserName(userName);

        String invalidity = checkInvalidity(user);
        if(invalidity != null){
            return invalidity;
        }

        if(userName.length() != 0 && userPassword.length() != 0){
            return login(userPassword,user,session);
        }else{
            //any empty input will cause INPUT_ERROR
            return objectMapper.writeValueAsString(new Result(LoginState.INPUT_ERROR,null));
        }
    }

    @RequestMapping("/user/{id}/logout")
    public String logout(@PathVariable(value = "id")int uid,
                         HttpSession session) throws JsonProcessingException {
        //if the user has not logged in, return ACCESS_DENIED
        User currentUser = (User)session.getAttribute("loginUser");
        if(currentUser == null){
            return objectMapper.writeValueAsString(new Result(LogoutState.ACCESS_DENIED,null));
        }

        //if current user's id does not match the given uid, return ACCESS_DENIED
        if(currentUser.getUid() != uid){
            return objectMapper.writeValueAsString(new Result(LogoutState.ACCESS_DENIED,null));
        }

        //remove the login user from session
        session.removeAttribute("loginUser");

        //return SUCCESSFUL
        return objectMapper.writeValueAsString(new Result(LogoutState.SUCCESSFUL,currentUser.getUserName()));
    }
}
