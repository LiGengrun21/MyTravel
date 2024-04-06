package com.mytravel.hotelservice.listener;

import com.alibaba.fastjson.JSONObject;
import com.mytravel.hotelservice.entity.Hotel;
import com.mytravel.hotelservice.entity.dto.HotelRatingDto;
import com.mytravel.hotelservice.mapper.HotelMapper;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Li Gengrun
 * @date 2024/4/6 23:56
 */
@Component
@RabbitListener(queues = "hotelReview.queue")
public class HotelReviewListener {

    @Autowired
    private HotelMapper hotelMapper;

    /**
     * listen to review-service to get hotel rating updated
     * @param jsonString
     */
    @RabbitHandler
    public void updateRating(String jsonString){
        HotelRatingDto hotelRatingDto = JSONObject.parseObject(jsonString, HotelRatingDto.class);
        System.out.println("before hotel rating information:"+hotelRatingDto);
        Hotel hotel=new Hotel();
        hotel.setHotelId(hotelRatingDto.getHotelId());
        hotel.setRating(hotelRatingDto.getRating());
        /**
         * 迫不得已的补救方法，不知道为什么rating会出现null
         */
        if (hotel.getRating()==null){
            hotel.setRating(0.0);
        }
        System.out.println("after hotel rating information:"+hotel);
        //update rating
        hotelMapper.updateById(hotel);
    }
}
