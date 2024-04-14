package com.mytravel.reviewservice.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @author Li Gengrun
 * @date 2024/4/14 10:23
 */
@Data
@TableName(value = "attraction_review")
public class AttractionReview {

    @TableId(value = "review_id", type = IdType.AUTO)
    private Integer reviewId;

    private String content;

    private Integer userId;

    private Integer status;

    private Timestamp createdAt;

    private Timestamp updatedAt;

    private Integer attractionId;

    private Integer rating; //景点评分
}
