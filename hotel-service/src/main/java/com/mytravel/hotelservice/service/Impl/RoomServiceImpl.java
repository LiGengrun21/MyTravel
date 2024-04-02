package com.mytravel.hotelservice.service.Impl;

import com.mytravel.hotelservice.entity.Room;
import com.mytravel.hotelservice.entity.dto.HotelOrderDto;
import com.mytravel.hotelservice.mapper.RoomMapper;
import com.mytravel.hotelservice.service.RoomService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSON;



/**
 * @author Li Gengrun
 * @date 2024/4/1 10:59
 */
@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    RoomMapper roomMapper;

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Override
    public int createOrder(HotelOrderDto hotelOrderDto) throws Exception {

        // before creating order, check if the room is available
        Room room=roomMapper.selectById(hotelOrderDto.getRoomId());
        if (room.getRoomStatus()!=1){
            return 0; //order creation fails
        }
        // if available, create and send the order info order-service
        //System.out.println(hotelOrderDto.toString()+"XXXXXXXXXXXXXXXXXXXXXXXXXXX");
        String jsonString=JSON.toJSONString(hotelOrderDto);
        // 发送JSON String
        rabbitTemplate.convertAndSend("order.exchange", "hotelOrderRoutingKey", jsonString);
        // update room information (change room_status)
        room.setRoomStatus(2); //room booked
        int result=roomMapper.updateById(room);
        return result;
    }

    @Override
    public Room getRoomById(int roomId) throws Exception {
        return roomMapper.selectById(roomId);
    }

    /**
     * 只是一个简单的例子，没有考虑外键以及重复等问题，编写测试用例时要小心
     *
     * @param room
     * @return
     * @throws Exception
     */
    @Override
    public int createRoom(Room room) throws Exception {
        return roomMapper.insert(room);
    }
}
