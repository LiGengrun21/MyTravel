package com.mytravel.reviewservice.service.Impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mytravel.reviewservice.entity.HotelReview;
import com.mytravel.reviewservice.entity.dto.HotelRatingDto;
import com.mytravel.reviewservice.entity.dto.HotelReviewParam;
import com.mytravel.reviewservice.mapper.HotelReviewMapper;
import com.mytravel.reviewservice.service.HotelReviewService;
import com.mytravel.reviewservice.util.Result;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author Li Gengrun
 * @date 2024/4/6 21:25
 */
@Service
public class HotelReviewServiceImpl implements HotelReviewService {

    @Autowired
    private HotelReviewMapper hotelReviewMapper;

    @Autowired
    private RabbitTemplate rabbitTemplate;

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
        /**
         * calculate average rating
         */
        processRating(hotelId);
        return Result.SUCCESS(hotelReview); //return the newly created hotel review
    }

    @Override
    public Result updateHotelReview(HotelReview hotelReview) throws Exception {
        hotelReview.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        int result=hotelReviewMapper.updateById(hotelReview);
        if (result==1){
            /**
             * calculate average rating
             */
            processRating(hotelReview.getHotelId());
            return Result.SUCCESS(hotelReview);
        }
        else{
            return Result.FAIL("Tried to update hotel review but failed.");
        }
    }

    @Override
    public Result getReviewByHotelId(int hotelId) throws Exception {
        QueryWrapper<HotelReview> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("hotel_id",hotelId);
        List<HotelReview> hotelReviewList=hotelReviewMapper.selectList(queryWrapper);
        return Result.SUCCESS(hotelReviewList);
    }

    @Override
    public Result deleteHotelReview(HotelReviewParam hotelReviewParam) throws Exception {
        int userId= hotelReviewParam.getUserId();
        int hotelId= hotelReviewParam.getHotelId();
        QueryWrapper<HotelReview> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        queryWrapper.eq("hotel_id",hotelId);
        HotelReview hotelReview=hotelReviewMapper.selectOne(queryWrapper);
        if (hotelReview==null){
            return Result.FAIL("The user's review cannot be found.");
        }
        int result=hotelReviewMapper.deleteById(hotelReview);
        if (result==0){
            return Result.FAIL("Delete the user's review fails");
        }
        /**
         * calculate average rating
         */
        processRating(hotelId);
        return Result.SUCCESS();
    }

    private void processRating(int hotelId){
        /**
         * calculate new rating data for this hotel
         */
        QueryWrapper<HotelReview> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("hotel_id", hotelId);
        List<HotelReview> hotelReviewList=hotelReviewMapper.selectList(queryWrapper);
        double avgRating=0;
        if (hotelReviewList==null){
            avgRating=0; //This hotel has no rating as all
        }
        else{
            for (HotelReview review : hotelReviewList) {
                avgRating+=review.getRating();
            }
            avgRating=avgRating/hotelReviewList.size();
        }
        /**
         * send average rating and hotelId in the form of json string to MQ
         */
        HotelRatingDto hotelRatingDto=new HotelRatingDto();
        hotelRatingDto.setHotelId(hotelId);
        hotelRatingDto.setRating(avgRating);
        String jsonString= JSON.toJSONString(hotelRatingDto);
        rabbitTemplate.convertAndSend("review.exchange", "hotelReviewRoutingKey", jsonString);
        System.out.println(hotelRatingDto);
        return;
    }
}
