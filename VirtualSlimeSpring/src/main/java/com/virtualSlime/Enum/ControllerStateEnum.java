package com.virtualSlime.Enum;

// All controller enum should implement ControllerStateEnum
// In order to be serializable into Json String
public interface ControllerStateEnum {
    int getStateCode();
    String getValue();
    Object getInfo();
    void addInfo(String info);
}
