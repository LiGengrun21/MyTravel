package com.mytravel.attractionservice.service;

import com.mytravel.attractionservice.util.Result;
import org.springframework.stereotype.Service;

/**
 * @author Li Gengrun
 * @date 2024/4/7 13:01
 */
@Service
public interface AttractionService {

    Result getAttractionById(int attractionId) throws Exception;
}
