package com.saccess.forumservice.services;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class PostService {
    public static void main(String[] args) {
        SpringApplication.run(PostService.class, args);
    }

}
