package com.mytravel.hotelservice.service.Impl;

import com.mytravel.hotelservice.entity.Hotel;
import com.mytravel.hotelservice.mapper.HotelMapper;
import com.mytravel.hotelservice.service.HotelService;
import com.mytravel.hotelservice.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Li Gengrun
 * @date 2024/4/14 13:00
 */
@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelMapper hotelMapper;

    @Override
    public Hotel getHotelById(int hotelId) throws Exception {
        return hotelMapper.selectById(hotelId);
    }

    @Override
    public Result updateHotel(Hotel hotel) throws Exception {
        return Result.SUCCESS(hotelMapper.updateById(hotel));
    }

    @Override
    public Result deleteHotel(int hotelId) throws Exception {
        return Result.SUCCESS(hotelMapper.deleteById(hotelId));
    }
}
