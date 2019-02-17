package com.graduate.engine;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

@MapperScan(basePackages = "com.graduate.engine.mapper")
@EnableScheduling
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class EngineBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(EngineBootApplication.class, args);
    }

}
