package com.virtualSlime.Enum;

public enum IndexPageState implements ControllerStateEnum{
    SUCCESSFUL(0,"hello from virtual slime");

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
