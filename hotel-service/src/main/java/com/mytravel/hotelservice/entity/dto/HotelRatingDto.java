package com.mytravel.hotelservice.entity.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Li Gengrun
 * @date 2024/4/7 0:04
 */
@Data
public class HotelRatingDto implements Serializable {

    private Integer hotelId;

    private Double rating;

    public HotelRatingDto() {
    }

    public HotelRatingDto(Integer hotelId, Double rating) {
        this.hotelId = hotelId;
        this.rating = rating;
    }
}
