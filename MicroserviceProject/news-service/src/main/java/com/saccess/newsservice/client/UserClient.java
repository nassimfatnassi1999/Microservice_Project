package com.saccess.newsservice.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "USER-SERVICE")
public interface UserClient {
}
