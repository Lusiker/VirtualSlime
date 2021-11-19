package com.virtualSlime.Utils;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class GlobalMailCollector {
    private final int EXPIRE_LIMIT = 300000;//5 min in ms

    private final HashMap<Integer, UserVerificationWrapper> map;
    private Boolean processing;

    private void checkMailExpired(Date now){
        //check all the existing wrapper, remove wrappers with expired time
        for(Map.Entry<Integer, UserVerificationWrapper> entry : map.entrySet()){
            if(now.getTime() - entry.getValue().getStartedTime().getTime() >= EXPIRE_LIMIT){
                if(!entry.getValue().getIsChecking()) {
                    map.remove(entry.getKey());
                }
            }
        }
    }

    public GlobalMailCollector(){
        this.map = new HashMap<Integer, UserVerificationWrapper>();
        this.processing = false;
    }

    public void addNewMailEntity(UserVerificationWrapper wrapper){
        //both update and insert new one
        map.put(wrapper.getUser().getUid(),wrapper);
    }

    public int checkAvailability(int uid, String code){
        UserVerificationWrapper wrapper = map.get(uid);

        if(wrapper == null){
            //the verification failed
            return -1;
        }

        wrapper.setIsChecking(true);
        if(code.equals(wrapper.getCode())){
            //verification successful, remove wrapper
            map.remove(uid);

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
            checkMailExpired(lastUpdate);

            if(map.size() == 0){
                break;
            }
        }

        processing = false;
    }
}
