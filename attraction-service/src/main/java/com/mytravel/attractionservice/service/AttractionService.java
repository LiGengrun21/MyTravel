package com.mytravel.attractionservice.service;

import com.mytravel.attractionservice.entity.Attraction;
import com.mytravel.attractionservice.entity.dto.AttractionOrderDto;
import com.mytravel.attractionservice.entity.dto.AttractionSearchDto;
import com.mytravel.attractionservice.util.Result;
import org.springframework.stereotype.Service;

/**
 * @author Li Gengrun
 * @date 2024/4/7 13:01
 */
@Service
public interface AttractionService {

    Attraction getAttractionById(int attractionId) throws Exception;

    Result createAttraction(Attraction attraction) throws Exception;

    Result createOrder(AttractionOrderDto attractionOrderDto) throws Exception;

    Result search(AttractionSearchDto attractionSearchDto) throws Exception;
}


