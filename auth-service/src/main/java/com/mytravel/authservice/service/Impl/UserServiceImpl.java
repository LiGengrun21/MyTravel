package com.mytravel.authservice.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mytravel.authservice.entity.dto.LoginParam;
import com.mytravel.authservice.entity.dto.User;
import com.mytravel.authservice.mapper.UserMapper;
import com.mytravel.authservice.service.UserService;
import com.mytravel.authservice.util.Result;
//import com.mytravel.authservice.util.jwt.JwtTokenUtil;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author Li Gengrun
 * @date 2024/3/29 15:00
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    UserMapper userMapper;

//    @Resource
//    JwtTokenUtil jwtTokenUtil;

//    @Autowired
//    private PasswordEncoder passwordEncoder;

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
            return Result.FAIL("Login failed due to wrong username or password!");
        }
//        PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
//        boolean isAuthenticated=passwordEncoder.matches(password, loginUser.getPassword());
//        if (!isAuthenticated){
//            return Result.FAIL("Password is not correct!");
//        }
//        Map<String, Object> tokenMap = jwtTokenUtil.generateTokenAndRefreshToken(Integer.toString(loginUser.getId()), loginUser.getUsername());
//        System.out.println(tokenMap);
        return Result.SUCCESS(loginUser);
    }

    @Override
    public List<User> getUserList() {
        return userMapper.selectList(null);
    }

    @Override
    public Result register(User user) throws Exception {
        /**
         * check if email registered before
         */
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("email",user.getEmail());
        List<User> userList=userMapper.selectList(queryWrapper);
        if (userList==null){
            /**
             *  create a user
             */
            User user1=new User();
            user1.setEmail(user.getEmail());
            user1.setPassword(user.getPassword());
            user1.setUsername(user.getUsername());
            user1.setRole("user");
            user1.setStatus(1);
            int result=userMapper.insert(user1);
            if (result==0){
                return Result.FAIL("Sign-up failed!");
            }
            else {
                return Result.SUCCESS(userMapper.selectOne(queryWrapper));
            }
        }
        else{
            return Result.FAIL("The email has been registered already");
        }
    }
}
