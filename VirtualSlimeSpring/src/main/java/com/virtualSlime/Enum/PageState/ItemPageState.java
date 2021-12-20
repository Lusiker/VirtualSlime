package com.virtualSlime.Enum.PageState;

public enum ItemPageState implements ControllerStateEnum {
    ITEM_NOT_EXIST(-1,"item_not_exist"),
    FAILED(-2,"fail"),
    NOT_ENOUGH_CURRENCY(-3,"not_enough_currency"),
    NO_BUY_RECORD(-4,"no_buy_record"),
    INTERNAL_ERROR(-5,"internal_error"),
    SUCCESSFUL(0,"successful"),
    CREATE_SUCCESSFUL(1,"create_successful"),
    UPDATE_SUCCESSFUL(2,"update_successful"),
    ADD_SUCCESSFUL(3,"add_successful"),
    BUY_SUCCESSFUL(4,"buy_successful");

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
