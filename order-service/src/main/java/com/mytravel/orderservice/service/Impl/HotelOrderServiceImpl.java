package com.mytravel.orderservice.service.Impl;

import com.alibaba.fastjson.JSON;
import com.mytravel.orderservice.client.AuthServiceClient;
import com.mytravel.orderservice.client.HotelServiceClient;
import com.mytravel.orderservice.client.pojo.Hotel;
import com.mytravel.orderservice.client.pojo.Room;
import com.mytravel.orderservice.entity.AttractionOrder;
import com.mytravel.orderservice.entity.HotelOrder;
import com.mytravel.orderservice.entity.dto.DetailedHotelOrderDto;
import com.mytravel.orderservice.entity.dto.HotelOrderEmailDto;
import com.mytravel.orderservice.entity.dto.User;
import com.mytravel.orderservice.mapper.HotelOrderMapper;
import com.mytravel.orderservice.service.HotelOrderService;
import com.mytravel.orderservice.util.Result;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Li Gengrun
 * @date 2024/4/1 13:37
 */
@Service
public class HotelOrderServiceImpl implements HotelOrderService {

    @Autowired
    AuthServiceClient authServiceClient;

    @Autowired
    HotelServiceClient hotelServiceClient;

    @Autowired
    HotelOrderMapper hotelOrderMapper;

    @Autowired
    RabbitTemplate rabbitTemplate;

    /**
     * remote call auth-service to get user info to make a detailed order
     * @param orderId
     * @return
     * @throws Exception
     */
    @Override
    public DetailedHotelOrderDto getDetailedHotelOrder(int orderId) throws Exception {
        HotelOrder hotelOrder = hotelOrderMapper.selectById(orderId);
        if (hotelOrder==null){
            return null;
        }
        int userId = hotelOrder.getUserId();
        //Open Feign remote call
        User user = authServiceClient.getUserById(userId);
        DetailedHotelOrderDto detailedHotelOrderDto=new DetailedHotelOrderDto();
        detailedHotelOrderDto.setOrderId(hotelOrder.getOrderId());
        detailedHotelOrderDto.setEmail(user.getEmail());
        detailedHotelOrderDto.setUsername(user.getUsername());
        detailedHotelOrderDto.setOrderStatus(hotelOrder.getOrderStatus());
        detailedHotelOrderDto.setCheckIn(hotelOrder.getCheckIn());
        detailedHotelOrderDto.setCheckOut(hotelOrder.getCheckOut());
        detailedHotelOrderDto.setPrice(hotelOrder.getPrice());
        detailedHotelOrderDto.setRoomId(hotelOrder.getRoomId());
        detailedHotelOrderDto.setCreatedAt(hotelOrder.getCreatedAt());
        detailedHotelOrderDto.setUpdatedAt(hotelOrder.getUpdatedAt());
        detailedHotelOrderDto.setUserId(userId);
        return detailedHotelOrderDto;
    }

    @Override
    public Result confirmHotelOrder(int orderId) throws Exception {
        HotelOrder hotelOrder=hotelOrderMapper.selectById(orderId);
        if (hotelOrder==null){
            return Result.FAIL("confirm hotel order failed because the order cannot be found.");
        }
        if (hotelOrder.getOrderStatus()==2){
            return Result.FAIL("Payment failed because the hotel order has already been paid.");
        }
        if (hotelOrder.getOrderStatus()==3){
            return Result.FAIL("Payment failed because the hotel order has been canceled.");
        }
        hotelOrder.setOrderStatus(2); // pay
        hotelOrderMapper.updateById(hotelOrder);
        /**
         *  gather email info from auth-service and hotel-service via feign
         */
        HotelOrderEmailDto hotelOrderEmailDto=new HotelOrderEmailDto();
        User user = authServiceClient.getUserById(hotelOrder.getUserId());
        String username=user.getUsername();
        String email=user.getEmail();
        Room room=hotelServiceClient.getRoomById(hotelOrder.getRoomId());
        Hotel hotel=hotelServiceClient.getHotelById(room.getHotelId());
        String hotelName=hotel.getName();
        String roomNumber= room.getRoomNumber();
        hotelOrderEmailDto.setEmail(email);
        hotelOrderEmailDto.setUsername(username);
        hotelOrderEmailDto.setAmount(hotelOrder.getPrice());
        hotelOrderEmailDto.setHotelName(hotelName);
        hotelOrderEmailDto.setRoomNumber(roomNumber);
        /**
         * send hotel order email info to notification-service
         */
        String jsonString= JSON.toJSONString(hotelOrderEmailDto);
        rabbitTemplate.convertAndSend("email.exchange", "hotelOrderEmailRoutingKey", jsonString);
        return Result.SUCCESS(hotelOrder);
    }
}
