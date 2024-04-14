package com.mytravel.orderservice.client.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author Li Gengrun
 * @date 2024/4/1 10:43
 */
@Data
@TableName(value = "hotel")
public class Hotel {

    @TableId(value = "hotel_id", type = IdType.AUTO)
    private Integer hotelId;

    private String name;

    private String address;

    private String description;

    private String phoneNumber;

    private String email;

    private Double rating;
}
