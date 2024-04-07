package com.mytravel.orderservice.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author Li Gengrun
 * @date 2024/4/7 13:44
 */
@Data
@TableName("attraction_order")
public class AttractionOrder implements Serializable {

    @TableId(value = "order_id", type = IdType.AUTO)
    private Integer orderId;

    private Integer userId;

    private Double price; //总价=人数*单价

    private Timestamp createdAt;

    private Timestamp updatedAt;

    private Integer orderStatus;

    private Integer attractionId;

    private String visitDate; //预定日期

    private Integer visitorsNumber; //预定人数

}
