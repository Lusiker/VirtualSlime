package com.virtualSlime.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.virtualSlime.Entity.User;
import com.virtualSlime.Enum.RegisterState;
import com.virtualSlime.Service.PasswordSimplicityChecker;
import com.virtualSlime.Service.UserRepository;
import com.virtualSlime.Utils.Result;
import com.virtualSlime.Utils.StringEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

@RestController
public class RegisterController {
    @Resource
    private UserRepository userRepository;
    @Resource
    private ObjectMapper objectMapper;

    /**
     * Read all users into a list, check their email one by one to find if duplicate exists
     * @param email - given email string
     * @return - a boolean value of whether the given email has been registered into the database
     */
    private boolean checkEmailDuplicate(String email){
        User user = userRepository.selectUserByEmail(email);

        return user != null;
    }

    /**
     *
     * @param newUserEmail - default value ""
     * @param newUserPassword - this password SHOULD NOT be too long - default value ""
     * @return - a state enum json including an int type code and a brief introduction
     * @throws JsonProcessingException - under no circumstance will this exception be thrown (I guess...)
     */
    @RequestMapping(value = "/register")
    public String userRegister(@RequestParam(value = "useremail",defaultValue = "")String newUserEmail,
                               @RequestParam(value = "password",defaultValue = "")String newUserPassword
                               ) throws JsonProcessingException {
//        if(currentUser != null){
//            //if the user has logged in, return ACCESS_DENIED
//            return objectMapper.writeValueAsString(new Result(RegisterState.ACCESS_DENIED,null));
//        }

        if(newUserEmail.length() != 0 && newUserPassword.length() != 0) {
            if(PasswordSimplicityChecker.checkPasswordSimplicity(newUserPassword)){
                //password too simple
                return objectMapper.writeValueAsString(new Result(RegisterState.PASSWORD_TOO_SIMPLE,null));
            }
            if(checkEmailDuplicate(newUserEmail)){
                //duplicate email address detected
                return objectMapper.writeValueAsString(new Result(RegisterState.EMAIL_DUPLICATE,null));
            }

            //now is used for salting the password and generating user's initial username
            Date now = new Date();
            String userNameTemp = Long.toString(now.getTime());
            int charSum = 0;
            for(char c : newUserEmail.toCharArray()){
                charSum += c;
            }
            userNameTemp += Integer.toString(charSum);
            int len = userNameTemp.length();
            userNameTemp = userNameTemp.substring(len / 2 - 3, len);
            // user's initial username is generated from the time the account is created
            // and the given email address
            String newUserName = "user_" + userNameTemp;

            // user password is encoded here
            String encodedUserPassword = StringEncoder.userPasswordEncode(newUserPassword);

            //insert new user into the database
            User registeringUser = new User(newUserName, newUserEmail, encodedUserPassword);
            //return value success or failure
            if(userRepository.insertUser(registeringUser)){
                return objectMapper.writeValueAsString(new Result(RegisterState.SUCCESSFUL,null));
            } else {
                return objectMapper.writeValueAsString(new Result(RegisterState.INTERNAL_ERROR,null));
            }
        } else {
            //any empty parameter will cause input_error
            return objectMapper.writeValueAsString(new Result(RegisterState.INPUT_ERROR, null));
        }
    }
}
