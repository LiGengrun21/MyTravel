package com.mytravel.orderservice.service.Impl;

import com.mytravel.orderservice.client.AuthServiceClient;
import com.mytravel.orderservice.entity.HotelOrder;
import com.mytravel.orderservice.entity.dto.DetailedHotelOrderDto;
import com.mytravel.orderservice.entity.dto.User;
import com.mytravel.orderservice.mapper.HotelOrderMapper;
import com.mytravel.orderservice.service.HotelOrderService;
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
    HotelOrderMapper hotelOrderMapper;

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
}
