package com.mytravel.hotelservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mytravel.hotelservice.entity.Hotel;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Li Gengrun
 * @date 2024/4/7 0:25
 */
@Mapper
public interface HotelMapper extends BaseMapper<Hotel> {
}
