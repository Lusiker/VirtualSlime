package com.virtualSlime.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.virtualSlime.Entity.User;
import com.virtualSlime.Enum.RegisterState;
import com.virtualSlime.Mapper.UserMapper;
import com.virtualSlime.Service.PasswordSimplicityChecker;
import com.virtualSlime.Utils.StringEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class RegisterController {
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private UserMapper userMapper;

    /**
     * Read all users into a list, check their email one by one to find if duplicate exists
     * @param email
     * @return - a boolean value of whether the given email has been registered into the database
     */
    private boolean checkEmailDuplicate(String email){
        List<User> list = userMapper.selectList(null);

        for(User user : list){
            if(user.getUserEmail().equals(email)){
                return true;
            }
        }

        return false;
    }

    /**
     *
     * @param newUserEmail - default value ""
     * @param newUserPassword - this password SHOULD NOT be too long - default value ""
     * @return - a state enum json including an int type code and a brief introduction
     * @throws JsonProcessingException - under no circumstance will this exception be thrown (I guess...)
     */
    @RequestMapping(value = "/register")
    public String userRegister(@RequestParam(value = "userEmail",defaultValue = "")String newUserEmail,
                               @RequestParam(value = "userPassword",defaultValue = "")String newUserPassword) throws JsonProcessingException {
        if(newUserEmail.length() != 0 && newUserPassword.length() != 0) {
            if(!PasswordSimplicityChecker.checkPasswordSimplicity(newUserPassword)){
                //password too simple
                return objectMapper.writeValueAsString(RegisterState.PASSWORD_TOO_SIMPLE);
            }
            if(checkEmailDuplicate(newUserEmail)){
                //duplicate email address detected
                RegisterState.EMAIL_DUPLICATE.refreshValue();
                RegisterState.EMAIL_DUPLICATE.addInfo(newUserEmail);

                return objectMapper.writeValueAsString(RegisterState.EMAIL_DUPLICATE);
            }

            //now is used for salting the password and generating user's initial username
            long now = new Date().getTime();
            String userNameTemp = Long.toString(now);
            int charSum = 0;
            for(char c : newUserEmail.toCharArray()){
                charSum += c;
            }
            userNameTemp += Integer.toString(charSum);
            int len = userNameTemp.length();
            if(len >= 10){
                userNameTemp = userNameTemp.substring(len / 2 - 3, len);
            }
            // user's initial username is generated from the time the account is created
            // and the given email address
            String newUserName = "user_" + userNameTemp;

            // user password is encoded here
            String encodedUserPassword = StringEncoder.userPasswordEncode(newUserPassword);

            //insert new user into the database
            User registeringUser = new User(newUserName, newUserEmail, encodedUserPassword);
            userMapper.insert(registeringUser);

            RegisterState.SUCCESSFUL.refreshValue();
            RegisterState.SUCCESSFUL.addInfo(newUserName);

            return objectMapper.writeValueAsString(RegisterState.SUCCESSFUL);
        }else{
            //any empty parameter will cause input_error
            return objectMapper.writeValueAsString(RegisterState.INPUT_ERROR);
        }

    }
}
