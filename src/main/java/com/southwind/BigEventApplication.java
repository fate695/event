package com.southwind;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.southwind.mapper")
public class BigEventApplication {

    public static void main(String[] args) {
        //测试git
        SpringApplication.run(BigEventApplication.class, args);
    }

}
