package com.mytravel.hotelservice.entity;

import lombok.Data;

/**
 * @author Li Gengrun
 * @date 2024/4/1 10:43
 */
@Data
public class Hotel {

    private Integer hotelId;

    private String name;

    private String address;

    private String description;

    private String phoneNumber;

    private String email;

    private Double rating;
}
