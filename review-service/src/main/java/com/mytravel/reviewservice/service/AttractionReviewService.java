package com.mytravel.reviewservice.service;
import com.mytravel.reviewservice.entity.AttractionReview;
import com.mytravel.reviewservice.util.Result;
import org.springframework.stereotype.Service;

/**
 * @author Li Gengrun
 * @date 2024/4/14 10:29
 */
@Service
public interface AttractionReviewService {

    Result createAttractionReview(AttractionReview attractionReview) throws Exception;

    Result getReviewByAttractionId(int attractionId) throws Exception;
}
