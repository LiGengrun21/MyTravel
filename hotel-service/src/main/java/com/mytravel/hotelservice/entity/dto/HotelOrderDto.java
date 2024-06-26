package com.mytravel.hotelservice.entity.dto;

import lombok.Data;
import java.io.Serializable;

/**
 * @author Li Gengrun
 * @date 2024/4/1 15:47
 */
@Data
public class HotelOrderDto implements Serializable {

    private Integer userId;

    private Double price;

    private Integer roomId;

    private String checkIn;

    private String checkOut;

    public HotelOrderDto(Integer userId, Double price, Integer roomId, String checkIn, String checkOut) {
        this.userId = userId;
        this.price = price;
        this.roomId = roomId;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public HotelOrderDto() {
    }
}
