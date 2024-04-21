package com.mytravel.orderservice.client;

import com.mytravel.orderservice.entity.dto.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

/**
 * @author Li Gengrun
 * @date 2024/4/1 20:48
 */
@FeignClient(name = "auth-service", path = "/auth")
@Component
public interface AuthServiceClient {
    @GetMapping
    User getUserById(@RequestParam int id) throws Exception;
}
