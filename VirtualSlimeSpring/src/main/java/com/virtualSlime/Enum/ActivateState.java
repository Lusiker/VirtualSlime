package com.virtualSlime.Enum;

public enum ActivateState implements ControllerStateEnum{
    SUCCESSFUL(0,"activate_start"),
    NOT_LOGIN(-1,"not_login"),
    WRONG_INFO(1,"wrong_info"),
    TIME_OUT(2,"time_out"),
    ACCESS_DENIED(3,"access_denied");

    private final Integer stateCode;
    private final String value;

    ActivateState(Integer stateCode,String value) {
        this.stateCode = stateCode;
        this.value = value;
    }

    @Override
    public int getStateCode(){
        return this.stateCode;
    }

    @Override
    public String getValue() {
        return this.value;
    }
}
