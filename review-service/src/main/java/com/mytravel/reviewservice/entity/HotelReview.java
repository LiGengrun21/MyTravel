package com.mytravel.reviewservice.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.sql.Timestamp;

import java.io.Serializable;

/**
 * @author Li Gengrun
 * @date 2024/4/6 20:38
 */
@Data
@TableName(value = "hotel_review")
public class HotelReview implements Serializable {

    @TableId(value = "review_id", type = IdType.AUTO)
    private Integer reviewId;

    private String content;

    private Integer userId;

    private Integer status;

    private Timestamp createdAt;

    private Timestamp updatedAt;

    private Integer hotelId;

    private Integer rating; //酒店评分
}
