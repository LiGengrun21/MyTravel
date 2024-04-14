package com.mytravel.orderservice.client;

import com.mytravel.orderservice.client.pojo.Hotel;
import com.mytravel.orderservice.client.pojo.Room;
import com.mytravel.orderservice.entity.dto.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Li Gengrun
 * @date 2024/4/14 12:57
 */
@FeignClient(name = "hotel-service", path = "/hotel")
@Component
public interface HotelServiceClient {

    @GetMapping(value = "/room")
    Room getRoomById(@RequestParam int roomId) throws Exception;

    @GetMapping
    Hotel getHotelById(@RequestParam int hotelId) throws Exception;
}
