package com.mytravel.hotelservice.service.Impl;

import com.mytravel.hotelservice.entity.Room;
import com.mytravel.hotelservice.mapper.RoomMapper;
import com.mytravel.hotelservice.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Li Gengrun
 * @date 2024/4/1 10:59
 */
@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    RoomMapper roomMapper;

    @Override
    public int createOrder(Room room) throws Exception {
        return 0;
    }

    @Override
    public Room getRoomById(int roomId) throws Exception {
        return roomMapper.selectById(roomId);
    }
}
