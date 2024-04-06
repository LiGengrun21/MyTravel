package com.mytravel.reviewservice.controller;

import com.mytravel.reviewservice.entity.HotelReview;
import com.mytravel.reviewservice.service.HotelReviewService;
import com.mytravel.reviewservice.util.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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

    @Operation(summary = "create a hotel review")
    @ResponseBody
    @PostMapping("/hotel")
    public Result createHotelReview(HotelReview hotelReview) throws Exception{
        return hotelReviewService.createHotelReview(hotelReview);
    }
}
