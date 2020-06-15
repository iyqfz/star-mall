package com.iyqrj.starmall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.iyqrj.mall.mapper")
public class StarMallApplication {

    public static void main(String[] args) {
        SpringApplication.run(StarMallApplication.class, args);
    }

}
