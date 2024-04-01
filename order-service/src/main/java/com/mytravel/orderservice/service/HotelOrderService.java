package com.mytravel.orderservice.service;

import com.mytravel.orderservice.entity.dto.DetailedHotelOrderDto;
import org.springframework.stereotype.Service;

/**
 * @author Li Gengrun
 * @date 2024/4/1 13:37
 */
@Service
public interface HotelOrderService {

    DetailedHotelOrderDto getDetailedHotelOrder(int orderId) throws Exception;
}
