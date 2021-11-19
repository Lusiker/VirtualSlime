package com.virtualSlime.Utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.virtualSlime.Enum.ControllerStateEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Result {
    ControllerStateEnum stateEnum;
    Object returnObject;

    public String asJson() throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(this);
    }
}
