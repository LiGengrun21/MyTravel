package com.mytravel.hotelservice.service;

import com.mytravel.hotelservice.entity.Room;
import com.mytravel.hotelservice.entity.dto.HotelOrderDto;
import com.mytravel.hotelservice.util.Result;
import org.springframework.stereotype.Service;

/**
 * @author Li Gengrun
 * @date 2024/4/1 10:59
 */
@Service
public interface RoomService {

    Result createOrder(HotelOrderDto hotelOrderDto) throws Exception;

    Room getRoomById(int roomId) throws Exception;

    int createRoom(Room room) throws Exception;
}
