package com.mytravel.orderservice.entity.dto;

import com.mytravel.orderservice.entity.AttractionOrder;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Li Gengrun
 * @date 2024/4/7 15:12
 */
@Data
public class DetailedAttractionOrderDto extends AttractionOrder implements Serializable {
    private String username;

    private String email;
}
