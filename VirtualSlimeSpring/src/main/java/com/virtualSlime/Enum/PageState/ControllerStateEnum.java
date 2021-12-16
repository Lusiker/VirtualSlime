package com.virtualSlime.Enum.PageState;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

// All controller enum should implement ControllerStateEnum
// In order to be serializable into Json String
@JsonAutoDetect
public interface ControllerStateEnum {
    int getStateCode();
    String getValue();
}
