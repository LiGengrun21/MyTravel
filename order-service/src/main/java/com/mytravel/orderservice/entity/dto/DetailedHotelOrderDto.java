package com.mytravel.orderservice.entity.dto;

import com.mytravel.orderservice.entity.HotelOrder;
import lombok.Data;

import java.io.Serializable;

/**
 * 当前端需要返回详细的订单时，使用这个类
 * @author Li Gengrun
 * @date 2024/4/1 21:01
 */
@Data
public class DetailedHotelOrderDto extends HotelOrder implements Serializable{

    private String username;

    private String email;
}
