package com.virtualSlime.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.virtualSlime.Entity.User;
import com.virtualSlime.Enum.RegisterState;
import com.virtualSlime.Utils.StringEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
public class RegisterController {
    @Autowired
    private ObjectMapper objectMapper;

    /**
     * checkPasswordSimplicity - check whether the password is too simple for safety concern
     * @param password - user password waiting for checking
     * @return the bool result of checking
     */
    private boolean checkPasswordSimplicity(String password){

        return false;
    }

    @RequestMapping(value = "/register")
    public String userRegister(@RequestParam(value = "userEmail",defaultValue = "")String newUserEmail,
                               @RequestParam(value = "userPassword",defaultValue = "")String newUserPassword) throws JsonProcessingException {
        if(newUserEmail.length() != 0 && newUserPassword.length() != 0) {
            if(!checkPasswordSimplicity(newUserPassword)){
                return objectMapper.writeValueAsString(RegisterState.PASSWORD_TOO_SIMPLE);
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
                userNameTemp = userNameTemp.substring(len / 2, len);
            }
            // user's initial username is generated from the time the account is created
            // and the given email address
            String newUserName = "user_" + userNameTemp;

            // user password is encoded here
            // the salt value should be *now* above
            String encodedUserPassword = StringEncoder.userPasswordEncode(newUserPassword,now);

            User registeringUser = new User(newUserName,newUserEmail, encodedUserPassword);
            RegisterState.SUCCESSFUL.refreshValue();
            RegisterState.SUCCESSFUL.addInfo(newUserName);

            return objectMapper.writeValueAsString(RegisterState.SUCCESSFUL);
        }else{
            return objectMapper.writeValueAsString(RegisterState.INPUT_ERROR);
        }

    }
}
