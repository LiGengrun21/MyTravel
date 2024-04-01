package com.mytravel.orderservice.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @author Li Gengrun
 * @date 2024/4/1 13:12
 */
@Data
@TableName("hotel_order")
public class HotelOrder implements Serializable {

    @TableId(value = "order_id", type = IdType.AUTO)
    private Integer orderId;

    private Integer userId;

    private Double price;

    private Integer orderStatus;

    private Timestamp createdAt;

    private Timestamp updatedAt;

    private Integer roomId;

    private String checkIn;

    private String checkOut;
}
