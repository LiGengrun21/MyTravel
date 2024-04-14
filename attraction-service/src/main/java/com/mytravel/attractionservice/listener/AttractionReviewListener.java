package com.mytravel.attractionservice.listener;

import com.alibaba.fastjson.JSONObject;
import com.mytravel.attractionservice.entity.Attraction;
import com.mytravel.attractionservice.entity.dto.AttractionRatingDto;
import com.mytravel.attractionservice.mapper.AttractionMapper;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Li Gengrun
 * @date 2024/4/14 11:04
 */
@Component
@RabbitListener(queues = "attractionReview.queue")
public class AttractionReviewListener {

    @Autowired
    private AttractionMapper attractionMapper;

    /**
     * listen to review-service to get attraction rating updated
     * @param jsonString
     */
    @RabbitHandler
    public void updateRating(String jsonString){
        AttractionRatingDto attractionRatingDto = JSONObject.parseObject(jsonString, AttractionRatingDto.class);
        System.out.println("before attraction rating information:"+attractionRatingDto);
        Attraction attraction=new Attraction();
        attraction.setAttractionId(attractionRatingDto.getAttractionId());
        attraction.setRating(attractionRatingDto.getRating());
        /**
         * 迫不得已的补救方法，不知道为什么rating会出现null
         */
        if (attraction.getRating()==null){
            attraction.setRating(0.0);
        }
        System.out.println("after attraction rating information:"+attraction);
        //update rating
        attractionMapper.updateById(attraction);
    }
}


