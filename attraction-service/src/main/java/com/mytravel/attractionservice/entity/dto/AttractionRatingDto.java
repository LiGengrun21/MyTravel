package com.mytravel.attractionservice.entity.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Li Gengrun
 * @date 2024/4/14 10:47
 */
@Data
public class AttractionRatingDto implements Serializable {

    private Integer attractionId;

    private Double rating;

    public AttractionRatingDto() {
    }

    public AttractionRatingDto(Integer attractionId, Double rating) {
        this.attractionId = attractionId;
        this.rating = rating;
    }
}
