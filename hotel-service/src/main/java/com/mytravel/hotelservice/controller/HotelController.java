package com.mytravel.hotelservice.controller;

import com.mytravel.hotelservice.entity.Room;
import com.mytravel.hotelservice.service.RoomService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Li Gengrun
 * @date 2024/4/1 11:02
 */
@Tag(name="Hotel",description = "hotel-service")
@RestController
@RequestMapping("/hotel")
public class HotelController {

    @Autowired
    private RoomService roomService;

    /**
     *
     * @param room
     * @return orderId
     * @throws Exception
     */
    @Operation(summary = "reserve a room")
    @ResponseBody
    @PostMapping("/room/order")
    public int createOrder(Room room) throws Exception{
        return roomService.createOrder(room);
    }

    /**
     *
     * @param roomId
     * @return Room
     * @throws Exception
     */
    @Operation(summary = "get a room via roomId")
    @ResponseBody
    @GetMapping("/room/id")
    public Room getRoomById(int roomId) throws Exception{
        return roomService.getRoomById(roomId);
    }
}
