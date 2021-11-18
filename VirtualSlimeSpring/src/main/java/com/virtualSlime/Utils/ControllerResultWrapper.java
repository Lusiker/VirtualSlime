package com.virtualSlime.Utils;

import com.virtualSlime.Enum.ControllerStateEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ControllerResultWrapper {
    ControllerStateEnum stateEnum;
    Object returnValue;
}
