package com.virtualSlime.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.virtualSlime.Entity.User;
import com.virtualSlime.Enum.LoginPageState;
import com.virtualSlime.Enum.UserState;
import com.virtualSlime.Service.UserRepository;
import com.virtualSlime.Utils.Result;
import com.virtualSlime.Utils.StringEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
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
            return objectMapper.writeValueAsString(new Result(LoginPageState.WRONG_INFO,null));
        }

        if(user.getUserState() == UserState.LOGOFF){
            //return UserState.LOGOFF to logged off user
            return objectMapper.writeValueAsString(new Result(LoginPageState.ACCESS_DENIED,null));
        }

        return null;
    }

    private String login(String userPassword, User user) throws JsonProcessingException {
            //match the given password with the saved encoded password
            if(StringEncoder.matchPassword(userPassword, user.getUserPassword())){

                //update user's lastLogin and totalLogin in the database
                Date now = new Date();
                if(!userRepository.updateUserLogin(user, now)){
                    //if the update process fails, return INTERNAL_ERROR
                    return objectMapper.writeValueAsString(new Result(LoginPageState.INTERNAL_ERROR,null));
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
                return objectMapper.writeValueAsString(new Result(LoginPageState.SUCCESSFUL,user.getUid()));
            }else{
                //password wrong, return WRONG_INFO
                return objectMapper.writeValueAsString(new Result(LoginPageState.WRONG_INFO,null));
            }
    }

    @RequestMapping("/login/email")
    public String loginByEmail(@RequestParam(value = "useremail",defaultValue = "")String userEmail,
                               @RequestParam(value = "password",defaultValue = "")String userPassword) throws JsonProcessingException {

        //find the user by the given email address
        User user = userRepository.selectUserByEmail(userEmail);

        String invalidity = checkInvalidity(user);
        if(invalidity != null){
            return invalidity;
        }

        if(userEmail.length() != 0 && userPassword.length() != 0){
            return login(userPassword, user);
        }else{
            //any empty input will cause INPUT_ERROR
            return objectMapper.writeValueAsString(new Result(LoginPageState.INPUT_ERROR,null));
        }
    }

    @RequestMapping("/login/name")
    public String loginByUserName(@RequestParam(value = "username",defaultValue = "")String userName,
                                  @RequestParam(value = "password",defaultValue = "")String userPassword) throws JsonProcessingException {
        //find the user by the given username
        User user = userRepository.selectUserByUserName(userName);

        String invalidity = checkInvalidity(user);
        if(invalidity != null){
            return invalidity;
        }

        if(userName.length() != 0 && userPassword.length() != 0){
            return login(userPassword, user);
        }else{
            //any empty input will cause INPUT_ERROR
            return objectMapper.writeValueAsString(new Result(LoginPageState.INPUT_ERROR,null));
        }
    }
}
