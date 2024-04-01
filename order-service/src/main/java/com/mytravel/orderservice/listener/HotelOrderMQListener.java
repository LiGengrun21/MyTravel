package com.mytravel.orderservice.listener;

import com.mytravel.orderservice.entity.HotelOrder;
import com.mytravel.orderservice.entity.dto.HotelOrderDto;
import com.mytravel.orderservice.mapper.HotelOrderMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

/**
 * @author Li Gengrun
 * @date 2024/4/1 15:36
 */
@Component
public class HotelOrderMQListener {

    @Autowired
    HotelOrderMapper hotelOrderMapper;

    /**
     * listen to hotel-service and add the order to DB
     * @param hotelOrderDto
     * @return
     */
    @RabbitListener(queues = "hotelOrder.queue")
    public int createHotelOrder(HotelOrderDto hotelOrderDto){
        HotelOrder hotelOrder=new HotelOrder();
        hotelOrder.setUserId(hotelOrder.getUserId());
        hotelOrder.setRoomId(hotelOrderDto.getRoomId());
        hotelOrder.setPrice(hotelOrder.getPrice());
        hotelOrder.setCheckIn(hotelOrderDto.getCheckIn());
        hotelOrder.setCheckOut(hotelOrderDto.getCheckOut());
        hotelOrder.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        hotelOrder.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        hotelOrder.setOrderStatus(1); //order created
        hotelOrderMapper.insert(hotelOrder);
        return 0;
    }
}
