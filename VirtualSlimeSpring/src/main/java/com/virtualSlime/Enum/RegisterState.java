package com.virtualSlime.Enum;

public enum RegisterState implements ControllerStateEnum{
    //states for register controller
    SUCCESSFUL(0,"register_success"),
    INPUT_ERROR(-1,"input_error"),
    PASSWORD_TOO_SIMPLE(1,"password_too_simple"),
    EMAIL_DUPLICATE(2,"email_duplicate"),
    INTERNAL_ERROR(3,"internal_error"),
    ACCESS_DENIED(4,"access_denied");

    private final Integer stateCode;
    private final String value;

    RegisterState(Integer stateCode,String value) {
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
