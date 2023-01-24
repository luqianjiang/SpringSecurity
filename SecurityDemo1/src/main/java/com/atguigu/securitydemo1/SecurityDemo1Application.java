package com.atguigu.securitydemo1;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.atguigu.securitydemo1.mapper")
public class SecurityDemo1Application {
    public static void main(String[] args) {
        SpringApplication.run(SecurityDemo1Application.class,args);
    }
}
