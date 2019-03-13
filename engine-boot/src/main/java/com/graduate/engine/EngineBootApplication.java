package com.graduate.engine;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

@MapperScan(basePackages = "com.graduate.engine.mapper")
@EnableScheduling
@EnableSwagger2Doc
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class EngineBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(EngineBootApplication.class, args);
    }

}
