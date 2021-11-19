package com.virtualSlime.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.virtualSlime.Entity.User;
import com.virtualSlime.Enum.ActivateState;
import com.virtualSlime.Service.CommonMailSender;
import com.virtualSlime.Service.UserRepository;
import com.virtualSlime.Utils.ControllerResultWrapper;
import com.virtualSlime.Utils.GlobalMailCollector;
import com.virtualSlime.Utils.NumberProcessing;
import com.virtualSlime.Utils.UserVerificationWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * Activate - /user/uid/activate - for logged-in user to get a mail with a verification code
 *      the code will expire within 5 min. Invoke this api for the second time will overwrite
 *      previous code and time.
 * CheckCode - /user/uid/activate/checkCode=value - if value equals the one sent to user's mail,
 *      the user's hasActivated will be set to true and state to NORMAL
 */
@RestController
public class ActivateController {
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private CommonMailSender sender;
    @Autowired
    private GlobalMailCollector collector;
    @Autowired
    private UserRepository userRepository;

    private String checkInvalidAccess(User loginUser,int uid,HttpSession session) throws JsonProcessingException {
        if(loginUser == null){
            //user not logging in cannot access activate process
            ControllerResultWrapper wrapper = new ControllerResultWrapper(ActivateState.NOT_LOGIN,null);
            return objectMapper.writeValueAsString(wrapper);
        }

        if(loginUser.getUid() != uid){
            //user cannot access other user's activate process
            ControllerResultWrapper wrapper = new ControllerResultWrapper(ActivateState.ACCESS_DENIED,null);
            return objectMapper.writeValueAsString(wrapper);
        }

        if(loginUser.getUserHasActivated()){
            //user that has activated cannot access activate process
            ControllerResultWrapper wrapper = new ControllerResultWrapper(ActivateState.HAS_ACTIVATED,null);
            return objectMapper.writeValueAsString(wrapper);
        }

        return null;
    }

    private UserVerificationWrapper getVerificationWrapper(User loginUser){
        Date now = new Date();
        String code = NumberProcessing.getRandomFourDigitNumber(now.getTime());

        return new UserVerificationWrapper(loginUser,now,code,false);
    }

    @RequestMapping("/user/{uid}/activate")
    public String activate(@PathVariable(value = "uid")int uid,
                           HttpSession session) throws JsonProcessingException {
        User loginUser = (User) session.getAttribute("loginUser");
        String invalidResult = checkInvalidAccess(loginUser,uid,session);
        if(invalidResult != null){
            return invalidResult;
        }

        //create a verification wrapper
        UserVerificationWrapper verificationWrapper = getVerificationWrapper(loginUser);

        //send the verification code mail
        sender.sendCertificationCode(verificationWrapper);

        //add the wrapper to the global collector
        collector.addNewMailEntity(verificationWrapper);
        if(!collector.getIsProcessing()){
            collector.go();
        }

        //return START
        ControllerResultWrapper wrapper = new ControllerResultWrapper(ActivateState.START,null);
        return objectMapper.writeValueAsString(wrapper);
    }

    @RequestMapping("/user/{uid}/activate/checkCode={checkCode}")
    public String checkCode(@PathVariable(value = "uid")int uid,
                            @PathVariable(value = "checkCode")String checkCode,
                            HttpSession session) throws JsonProcessingException {
        User loginUser = (User) session.getAttribute("loginUser");
        String invalidResult = checkInvalidAccess(loginUser,uid,session);
        if(invalidResult != null){
            return invalidResult;
        }

        int checkResult = collector.checkAvailability(uid,checkCode);
        if(checkResult == -1){
            //verification failed due to time expiration or there is no wrapper submitted before verification
            ControllerResultWrapper wrapper = new ControllerResultWrapper(ActivateState.FAILED,null);
            return objectMapper.writeValueAsString(wrapper);
        }else if(checkResult == 1){
            //wrong code provided, return WRONG_CODE
            ControllerResultWrapper wrapper = new ControllerResultWrapper(ActivateState.WRONG_CODE,null);
            return objectMapper.writeValueAsString(wrapper);
        }else{
            //checkResult == 0
            //update loginUser's info
            userRepository.updateUserHasActivatedTrue(loginUser);
            //return SUCCESSFUL with updated loginUser
            ControllerResultWrapper wrapper = new ControllerResultWrapper(ActivateState.SUCCESSFUL,loginUser);
            return objectMapper.writeValueAsString(wrapper);
        }
    }
}
