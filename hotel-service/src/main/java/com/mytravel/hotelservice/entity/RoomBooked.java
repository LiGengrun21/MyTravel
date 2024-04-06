package com.mytravel.hotelservice.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Li Gengrun
 * @date 2024/4/6 12:14
 */
@Data
@TableName(value = "room_booked")
public class RoomBooked implements Serializable {

    @TableId(value = "room_booked_id", type = IdType.AUTO)
    private Integer roomBookedId;

    private Integer roomId;

    private String startDate;

    private String endDate;
}
