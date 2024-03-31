package com.mytravel.authservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mytravel.authservice.entity.dto.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Li Gengrun
 * @date 2024/3/29 17:07
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
