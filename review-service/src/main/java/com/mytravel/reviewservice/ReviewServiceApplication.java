package com.mytravel.reviewservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.mytravel.reviewservice.mapper")
public class ReviewServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReviewServiceApplication.class, args);
    }

}
