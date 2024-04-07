package com.mytravel.orderservice.controller;

import com.mytravel.orderservice.entity.dto.DetailedAttractionOrderDto;
import com.mytravel.orderservice.entity.dto.DetailedHotelOrderDto;
import com.mytravel.orderservice.service.AttractionOrderService;
import com.mytravel.orderservice.service.HotelOrderService;
import com.mytravel.orderservice.util.Result;
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

    @Autowired
    AttractionOrderService attractionOrderService;

    /**
     * 还没改成result返回值
     * @param orderId
     * @return
     * @throws Exception
     */
    @Operation(summary = "get the detailed hotel order including user info")
    @ResponseBody
    @GetMapping("/hotel/detailed")
    public DetailedHotelOrderDto getDetailedHotelOrder(int orderId)throws Exception{
        return hotelOrderService.getDetailedHotelOrder(orderId);
    }

    @Operation(summary = "get the detailed attraction order including user info")
    @ResponseBody
    @GetMapping("/attraction/detailed")
    public Result getDetailedAttractionOrder(int orderId) throws Exception{
        return attractionOrderService.getDetailedAttractionOrder(orderId);
    }

    @Operation(summary = "confirm and pay the order")
    @ResponseBody
    @GetMapping("/attraction/confirm")
    public Result conformOrder(int orderId) throws Exception{
        return attractionOrderService.confirmAttractionOrder(orderId);
    }

    @Operation(summary = "cancel the order")
    @ResponseBody
    @GetMapping("/attraction/cancel")
    public Result cancelOrder(int orderId) throws Exception{
        return attractionOrderService.cancelAttractionOrder(orderId);
    }

}
