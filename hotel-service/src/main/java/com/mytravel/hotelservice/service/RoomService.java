package com.mytravel.hotelservice.service;

import com.mytravel.hotelservice.entity.Room;
import org.springframework.stereotype.Service;

/**
 * @author Li Gengrun
 * @date 2024/4/1 10:59
 */
@Service
public interface RoomService {

    int createOrder(Room room) throws Exception;

    Room getRoomById(int roomId) throws Exception;
}
