package com.virtualSlime.Enum;

public enum ProfileState implements ControllerStateEnum{
    INTERNAL_ERROR(-2,"internal_error"),
    FAILED(-1,"failed"),
    ACCESS_DENIED(-1,"access_denied"),
    AS_MASTER(0,"as_master"),
    AS_GUEST(1,"as_guest"),
    UPDATE_SUCCESSFUL(2,"update_successful"),
    UPDATE_FAILED(3,"update_failed");

    private final int stateCode;
    private final String value;

    ProfileState(int stateCode, String value){
        this.stateCode = stateCode;
        this.value = value;
    }

    @Override
    public int getStateCode() {
        return this.stateCode;
    }

    @Override
    public String getValue() {
        return this.value;
    }
}
