package com.mytravel.reviewservice.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mytravel.reviewservice.entity.HotelReview;
import com.mytravel.reviewservice.mapper.HotelReviewMapper;
import com.mytravel.reviewservice.service.HotelReviewService;
import com.mytravel.reviewservice.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

/**
 * @author Li Gengrun
 * @date 2024/4/6 21:25
 */
@Service
public class HotelReviewServiceImpl implements HotelReviewService {

    @Autowired
    private HotelReviewMapper hotelReviewMapper;

    @Override
    public Result createHotelReview(HotelReview hotelReview) throws Exception {
        int hotelId= hotelReview.getHotelId();
        int userId= hotelReview.getUserId();
        /**
         * check if the user has commented before
         */
        QueryWrapper<HotelReview> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        queryWrapper.eq("hotel_id", hotelId);
        HotelReview check=hotelReviewMapper.selectOne(queryWrapper);
        if (check!=null){
            return Result.FAIL("You have already reviewed the hotel.");
        }
        hotelReview.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        hotelReview.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        int result=hotelReviewMapper.insert(hotelReview);
        return Result.SUCCESS(result);
    }
}
