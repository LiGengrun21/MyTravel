package com.mytravel.attractionservice.entity.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Li Gengrun
 * @date 2024/4/7 16:13
 */
@Data
public class AttractionSearchDto implements Serializable {

    private String keyword;   //景点名称关键词

    private String city;      //城市

    private Integer priceMax; //门票上界

    private Integer priceMin; //门票下界

}
