package com.virtualslime;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.virtualslime.Mapper")
public class VirtualSlimeApplication {

    public static void main(String[] args) {
        SpringApplication.run(VirtualSlimeApplication.class, args);
    }

}
