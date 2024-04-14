package com.mytravel.reviewservice.controller;

import com.mytravel.reviewservice.entity.AttractionReview;
import com.mytravel.reviewservice.entity.HotelReview;
import com.mytravel.reviewservice.entity.dto.HotelReviewParam;
import com.mytravel.reviewservice.service.AttractionReviewService;
import com.mytravel.reviewservice.service.HotelReviewService;
import com.mytravel.reviewservice.util.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Li Gengrun
 * @date 2024/4/6 21:27
 */
@Tag(name="Review",description = "review-service")
@RestController
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    private HotelReviewService hotelReviewService;

    @Autowired
    private AttractionReviewService attractionReviewService;

    @Operation(summary = "create a hotel review")
    @ResponseBody
    @PostMapping("/hotel")
    public Result createHotelReview(HotelReview hotelReview) throws Exception{
        return hotelReviewService.createHotelReview(hotelReview);
    }

    /**
     * 不改的字段不用上传，只要传主键和要修改的字段
     * @param hotelReview
     * @return
     * @throws Exception
     */
    @Operation(summary = "update the hotel review")
    @ResponseBody
    @PutMapping("/hotel")
    public Result updateHotelReview(HotelReview hotelReview) throws Exception{
        return hotelReviewService.updateHotelReview(hotelReview);
    }

    /**
     * 展示某个酒店的评论区
     * @param hotelId
     * @return
     * @throws Exception
     */
    @Operation(summary = "get the review list of a hotel")
    @ResponseBody
    @GetMapping("/hotel")
    public Result getReviewByHotelId(int hotelId) throws Exception{
        return hotelReviewService.getReviewByHotelId(hotelId);
    }

    /**
     * 物理删除
     * @param hotelReviewParam
     * @return
     * @throws Exception
     */
    @Operation(summary = "delete the hotel review of a user")
    @ResponseBody
    @DeleteMapping("/hotel")
    public Result deleteHotelReview(HotelReviewParam hotelReviewParam) throws Exception{
        return hotelReviewService.deleteHotelReview(hotelReviewParam);
    }

    /**
     * 展示某个景点的评论区
     * @param attractionId
     * @return
     * @throws Exception
     */
    @Operation(summary = "get the review list of a tourist attraction")
    @ResponseBody
    @GetMapping("attraction")
    public Result getReviewByAttractionId(int attractionId) throws Exception{
        return attractionReviewService.getReviewByAttractionId(attractionId);
    }

    /**
     * 发表景点评论
     * @param attractionReview
     * @return
     * @throws Exception
     */
    @Operation(summary = "post a new review on the attraction")
    @ResponseBody
    @PostMapping("/attraction")
    public Result createAttractionReview(AttractionReview attractionReview) throws Exception{
        return attractionReviewService.createAttractionReview(attractionReview);
    }
}
