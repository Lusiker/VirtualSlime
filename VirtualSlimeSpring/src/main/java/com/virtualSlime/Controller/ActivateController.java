package com.virtualSlime.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.virtualSlime.Entity.User;
import com.virtualSlime.Enum.ActivatePageState;
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

    private String checkInvalidAccess(User user) throws JsonProcessingException {
        if(user == null){
            //user may not exist
            return objectMapper.writeValueAsString(new Result(ActivatePageState.FAILED,null));
        }

        if(user.getUserHasActivated()){
            //user that has activated cannot access activate process
            return objectMapper.writeValueAsString(new Result(ActivatePageState.HAS_ACTIVATED,null));
        }

        return null;
    }

    private UserVerificationWrapper getVerificationWrapper(User user){
        Date now = new Date();
        String code = NumberProcessing.getRandomFourDigitNumber(now.getTime());

        return new UserVerificationWrapper(user,now,code,false,false);
    }

    @RequestMapping("/user/{uid}/activate")
    public String activate(@PathVariable(value = "uid")String newUid) throws JsonProcessingException {
        int uid;
        try{
            uid = Integer.parseInt(newUid);
        }catch (Exception e){
            return objectMapper.writeValueAsString(new Result(ActivatePageState.FAILED,newUid));
        }

        User user = userRepository.selectUserByUid(uid);
        String invalidResult = checkInvalidAccess(user);
        if(invalidResult != null){
            return invalidResult;
        }

        //create a verification wrapper
        UserVerificationWrapper verificationWrapper = getVerificationWrapper(user);

        //send the verification code mail
        sender.sendCertificationCode(verificationWrapper);

        //add the wrapper to the global collector
        collector.addNewMailEntity(verificationWrapper);
        if(!collector.getIsProcessing()){
            collector.go();
        }

        //return START
        return objectMapper.writeValueAsString(new Result(ActivatePageState.START,null));
    }

    @RequestMapping("/user/{uid}/activate/checkCode={checkCode}")
    public String checkCode(@PathVariable(value = "uid")String newUid,
                            @PathVariable(value = "checkCode")String checkCode) throws JsonProcessingException {
        int uid;
        try{
            uid = Integer.parseInt(newUid);
        }catch (Exception e){
            return objectMapper.writeValueAsString(new Result(ActivatePageState.FAILED,newUid));
        }

        User user = userRepository.selectUserByUid(uid);
        String invalidResult = checkInvalidAccess(user);
        if(invalidResult != null){
            return invalidResult;
        }

        int checkResult = collector.checkAvailability(uid,checkCode);
        if(checkResult == -1){
            //verification failed due to time expiration or there is no wrapper submitted before verification
            return objectMapper.writeValueAsString(new Result(ActivatePageState.FAILED,null));
        }else if(checkResult == 1){
            //wrong code provided, return WRONG_CODE
            return objectMapper.writeValueAsString(new Result(ActivatePageState.WRONG_CODE,null));
        }else{
            //checkResult == 0
            //update loginUser's info
            if(!userRepository.updateUserHasActivatedTrue(user)){
                return objectMapper.writeValueAsString(new Result(ActivatePageState.FAILED,null));
            }
            //return SUCCESSFUL with updated loginUser
            return objectMapper.writeValueAsString(new Result(ActivatePageState.SUCCESSFUL,user));
        }
    }
}
