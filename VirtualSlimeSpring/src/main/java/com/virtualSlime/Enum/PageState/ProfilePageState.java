package com.virtualSlime.Enum.PageState;

public enum ProfilePageState implements ControllerStateEnum {
    FAILED(-1,"failed"),
    INTERNAL_ERROR(-2,"internal_error"),
    ACCESS_DENIED(-3,"access_denied"),
    UPDATE_FAILED(-4,"update_failed"),
    PASSWORD_TOO_SIMPLE(-5,"password_too_simple"),
    AS_MASTER(0,"as_master"),
    AS_GUEST(1,"as_guest"),
    UPDATE_SUCCESSFUL(2,"update_successful"),
    SHOW_CART(3,"show_cart"),
    SHOW_BOUGHT(4,"show_bought"),
    SHOW_FOLLOWER(5,"show_follower"),
    SHOW_FOLLOWING(6,"show_following"),
    SHOW_COMMENTS(7,"show_comments"),
    SHOW_ITEMS(8,"show_items");

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
