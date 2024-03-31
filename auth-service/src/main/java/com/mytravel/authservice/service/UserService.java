package com.mytravel.authservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mytravel.authservice.entity.dto.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Li Gengrun
 * @date 2024/3/29 15:00
 */
@Service
public interface UserService extends IService<User> {

    int createUser(User user) throws Exception;

    int deleteUser(int id) throws Exception;

    User getUserById(int id) throws Exception;

    int updateUser(User user) throws Exception;

    User login(String username, String password) throws Exception;

    List<User> getUserList() throws Exception;
}
