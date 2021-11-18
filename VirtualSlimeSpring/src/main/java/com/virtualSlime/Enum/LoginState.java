package com.virtualSlime.Enum;

public enum LoginState implements ControllerStateEnum{
    SUCCESSFUL(0,"login_success_",null),
    INPUT_ERROR(-1,"input_error",null),
    WRONG_INFO(1,"wrong_info",null),
    HAS_LOGIN(2,"has_login_",null),
    ACCESS_DENIED(3,"access_denied",null);

    private final Integer stateCode;
    private String value;
    private Object info;

    LoginState(Integer stateCode,String value, Object info) {
        this.stateCode = stateCode;
        this.value = value;
        this.info = info;
    }

    @Override
    public int getStateCode(){
        return this.stateCode;
    }

    @Override
    public String getValue() {
        return this.value;
    }

    @Override
    public Object getInfo() {
        return info;
    }

    public void addInfo(String info){
        this.value = value + info;
    }

    public void setInfo(Object info){
        this.info = info;
    }

    public void resetInfo(){
        this.info = null;
    }

    public void refreshValue(){
        if(this.stateCode == 0){
            this.value = "login_success_";
        }else if(this.stateCode == 2){
            this.value = "has_login_";
        }
    }
}
