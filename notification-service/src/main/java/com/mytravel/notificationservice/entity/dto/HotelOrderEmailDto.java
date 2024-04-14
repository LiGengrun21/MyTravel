package com.mytravel.notificationservice.entity.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Li Gengrun
 * @date 2024/4/14 12:29
 */
@Data
public class HotelOrderEmailDto implements Serializable {

    private String username;

    private String email;

    private Double amount;

    private String hotelName;

    private String roomNumber;

    public HotelOrderEmailDto() {
    }

    public HotelOrderEmailDto(String username, String email, Double amount, String hotelName, String roomNumber) {
        this.username = username;
        this.email = email;
        this.amount = amount;
        this.hotelName = hotelName;
        this.roomNumber = roomNumber;
    }
}
