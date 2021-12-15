package com.virtualSlime.Enum;

public enum ActivatePageState implements ControllerStateEnum{
    SUCCESSFUL(0,"activate_successful"),
    NOT_LOGIN(-1,"not_login"),
    FAILED(-2,"failed"),
    START(1,"activate_start"),
    WRONG_CODE(2,"wrong_info"),
    HAS_STARTED(3,"has_started"),
    HAS_ACTIVATED(4,"has_activated"),
    RESEND_SUCCESS(5,"resend_success"),
    REQUEST_TOO_FREQUENT(6,"request_too_frequent"),
    ACCESS_DENIED(7,"access_denied");

    private final Integer stateCode;
    private final String value;

    ActivatePageState(Integer stateCode, String value) {
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
