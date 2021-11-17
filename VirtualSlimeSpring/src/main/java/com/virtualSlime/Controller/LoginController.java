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
    public String login(@RequestParam(value = "email",defaultValue = "")String userEmail,
                            @RequestParam(value = "password",defaultValue = "")String userPassword,
                            HttpSession session) throws JsonProcessingException {
        if(userEmail.length() != 0 && userPassword.length() != 0){
            QueryWrapper<User> wrapper = new QueryWrapper<User>().eq("user_email",userEmail);
            User user = userMapper.selectOne(wrapper);

            if(user == null){
                return objectMapper.writeValueAsString(LoginState.WRONG_INFO);
            }

            if(StringEncoder.matchPassword(userPassword,user.getUserPassword())){
                session.setAttribute("loginUser",user);

                LoginState.SUCCESSFUL.refreshValue();
                LoginState.SUCCESSFUL.addInfo(user.getUserName());

                return objectMapper.writeValueAsString(LoginState.SUCCESSFUL);
            }else{
                return objectMapper.writeValueAsString(LoginState.WRONG_INFO);
            }
        }else{
            return objectMapper.writeValueAsString(LoginState.INPUT_ERROR);
        }
    }
}
