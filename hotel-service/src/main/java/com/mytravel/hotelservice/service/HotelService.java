package com.mytravel.hotelservice.service;

import com.mytravel.hotelservice.entity.Hotel;
import org.springframework.stereotype.Service;

/**
 * @author Li Gengrun
 * @date 2024/4/14 13:00
 */
@Service
public interface HotelService {

    Hotel getHotelById(int hotelId) throws Exception;
}
