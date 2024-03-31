package com.mytravel.authservice.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mytravel.authservice.entity.dto.User;
import com.mytravel.authservice.mapper.UserMapper;
import com.mytravel.authservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Li Gengrun
 * @date 2024/3/29 15:00
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public int createUser(User user) throws Exception {
        return userMapper.insert(user);
    }

    @Override
    public int deleteUser(int id) throws Exception {
        return userMapper.deleteById(id);
    }

    @Override
    public User getUserById(int id) throws Exception {
        return userMapper.selectById(id);
    }

    @Override
    public int updateUser(User user) throws Exception {
        return userMapper.updateById(user);
    }

    @Override
    public User login(String username, String password) throws Exception {
        return null;
    }

    @Override
    public List<User> getUserList() {
        return userMapper.selectList(null);
    }
}
