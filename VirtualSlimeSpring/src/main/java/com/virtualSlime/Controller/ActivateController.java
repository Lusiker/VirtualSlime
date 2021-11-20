package com.virtualSlime.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.virtualSlime.Entity.User;
import com.virtualSlime.Enum.ActivateState;
import com.virtualSlime.Service.CommonMailSender;
import com.virtualSlime.Service.UserRepository;
import com.virtualSlime.Utils.Result;
import com.virtualSlime.Utils.GlobalMailCollector;
import com.virtualSlime.Utils.NumberProcessing;
import com.virtualSlime.Utils.UserVerificationWrapper;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
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
    @Resource
    private CommonMailSender sender;
    @Resource
    private GlobalMailCollector collector;
    @Resource
    private UserRepository userRepository;
    @Resource
    private ObjectMapper objectMapper;

    private String checkInvalidAccess(User loginUser,int uid) throws JsonProcessingException {
        if(loginUser == null){
            //user not logging in cannot access activate process
            return objectMapper.writeValueAsString(new Result(ActivateState.NOT_LOGIN,null));
        }

        if(loginUser.getUid() != uid){
            //user cannot access other user's activate process
            return objectMapper.writeValueAsString(new Result(ActivateState.ACCESS_DENIED,null));
        }

        if(loginUser.getUserHasActivated()){
            //user that has activated cannot access activate process
            return objectMapper.writeValueAsString(new Result(ActivateState.HAS_ACTIVATED,null));
        }

        return null;
    }

    private UserVerificationWrapper getVerificationWrapper(User loginUser){
        Date now = new Date();
        String code = NumberProcessing.getRandomFourDigitNumber(now.getTime());

        return new UserVerificationWrapper(loginUser,now,code,false,false);
    }

    @RequestMapping("/user/{uid}/activate")
    public String activate(@PathVariable(value = "uid")String newUid,
                           HttpSession session) throws JsonProcessingException {
        int uid;
        try{
            uid = Integer.parseInt(newUid);
        }catch (Exception e){
            return objectMapper.writeValueAsString(new Result(ActivateState.FAILED,newUid));
        }

        User loginUser = (User) session.getAttribute("loginUser");
        String invalidResult = checkInvalidAccess(loginUser,uid);
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
        return objectMapper.writeValueAsString(new Result(ActivateState.START,null));
    }

    @RequestMapping("/user/{uid}/activate/checkCode={checkCode}")
    public String checkCode(@PathVariable(value = "uid")String newUid,
                            @PathVariable(value = "checkCode")String checkCode,
                            HttpSession session) throws JsonProcessingException {
        int uid;
        try{
            uid = Integer.parseInt(newUid);
        }catch (Exception e){
            return objectMapper.writeValueAsString(new Result(ActivateState.FAILED,newUid));
        }

        User loginUser = (User) session.getAttribute("loginUser");
        String invalidResult = checkInvalidAccess(loginUser,uid);
        if(invalidResult != null){
            return invalidResult;
        }

        int checkResult = collector.checkAvailability(uid,checkCode);
        if(checkResult == -1){
            //verification failed due to time expiration or there is no wrapper submitted before verification
            return objectMapper.writeValueAsString(new Result(ActivateState.FAILED,null));
        }else if(checkResult == 1){
            //wrong code provided, return WRONG_CODE
            return objectMapper.writeValueAsString(new Result(ActivateState.WRONG_CODE,null));
        }else{
            //checkResult == 0
            //update loginUser's info
            userRepository.updateUserHasActivatedTrue(loginUser);
            //return SUCCESSFUL with updated loginUser
            return objectMapper.writeValueAsString(new Result(ActivateState.SUCCESSFUL,loginUser));
        }
    }
}
