package com.virtualSlime.Controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.virtualSlime.Entity.User;
import com.virtualSlime.Enum.LoginState;
import com.virtualSlime.Mapper.UserMapper;
import com.virtualSlime.Utils.StringEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class LoginController {
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/user/login")
    public String login(@RequestParam(value = "userEmail",defaultValue = "")String userEmail,
                            @RequestParam(value = "userPassword",defaultValue = "")String userPassword,
                            HttpSession session) throws JsonProcessingException {
        //If the user has logged in, return state HAS_LOGIN
        User currentUser = (User) session.getAttribute("loginUser");
        if(currentUser != null){
            LoginState.HAS_LOGIN.refreshValue();
            LoginState.HAS_LOGIN.resetInfo();
            LoginState.HAS_LOGIN.addInfo(currentUser.getUserName());
            LoginState.HAS_LOGIN.setInfo(currentUser);

            return objectMapper.writeValueAsString(LoginState.HAS_LOGIN);
        }

        if(userEmail.length() != 0 && userPassword.length() != 0){
            //find the user by the given email address
            QueryWrapper<User> wrapper = new QueryWrapper<User>().eq("user_email",userEmail);
            User user = userMapper.selectOne(wrapper);

            //if mapper returns null, return WRONG_INFO
            //which means the user does not exist
            if(user == null){
                return objectMapper.writeValueAsString(LoginState.WRONG_INFO);
            }

            //match the given password with the saved encoded password
            if(StringEncoder.matchPassword(userPassword,user.getUserPassword())){
                //if matching is successful, add the user into session
                session.setAttribute("loginUser",user);

                LoginState.SUCCESSFUL.refreshValue();
                LoginState.SUCCESSFUL.resetInfo();

                LoginState.SUCCESSFUL.setInfo(user);
                LoginState.SUCCESSFUL.addInfo(user.getUserName());

                //return SUCCESSFUL json↓
                //{
                //  "state" : int,
                //  "value" : String,
                //  "info"  : {
                //              "userA" : ...,
                //              ...     : ...,
                //              "userN" : ...
                //             }
                //}
                return objectMapper.writeValueAsString(LoginState.SUCCESSFUL);
            }else{
                //password wrong, return WRONG_INFO
                return objectMapper.writeValueAsString(LoginState.WRONG_INFO);
            }
        }else{
            //any empty input will cause INPUT_ERROR
            return objectMapper.writeValueAsString(LoginState.INPUT_ERROR);
        }
    }
}
