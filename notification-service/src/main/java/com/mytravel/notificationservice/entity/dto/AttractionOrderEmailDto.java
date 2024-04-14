package com.mytravel.notificationservice.entity.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Li Gengrun
 * @date 2024/4/14 16:24
 */
@Data
public class AttractionOrderEmailDto implements Serializable {

    private String username;

    private String email;

    private Double amount;

    private String attractionName;

    private Integer visitorsNumber;

    public AttractionOrderEmailDto() {
    }

    public AttractionOrderEmailDto(String username, String email, Double amount, String attractionName, Integer visitorsNumber) {
        this.username = username;
        this.email = email;
        this.amount = amount;
        this.attractionName = attractionName;
        this.visitorsNumber = visitorsNumber;
    }
}
