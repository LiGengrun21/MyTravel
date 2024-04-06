package com.mytravel.hotelservice.entity.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 查询条件封装
 * @author Li Gengrun
 * @date 2024/4/6 14:23
 */
@Data
public class HotelSearchDto implements Serializable {

    private String keyword;

    private String roomType;

    private Integer priceMax;

    private Integer priceMin;

    private String checkinDate;

    private String checkoutDate;
}
