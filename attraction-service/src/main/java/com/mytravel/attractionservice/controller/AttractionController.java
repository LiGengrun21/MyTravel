package com.mytravel.attractionservice.controller;

import com.mytravel.attractionservice.entity.Attraction;
import com.mytravel.attractionservice.entity.dto.AttractionOrderDto;
import com.mytravel.attractionservice.entity.dto.AttractionSearchDto;
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

    @Operation(summary = "make attraction reservations")
    @ResponseBody
    @PostMapping("/order")
    public Result createOrder(@RequestBody AttractionOrderDto attractionOrderDto) throws Exception{
        return attractionService.createOrder(attractionOrderDto);
    }

    @Operation(summary = "get a tourist attraction via attractionId")
    @ResponseBody
    @GetMapping
    public Result getAttractionById(int attractionId) throws Exception{
        return attractionService.getAttractionById(attractionId);
    }

    @Operation(summary = "create a new tourist attraction (used by admin)")
    @ResponseBody
    @PostMapping
    public Result createAttraction(Attraction attraction) throws Exception{
        return attractionService.createAttraction(attraction);
    }

    /**
     * 搜索符合条件的景点
     * @param attractionSearchDto
     * @return
     * @throws Exception
     */
    @Operation(summary = "tourist attraction searching")
    @ResponseBody
    @GetMapping("/search")
    public Result search(AttractionSearchDto attractionSearchDto) throws Exception{
        return attractionService.search(attractionSearchDto);
    }
}
