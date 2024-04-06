package com.mytravel.reviewservice.entity.dto;

import lombok.Data;

import java.io.Serializable;

/** 在逻辑删除评论时作为参数传进去
 * @author Li Gengrun
 * @date 2024/4/6 22:23
 */

@Data
public class HotelReviewParam implements Serializable {

    private Integer hotelId;

    private Integer userId;
}
