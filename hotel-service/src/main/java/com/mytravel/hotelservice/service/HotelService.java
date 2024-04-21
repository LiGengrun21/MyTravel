package com.mytravel.hotelservice.service;

import com.mytravel.hotelservice.entity.Hotel;
import com.mytravel.hotelservice.util.Result;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;

/**
 * @author Li Gengrun
 * @date 2024/4/14 13:00
 */
@Service
public interface HotelService {

    Hotel getHotelById(int hotelId) throws Exception;

    Result updateHotel(Hotel hotel) throws Exception;

    Result deleteHotel(int hotelId) throws Exception;
}
