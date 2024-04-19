package com.saccess.newsservice.client;

import com.saccess.newsservice.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(value = "USER-SERVICE")
public interface UserClient {

    @GetMapping("/user/getAllUsers")
    List<UserDto> getAllUsers();
}
