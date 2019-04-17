package com.tna.ssmdemo;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
@MapperScan("com.tna.ssmdemo.mapper")
public class SsmdemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SsmdemoApplication.class, args);
    }

}

