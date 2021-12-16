package com.virtualSlime.Utils;

import com.virtualSlime.Utils.InfoWrapper.UserVerificationWrapper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class GlobalMailCollector {
    private final long EXPIRE_LIMIT = 300000;//5 min in ms

    private final HashMap<Integer, UserVerificationWrapper> map;
    private Boolean processing;
    private Boolean pause;

    private void checkMailExpired(Date now){
        //check all the existing wrapper, remove wrappers with expired time and
        //wrappers that has been checked
        for(Map.Entry<Integer, UserVerificationWrapper> entry : map.entrySet()){
            if(!entry.getValue().getIsChecking()) {
                if(entry.getValue().getIsChecked()){
                    map.remove(entry.getKey());
                    break;
                }

                if(now.getTime() - entry.getValue().getStartedTime().getTime() >= EXPIRE_LIMIT){
                    map.remove(entry.getKey());
                    break;
                }
            }
        }
    }

    public GlobalMailCollector(){
        this.map = new HashMap<Integer, UserVerificationWrapper>();
        this.processing = false;
        this.pause = false;
    }

    public void addNewMailEntity(UserVerificationWrapper wrapper){
        //both update and insert new one
        pause = true;
        map.put(wrapper.getUser().getUid(),wrapper);
        pause = false;
    }

    public int checkAvailability(int uid, String code){
        UserVerificationWrapper wrapper = map.get(uid);

        if(wrapper == null){
            //the verification failed due to time expiration
            return -1;
        }

        //lock this node to prevent being removed
        wrapper.setIsChecking(true);

        if(code.equals(wrapper.getCode())){
            //verification successful, remove wrapper
            wrapper.setIsChecked(true);
            wrapper.setIsChecking(false);

            return 0;
        }else{
            //the code is available, but input is wrong
            wrapper.setIsChecking(false);

            return 1;
        }
    }

    public boolean getIsProcessing(){
        return this.processing;
    }

    @Async
    public void go(){
        //loop while map is not empty
        processing = true;

        while(processing){
            Date lastUpdate = new Date();
            if(!pause) {
                checkMailExpired(lastUpdate);
            }

            if(map.size() == 0){
                break;
            }
        }

        processing = false;
    }
}
