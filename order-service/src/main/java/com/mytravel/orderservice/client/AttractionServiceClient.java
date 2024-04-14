package com.mytravel.orderservice.client;

import com.mytravel.orderservice.client.pojo.Attraction;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Li Gengrun
 * @date 2024/4/14 16:31
 */
@FeignClient(name = "attraction-service", path = "/attraction")
@Component
public interface AttractionServiceClient {

    @GetMapping
    Attraction getAttractionById(@RequestParam int attractionId) throws Exception;
}
