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
@TableName(value = "room")
public class Room {

    @TableId(value = "room_id", type = IdType.AUTO)
    private Integer roomId;

    private Integer hotelId;

    private Integer roomType;

    private Double price;

    private String roomNumber;

    private String description;

    private Integer roomStatus;
}
