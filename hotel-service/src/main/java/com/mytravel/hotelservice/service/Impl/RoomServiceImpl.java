package com.mytravel.hotelservice.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mytravel.hotelservice.entity.Room;
import com.mytravel.hotelservice.entity.RoomBooked;
import com.mytravel.hotelservice.entity.dto.HotelOrderDto;
import com.mytravel.hotelservice.entity.dto.HotelSearchDto;
import com.mytravel.hotelservice.entity.dto.HotelSearchResult;
import com.mytravel.hotelservice.mapper.RoomBookedMapper;
import com.mytravel.hotelservice.mapper.RoomMapper;
import com.mytravel.hotelservice.service.RoomService;
import com.mytravel.hotelservice.util.Result;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Li Gengrun
 * @date 2024/4/1 10:59
 */
@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    RoomMapper roomMapper;

    @Autowired
    RoomBookedMapper roomBookedMapper;

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Override
    public Result createOrder(HotelOrderDto hotelOrderDto) throws Exception {

        /**
         * before creating order, check if the room at that time is available
         */
        int roomId=hotelOrderDto.getRoomId();
        Room room=roomMapper.selectById(roomId);
        // check if room exists
        if (room==null){
            return Result.FAIL("room not exists.");
        }
        // check room status, if not 1, then fails
        if (room.getRoomStatus()!=1){
            return Result.FAIL("room not available.");
        }
        // check if booked during the time
        QueryWrapper<RoomBooked> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("room_id", roomId);
        String checkin=hotelOrderDto.getCheckIn();
        String checkout=hotelOrderDto.getCheckOut();
        if (checkin==null || checkin.equals("")||checkout==null||checkout.equals("")){
            return Result.FAIL("checkin and checkout time cannot be empty."); //these fields cannot be empty
        }
        // logic to decide if the booking is possible
        queryWrapper.le("start_date",checkout);
        queryWrapper.ge("end_date", checkin);
        Long count=roomBookedMapper.selectCount(queryWrapper);
        if (count!=0){
            return Result.FAIL("room booked, please change your time slot.");
        }
        /**
         * if available (count==0), create and send the order info order-service
         */
        String jsonString=JSON.toJSONString(hotelOrderDto);
        // 发送JSON String
        rabbitTemplate.convertAndSend("order.exchange", "hotelOrderRoutingKey", jsonString);
        /**
         * update room information (add a new item to room_booked)
         */
        RoomBooked roomBooked=new RoomBooked();
        roomBooked.setRoomId(roomId);
        roomBooked.setStartDate(checkin);
        roomBooked.setEndDate(checkout);
        int result=roomBookedMapper.insert(roomBooked);
        return Result.SUCCESS(result);
    }

    @Override
    public Room getRoomById(int roomId) throws Exception {
        return roomMapper.selectById(roomId);
    }

    /**
     * 只是一个简单的例子，没有考虑外键以及重复等问题，编写测试用例时要小心
     * @param room
     * @return
     * @throws Exception
     */
    @Override
    public int createRoom(Room room) throws Exception {
        return roomMapper.insert(room);
    }

    @Override
    public Result search(HotelSearchDto hotelSearchDto) throws Exception {
        List<HotelSearchResult> searchResultList=roomMapper.search(hotelSearchDto);
        /**
         * no checkin and checkout conditions
         */
        String checkin=hotelSearchDto.getCheckinDate();
        String checkout=hotelSearchDto.getCheckoutDate();
        if (checkin==null||checkin.equals("")||checkout==null||checkout.equals("")){
            return Result.SUCCESS(searchResultList);
        }
        /**
         * filter by checkin and checkout time
         */
        List<HotelSearchResult> finalList=new ArrayList<>();
        for (HotelSearchResult hotelSearchResult : searchResultList) {
            int roomId=hotelSearchResult.getRoomId();
            //在room_booked中查询是否冲突，若冲突，则删除这一条
            QueryWrapper<RoomBooked> queryWrapper=new QueryWrapper<>();
            queryWrapper.eq("room_id", roomId);
            queryWrapper.le("start_date",hotelSearchDto.getCheckoutDate());
            queryWrapper.ge("end_date", hotelSearchDto.getCheckinDate());
            Long count=roomBookedMapper.selectCount(queryWrapper);
            if (count!=0){
                continue;
            }
            else{
                finalList.add(hotelSearchResult);
            }
        }
        return Result.SUCCESS(finalList);
    }
}
