package com.virtualSlime.Enum;

/**
 * LoginState manage states of login
 */
public enum LoginState implements ControllerStateEnum{
    SUCCESSFUL(0,"login_success"),
    INPUT_ERROR(-1,"input_error"),
    WRONG_INFO(1,"wrong_info"),
    HAS_LOGIN(2,"has_login"),
    INTERNAL_ERROR(3,"internal_error"),
    ACCESS_DENIED(4,"access_denied");

    private final Integer stateCode;
    private final String value;

    LoginState(Integer stateCode,String value) {
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
