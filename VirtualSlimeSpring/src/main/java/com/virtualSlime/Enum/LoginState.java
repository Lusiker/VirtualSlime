package com.virtualSlime.Enum;

public enum LoginState implements ControllerStateEnum{
    SUCCESSFUL(0,"login_success_"),
    INPUT_ERROR(-1,"input_error"),
    WRONG_INFO(1,"wrong_info"),
    ACCESS_DENIED(2,"access_denied");

    private final Integer stateCode;
    private String value;

    LoginState(Integer stateCode,String value) {
        this.stateCode = stateCode;
        this.value = value;
    }

    public int getStateCode(){
        return this.stateCode;
    }

    public String getValue() {
        return this.value;
    }

    public void addInfo(String info){
        this.value = value + info;
    }

    public void refreshValue(){
        if(this.stateCode == 0){
            this.value = "register_success_";
        }else if(this.stateCode == 2){
            this.value = "email_duplicate_";
        }
    }
}
