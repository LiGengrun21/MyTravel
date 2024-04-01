package com.mytravel.orderservice.client;

import com.mytravel.orderservice.entity.dto.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

/**
 * @author Li Gengrun
 * @date 2024/4/1 20:48
 */
@FeignClient(name = "auth-service", path = "/user")
@Component
public interface AuthServiceClient {
    @GetMapping(value = "/id")
    User getUserById(@RequestParam int id) throws Exception;
}
