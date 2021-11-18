package com.virtualSlime.Config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.virtualSlime.Enum.ControllerStateEnum;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class GlobalConfig {

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer enumSerializer(){
        return jacksonObjectMapperBuilder -> jacksonObjectMapperBuilder.serializerByType(ControllerStateEnum.class, new JsonSerializer<ControllerStateEnum>() {
            @Override
            public void serialize(ControllerStateEnum stateEnum, JsonGenerator generator, SerializerProvider provider) throws IOException {
                generator.writeStartObject();
                generator.writeNumberField("state",stateEnum.getStateCode());
                generator.writeStringField("value",stateEnum.getValue());
                generator.writeObjectField("info",stateEnum.getInfo());
                generator.writeEndObject();
            }
        });
    }
}
