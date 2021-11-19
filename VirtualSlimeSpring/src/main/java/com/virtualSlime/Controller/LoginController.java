package com.virtualSlime.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.virtualSlime.Entity.User;
import com.virtualSlime.Enum.LoginState;
import com.virtualSlime.Enum.LogoutState;
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

    @RequestMapping("/user/login")
    public String login(@RequestParam(value = "userEmail",defaultValue = "")String userEmail,
                            @RequestParam(value = "userPassword",defaultValue = "")String userPassword,
                            HttpSession session) throws JsonProcessingException {
        //If the user has logged in, return state HAS_LOGIN
        User currentUser = (User) session.getAttribute("loginUser");
        if(currentUser != null){
            return new Result(LoginState.HAS_LOGIN,null).asJson();
        }

        if(userEmail.length() != 0 && userPassword.length() != 0){
            //find the user by the given email address
            User user = userRepository.selectUserByEmail(userEmail);

            //if mapper returns null, return WRONG_INFO
            //which means the user does not exist
            if(user == null){
                return new Result(LoginState.WRONG_INFO,null).asJson();
            }

            //match the given password with the saved encoded password
            if(StringEncoder.matchPassword(userPassword,user.getUserPassword())){
                //if matching is successful, add the user into session
                session.setAttribute("loginUser",user);

                //update user's lastLogin and totalLogin in the database
                Date now = new Date();
                if(!userRepository.updateUserLogin(user,now)){
                    //if the update process fails, return INTERNAL_ERROR
                    return new Result(LoginState.INTERNAL_ERROR,null).asJson();
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
                return new Result(LoginState.SUCCESSFUL,user).asJson();
            }else{
                //password wrong, return WRONG_INFO
                return new Result(LoginState.WRONG_INFO,null).asJson();
            }
        }else{
            //any empty input will cause INPUT_ERROR
            return new Result(LoginState.INPUT_ERROR,null).asJson();
        }
    }

    @RequestMapping("/user/{id}/logout")
    public String logout(@PathVariable(value = "id")int uid,
                         HttpSession session) throws JsonProcessingException {
        //if the user has not logged in, return ACCESS_DENIED
        User currentUser = (User)session.getAttribute("loginUser");
        if(currentUser == null){
            return new Result(LogoutState.ACCESS_DENIED,null).asJson();
        }

        //if current user's id does not match the given uid, return ACCESS_DENIED
        if(currentUser.getUid() != uid){
            return new Result(LogoutState.ACCESS_DENIED,null).asJson();
        }

        //remove the login user from session
        session.removeAttribute("loginUser");

        //return SUCCESSFUL
        return new Result(LogoutState.SUCCESSFUL,currentUser.getUserName()).asJson();
    }
}
