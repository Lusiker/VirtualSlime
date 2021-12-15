package com.virtualSlime.Enum;

public enum ProfilePageState implements ControllerStateEnum{
    FAILED(-1,"failed"),
    INTERNAL_ERROR(-2,"internal_error"),
    ACCESS_DENIED(-3,"access_denied"),
    UPDATE_FAILED(-4,"update_failed"),
    AS_MASTER(0,"as_master"),
    AS_GUEST(1,"as_guest"),
    UPDATE_SUCCESSFUL(2,"update_successful"),
    SHOW_CART(3,"show_cart"),
    SHOW_BOUGHT(4,"show_bought");

    private final int stateCode;
    private final String value;

    ProfilePageState(int stateCode, String value){
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
