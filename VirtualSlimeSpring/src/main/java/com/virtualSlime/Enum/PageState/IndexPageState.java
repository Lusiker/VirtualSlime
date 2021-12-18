package com.virtualSlime.Enum.PageState;

public enum IndexPageState implements ControllerStateEnum {
    HELLO(0,"hello from virtual slime"),
    SUCCESSFUL(1,"successful"),
    FAILED(-1,"failed"),
    NO_MORE(-2,"no_more");

    private final Integer stateCode;
    private final String value;

    IndexPageState(Integer stateCode,String value) {
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
