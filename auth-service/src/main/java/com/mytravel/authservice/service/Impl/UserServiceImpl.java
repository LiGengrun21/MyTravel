package com.mytravel.authservice.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mytravel.authservice.entity.dto.LoginParam;
import com.mytravel.authservice.entity.dto.User;
import com.mytravel.authservice.mapper.UserMapper;
import com.mytravel.authservice.service.UserService;
import com.mytravel.authservice.util.Result;
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
    public Result updateUser(User user) throws Exception {
        if (user==null){
            return Result.FAIL("Update user profile failed since the user cannot be found.");
        }
        int result=userMapper.updateById(user);
        if (result==0){
            return Result.FAIL("Update user failed!");
        }
        return Result.SUCCESS(user);
    }

    @Override
    public Result login(LoginParam loginParam) throws Exception {
        if (loginParam==null){
            return Result.FAIL("Login failed due to lack of login param!");
        }
        String username=loginParam.getUsername();
        String password= loginParam.getPassword();
        String role= loginParam.getRole();
        if (username==null||username.equals("")||password==null||password.equals("")||role==null||role.equals("")){
            return Result.FAIL("Login failed due to no username or password!");
        }
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("username", username);
        queryWrapper.eq("password",password);
        queryWrapper.eq("role",role);
        User loginUser=userMapper.selectOne(queryWrapper);
        if (loginUser==null){
            return Result.FAIL("Login failed due to wrong password or username!");
        }
        return Result.SUCCESS(loginUser);
    }

    @Override
    public List<User> getUserList() {
        return userMapper.selectList(null);
    }
}
