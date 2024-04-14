package com.mytravel.reviewservice.service.Impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mytravel.reviewservice.entity.AttractionReview;
import com.mytravel.reviewservice.entity.HotelReview;
import com.mytravel.reviewservice.entity.dto.AttractionRatingDto;
import com.mytravel.reviewservice.entity.dto.HotelRatingDto;
import com.mytravel.reviewservice.mapper.AttractionReviewMapper;
import com.mytravel.reviewservice.service.AttractionReviewService;
import com.mytravel.reviewservice.util.Result;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author Li Gengrun
 * @date 2024/4/14 10:32
 */
@Service
public class AttractionReviewServiceImpl implements AttractionReviewService {

    @Autowired
    private AttractionReviewMapper attractionReviewMapper;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public Result createAttractionReview(AttractionReview attractionReview) throws Exception {
        int attractionId= attractionReview.getAttractionId();
        int userId= attractionReview.getUserId();
        /**
         * check if the user has commented before
         */
        QueryWrapper<AttractionReview> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        queryWrapper.eq("attraction_id", attractionId);
        AttractionReview check=attractionReviewMapper.selectOne(queryWrapper);
        if (check!=null){
            return Result.FAIL("You have already reviewed the attraction.");
        }
        attractionReview.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        attractionReview.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        int result=attractionReviewMapper.insert(attractionReview);
        if (result==0){
            return Result.FAIL("Failed to post a review on the attraction");
        }
        /**
         * calculate average rating
         */
        processRating(attractionId);
        return Result.SUCCESS(attractionReview); //return the newly created hotel review
    }

    @Override
    public Result getReviewByAttractionId(int attractionId) throws Exception {
        QueryWrapper<AttractionReview> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("attraction_id",attractionId);
        List<AttractionReview> attractionReviewList=attractionReviewMapper.selectList(queryWrapper);
        return Result.SUCCESS(attractionReviewList);
    }

    private void processRating(int attractionId){
        /**
         * calculate new rating data for this hotel
         */
        QueryWrapper<AttractionReview> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("attraction_id", attractionId);
        List<AttractionReview> attractionReviewList=attractionReviewMapper.selectList(queryWrapper);
        double avgRating=0;
        if (attractionReviewList==null){
            avgRating=0; //This hotel has no rating as all
        }
        else{
            for (AttractionReview review : attractionReviewList) {
                avgRating+=review.getRating();
            }
            avgRating=avgRating/attractionReviewList.size();
        }
        /**
         * send average rating and hotelId in the form of json string to MQ
         */
        AttractionRatingDto attractionRatingDto=new AttractionRatingDto();
        attractionRatingDto.setAttractionId(attractionId);
        attractionRatingDto.setRating(avgRating);
        String jsonString= JSON.toJSONString(attractionRatingDto);
        rabbitTemplate.convertAndSend("review2.exchange", "attractionReviewRoutingKey", jsonString);
        System.out.println(attractionRatingDto);
        return;
    }
}
