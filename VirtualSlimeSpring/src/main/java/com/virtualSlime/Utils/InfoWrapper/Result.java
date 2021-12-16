package com.virtualSlime.Utils.InfoWrapper;

import com.virtualSlime.Enum.PageState.ControllerStateEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Result {
    ControllerStateEnum stateEnum;
    Object returnObject;
}
