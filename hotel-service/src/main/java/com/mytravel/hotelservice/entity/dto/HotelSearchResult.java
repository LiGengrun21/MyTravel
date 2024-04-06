package com.mytravel.hotelservice.entity.dto;

import lombok.Data;

import java.io.Serializable;

/**搜索酒店返回的结果，包含酒店和房间信息
 *
 * 返回12个数据
 * @author Li Gengrun
 * @date 2024/4/6 15:10
 */
@Data
public class HotelSearchResult implements Serializable {

    private Integer hotelId;

    private String name; //hotel name

    private String address;

    private String description; //hotel description

    private String phoneNumber;

    private String email;

    private Double rating; //hotel rating

    private Integer roomId;

    private Integer roomTypeId;

    private String roomTypeName;

    private Double price;

    private String roomNumber;
}
