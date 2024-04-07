package com.mytravel.attractionservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.mytravel.attractionservice.mapper")
@EnableRabbit
public class AttractionServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AttractionServiceApplication.class, args);
    }

}
