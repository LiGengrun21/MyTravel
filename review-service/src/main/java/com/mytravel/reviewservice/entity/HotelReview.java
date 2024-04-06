package com.mytravel.reviewservice.entity;

import lombok.Data;
import java.sql.Timestamp;

import java.io.Serializable;

/**
 * @author Li Gengrun
 * @date 2024/4/6 20:38
 */
@Data
public class HotelReview implements Serializable {

    private Integer reviewId;

    private String content;

    private Integer userId;

    private Integer status;

    private Timestamp createdAt;

    private Timestamp updatedAt;

    private Integer hotelId;

    private Integer rating; //酒店评分
}
