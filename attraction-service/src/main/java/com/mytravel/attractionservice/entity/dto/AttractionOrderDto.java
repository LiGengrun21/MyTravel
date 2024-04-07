package com.mytravel.attractionservice.entity.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Li Gengrun
 * @date 2024/4/7 13:35
 */
@Data
public class AttractionOrderDto implements Serializable {

    private Integer userId;

    private Integer attractionId;

    private Integer visitorsNumber;

    private String visitDate;

    private Double price;

    public AttractionOrderDto() {
    }

    public AttractionOrderDto(Integer userId, Integer attractionId, Integer visitorsNumber, String visitDate, Double price) {
        this.userId = userId;
        this.attractionId = attractionId;
        this.visitorsNumber = visitorsNumber;
        this.visitDate = visitDate;
        this.price = price;
    }
}
