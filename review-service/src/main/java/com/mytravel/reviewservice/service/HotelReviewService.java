package com.mytravel.reviewservice.service;

import com.mytravel.reviewservice.entity.HotelReview;
import com.mytravel.reviewservice.util.Result;
import org.springframework.stereotype.Service;

/**
 * @author Li Gengrun
 * @date 2024/4/6 21:24
 */
@Service
public interface HotelReviewService {

    Result createHotelReview(HotelReview hotelReview) throws Exception;
}
