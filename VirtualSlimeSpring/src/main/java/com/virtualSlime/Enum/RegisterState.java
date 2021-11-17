package com.virtualSlime.Enum;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public enum RegisterState implements ControllerStateEnum{
    //states for register controller
    SUCCESSFUL(0,"register_success_"),
    INPUT_ERROR(-1,"input_error"),
    PASSWORD_TOO_SIMPLE(1,"password_too_simple"),
    ;

    @JsonProperty("stateCode")
    private final Integer stateCode;
    @JsonProperty("value")
    private String value;

    RegisterState(Integer stateCode,String value) {
        this.stateCode = stateCode;
        this.value = value;
    }

    public int getStateCode(){
        return this.stateCode;
    }

    public String getValue() {
        return this.value;
    }

    public void addInfo(String info){
        this.value = value + info;
    }

    public void refreshValue(){
        if(this.stateCode == 0){
            this.value = "register_success_";
        }
    }
}
