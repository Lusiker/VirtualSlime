package com.virtualSlime.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
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

    private String checkInvalidAccess(User loginUser,int uid) throws JsonProcessingException {
        if(loginUser == null){
            //user not logging in cannot access activate process
            return new Result(ActivateState.NOT_LOGIN,null).asJson();
        }

        if(loginUser.getUid() != uid){
            //user cannot access other user's activate process
            return new Result(ActivateState.ACCESS_DENIED,null).asJson();
        }

        if(loginUser.getUserHasActivated()){
            //user that has activated cannot access activate process
            return new Result(ActivateState.HAS_ACTIVATED,null).asJson();
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
        return new Result(ActivateState.START,null).asJson();
    }

    @RequestMapping("/user/{uid}/activate/checkCode={checkCode}")
    public String checkCode(@PathVariable(value = "uid")int uid,
                            @PathVariable(value = "checkCode")String checkCode,
                            HttpSession session) throws JsonProcessingException {
        User loginUser = (User) session.getAttribute("loginUser");
        String invalidResult = checkInvalidAccess(loginUser,uid);
        if(invalidResult != null){
            return invalidResult;
        }

        int checkResult = collector.checkAvailability(uid,checkCode);
        if(checkResult == -1){
            //verification failed due to time expiration or there is no wrapper submitted before verification
            return new Result(ActivateState.FAILED,null).asJson();
        }else if(checkResult == 1){
            //wrong code provided, return WRONG_CODE
            return new Result(ActivateState.WRONG_CODE,null).asJson();
        }else{
            //checkResult == 0
            //update loginUser's info
            userRepository.updateUserHasActivatedTrue(loginUser);
            //return SUCCESSFUL with updated loginUser
            return new Result(ActivateState.SUCCESSFUL,loginUser).asJson();
        }
    }
}
