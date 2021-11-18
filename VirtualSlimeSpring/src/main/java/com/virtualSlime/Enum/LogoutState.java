package com.virtualSlime.Enum;

/**
 * LogoutState manage states of logout
 */
public enum LogoutState implements ControllerStateEnum{
    SUCCESSFUL(0,"logout_success"),
    INTERNAL_ERROR(1,"internal_error"),
    ACCESS_DENIED(2,"access_denied");

    private final Integer stateCode;
    private final String value;

    LogoutState(Integer stateCode,String value) {
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
