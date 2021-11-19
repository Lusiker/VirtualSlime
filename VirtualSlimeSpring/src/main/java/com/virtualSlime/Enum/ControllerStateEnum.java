package com.virtualSlime.Enum;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

// All controller enum should implement ControllerStateEnum
// In order to be serializable into Json String
@JsonAutoDetect
public interface ControllerStateEnum {
    int getStateCode();
    String getValue();
}
