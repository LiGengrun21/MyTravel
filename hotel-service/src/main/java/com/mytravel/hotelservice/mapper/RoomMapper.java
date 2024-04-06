package com.mytravel.hotelservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mytravel.hotelservice.entity.Room;
import com.mytravel.hotelservice.entity.dto.HotelSearchDto;
import com.mytravel.hotelservice.entity.dto.HotelSearchResult;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Li Gengrun
 * @date 2024/4/1 10:55
 */
@Mapper
public interface RoomMapper extends BaseMapper<Room> {
    List<HotelSearchResult> search(HotelSearchDto hotelSearchDto) throws Exception;
}
