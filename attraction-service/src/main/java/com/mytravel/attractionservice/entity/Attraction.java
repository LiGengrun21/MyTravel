package com.mytravel.attractionservice.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Li Gengrun
 * @date 2024/4/7 12:35
 */
@Data
@TableName(value = "attraction")
public class Attraction implements Serializable {

    @TableId(value = "attraction_id", type = IdType.AUTO)
    private Integer attractionId;

    private String name;

    private String description;

    private String city;

    private String phoneNumber;

    private String email;

    private String address;

    private Double rating;

    private String openTime; //景点开发时间段

    private Integer ticketsAvailable;

    private Double price;
}
