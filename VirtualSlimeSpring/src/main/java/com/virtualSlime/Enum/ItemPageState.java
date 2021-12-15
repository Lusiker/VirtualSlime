package com.virtualSlime.Enum;

public enum ItemPageState implements ControllerStateEnum{
    ITEM_NOT_EXIST(-1,"item_not_exist"),
    FAIL(-2,"fail"),
    NOT_ENOUGH_CURRENCY(-3,"not_enough_currency"),
    INTERNAL_ERROR(-4,"internal_error"),
    SUCCESSFUL(0,"successful"),
    UPDATE_SUCCESSFUL(1,"update_successful"),
    ADD_SUCCESSFUL(2,"add_successful"),
    BUY_SUCCESSFUL(3,"buy_successful");

    private final int stateCode;
    private final String value;

    ItemPageState(int stateCode, String value){
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
