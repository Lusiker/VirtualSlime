package com.virtualSlime;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@MapperScan("com.virtualSlime.Mapper")
public class VirtualSlimeApplication {

    public static void main(String[] args) {
        SpringApplication.run(VirtualSlimeApplication.class, args);
    }

}
