package com.mc.hyxinfo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages ="com.mc.hyxinfo.dataobject.mapper")
public class HyxinfoApplication {

    public static void main(String[] args) {
        SpringApplication.run(HyxinfoApplication.class, args);
    }

}
