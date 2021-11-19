package com.virtualSlime.Utils;

import com.virtualSlime.Enum.ControllerStateEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Result {
    ControllerStateEnum stateEnum;
    Object returnObject;
}
