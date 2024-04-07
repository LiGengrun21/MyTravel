package com.mytravel.orderservice.listener;

import com.alibaba.fastjson.JSONObject;
import com.mytravel.orderservice.entity.HotelOrder;
import com.mytravel.orderservice.entity.dto.HotelOrderDto;
import com.mytravel.orderservice.mapper.HotelOrderMapper;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

/**
 * @author Li Gengrun
 * @date 2024/4/1 15:36
 */
@Component
@RabbitListener(queues = "hotelOrder.queue")
public class HotelOrderMQListener {
    @Autowired
    HotelOrderMapper hotelOrderMapper;

    /**
     * listen to hotel-service and add the order to DB
     * @param jsonString
     */
    @RabbitHandler
    public void createHotelOrder(String jsonString){
        HotelOrderDto hotelOrderDto = JSONObject.parseObject(jsonString, HotelOrderDto.class);
        System.out.println("hotel order information:"+hotelOrderDto);
        HotelOrder hotelOrder=new HotelOrder();
        hotelOrder.setUserId(hotelOrderDto.getUserId());
        hotelOrder.setRoomId(hotelOrderDto.getRoomId());
        hotelOrder.setPrice(hotelOrderDto.getPrice());
        hotelOrder.setCheckIn(hotelOrderDto.getCheckIn());
        hotelOrder.setCheckOut(hotelOrderDto.getCheckOut());
        hotelOrder.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        hotelOrder.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        hotelOrder.setOrderStatus(1); //order created
        hotelOrderMapper.insert(hotelOrder);
    }
}
