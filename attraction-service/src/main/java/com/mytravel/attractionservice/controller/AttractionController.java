package com.mytravel.attractionservice.controller;

import com.mytravel.attractionservice.service.AttractionService;
import com.mytravel.attractionservice.util.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Li Gengrun
 * @date 2024/4/7 12:46
 */
@Tag(name="Tourist Attraction",description = "attraction-service")
@RestController
@RequestMapping("/attraction")
public class AttractionController {

    @Autowired
    private AttractionService attractionService;

//    @Operation(summary = "reserve a room")
//    @ResponseBody
//    @PostMapping("/order")
//    public Result createOrder(@RequestBody HotelOrderDto hotelOrderDto) throws Exception{
//        return
//    }

    @Operation(summary = "get a tourist attraction via attractionId")
    @ResponseBody
    @GetMapping
    public Result getAttractionById(int attractionId) throws Exception{
        return attractionService.getAttractionById(attractionId);
    }

    /**
     * 搜索符合条件的景点
     * @param
     * @return
     * @throws Exception
     */
//    @Operation(summary = "tourist attraction searching")
//    @ResponseBody
//    @GetMapping("/search")
//    public Result search(HotelSearchDto hotelSearchDto) throws Exception{
//        return roomService.search(hotelSearchDto);
//    }
}
