package com.mytravel.attractionservice.service.Impl;

import com.mytravel.attractionservice.mapper.AttractionMapper;
import com.mytravel.attractionservice.service.AttractionService;
import com.mytravel.attractionservice.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Li Gengrun
 * @date 2024/4/7 13:01
 */
@Service
public class AttractionServiceImpl implements AttractionService {

    @Autowired
    private AttractionMapper attractionMapper;

    @Override
    public Result getAttractionById(int attractionId) throws Exception {
        return Result.SUCCESS(attractionMapper.selectById(attractionId));
    }
}
