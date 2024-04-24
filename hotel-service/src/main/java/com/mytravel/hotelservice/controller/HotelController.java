package com.mytravel.hotelservice.controller;

import com.mytravel.hotelservice.entity.Hotel;
import com.mytravel.hotelservice.entity.Room;
import com.mytravel.hotelservice.entity.dto.HotelOrderDto;
import com.mytravel.hotelservice.entity.dto.HotelSearchDto;
import com.mytravel.hotelservice.service.HotelService;
import com.mytravel.hotelservice.service.RoomService;
import com.mytravel.hotelservice.util.Result;
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
@CrossOrigin(origins = "*")
public class HotelController {

    @Autowired
    private RoomService roomService;

    @Autowired
    private HotelService hotelService;

    /**
     * 前端发来的数据用一个dto包装
     * @param hotelOrderDto
     * @return result
     * @throws Exception
     */
    @Operation(summary = "reserve a room")
    @ResponseBody
    @PostMapping("/room/order")
    public Result createOrder(HotelOrderDto hotelOrderDto) throws Exception{
        return roomService.createOrder(hotelOrderDto);
    }

    /**
     *
     * @param roomId
     * @return Room
     * @throws Exception
     */
    @Operation(summary = "get a room via roomId")
    @ResponseBody
    @GetMapping("/room")
    public Room getRoomById(@RequestParam int roomId) throws Exception{
        return roomService.getRoomById(roomId);
    }

    @Operation(summary = "get a hotel via hotelId")
    @ResponseBody
    @GetMapping
    public Hotel getHotelById(@RequestParam int hotelId) throws Exception{
        return hotelService.getHotelById(hotelId);
    }

    @Operation(summary = "add a new room")
    @ResponseBody
    @PostMapping("/room")
    public int createRoom(Room room) throws Exception{
        return roomService.createRoom(room);
    }

    /**
     * 除了checkin和checkout的所有字段必须填全
     * @param hotelSearchDto
     * @return
     * @throws Exception
     */
    @Operation(summary = "hotel room searching")
    @ResponseBody
    @GetMapping("/room/search")
    public Result search(HotelSearchDto hotelSearchDto) throws Exception{
        return roomService.search(hotelSearchDto);
    }

    @Operation(summary = "update a hotel")
    @ResponseBody
    @PutMapping
    public Result updateHotel(Hotel hotel) throws Exception {
        return Result.SUCCESS(hotelService.updateHotel(hotel));
    }

    @Operation(summary = "delete a hotel")
    @ResponseBody
    @DeleteMapping
    public Result deleteHotel(int hotelId) throws Exception{
        return Result.SUCCESS(hotelService.deleteHotel(hotelId));
    }
}
