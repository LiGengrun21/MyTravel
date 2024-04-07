package com.mytravel.orderservice.listener;

import com.alibaba.fastjson.JSONObject;
import com.mytravel.orderservice.entity.AttractionOrder;
import com.mytravel.orderservice.entity.dto.AttractionOrderDto;
import com.mytravel.orderservice.entity.dto.HotelOrderDto;
import com.mytravel.orderservice.mapper.AttractionOrderMapper;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

/**
 * @author Li Gengrun
 * @date 2024/4/7 14:45
 */
@Component
@RabbitListener(queues = "attractionOrder.queue")
public class AttractionOrderMQListener {

    @Autowired
    private AttractionOrderMapper attractionOrderMapper;

    @RabbitHandler
    public void createAttractionOrder(String jsonString){
        /**
         * receive the json message
         */
        AttractionOrderDto attractionOrderDto = JSONObject.parseObject(jsonString, AttractionOrderDto.class);
        System.out.println("attraction order information:"+attractionOrderDto);
        /**
         * create new attraction order
         */
        AttractionOrder attractionOrder=new AttractionOrder();
        attractionOrder.setUserId(attractionOrderDto.getUserId());
        attractionOrder.setAttractionId(attractionOrderDto.getAttractionId());
        attractionOrder.setPrice(attractionOrderDto.getPrice()*attractionOrderDto.getVisitorsNumber());// total amount
        attractionOrder.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        attractionOrder.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        attractionOrder.setOrderStatus(1); //order created
        attractionOrder.setVisitDate(attractionOrderDto.getVisitDate());
        attractionOrder.setVisitorsNumber(attractionOrderDto.getVisitorsNumber());
        attractionOrderMapper.insert(attractionOrder);
    }
}
