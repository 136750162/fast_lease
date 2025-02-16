package com.fl.fastLease;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
@MapperScan("com.fl.fastLease.mapper")
public class FastLeaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(FastLeaseApplication.class, args);
    }

}
