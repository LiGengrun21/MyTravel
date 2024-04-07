package com.mytravel.attractionservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mytravel.attractionservice.entity.Attraction;
import com.mytravel.attractionservice.entity.dto.AttractionSearchDto;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

/**
 * @author Li Gengrun
 * @date 2024/4/7 13:02
 */
@Mapper
public interface AttractionMapper extends BaseMapper<Attraction> {
    List<Attraction> search(AttractionSearchDto attractionSearchDto) throws Exception;
}
