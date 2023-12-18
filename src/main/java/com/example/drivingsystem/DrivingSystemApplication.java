package com.example.drivingsystem;

import com.example.drivingsystem.utils.SnowflakeIdWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class DrivingSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(DrivingSystemApplication.class, args);
    }

    @Bean
    public SnowflakeIdWorker createIdWorker(){
        return new SnowflakeIdWorker(0,0);
    }



}
