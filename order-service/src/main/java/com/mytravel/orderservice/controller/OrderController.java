package com.mytravel.orderservice.controller;

import com.mytravel.orderservice.entity.dto.DetailedHotelOrderDto;
import com.mytravel.orderservice.service.HotelOrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Li Gengrun
 * @date 2024/4/1 13:38
 */
@Tag(name="Order",description = "order-service")
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    HotelOrderService hotelOrderService;

    @Operation(summary = "get the detailed hotel order including user info")
    @ResponseBody
    @GetMapping("/hotel/detailed")
    public DetailedHotelOrderDto getDetailedHotelOrder(int orderId)throws Exception{
        return hotelOrderService.getDetailedHotelOrder(orderId);
    }

    @Operation(summary = "test")
    @ResponseBody
    @GetMapping("/demo")
    public String test(){
        return "test order controller";
    }

}
