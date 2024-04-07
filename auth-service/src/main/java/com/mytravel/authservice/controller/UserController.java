package com.mytravel.authservice.controller;

import com.mytravel.authservice.entity.dto.User;
import com.mytravel.authservice.service.UserService;
import com.mytravel.authservice.util.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Li Gengrun
 * @date 2024/3/29 18:25
 */

@Tag(name="User",description = "auth-service, User")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Operation(summary = "get all users")
    @GetMapping("/list")
    public List<User> getUserList()throws Exception{
        return userService.getUserList();
    }

    @Operation(summary = "demo")
    @GetMapping("/demo")
    public String getDemo(){
        return "XXXX Hello!";
    }

    @Operation(summary = "get the user by id")
    @GetMapping("/id")
    public User getUserById(@RequestParam int id) throws Exception{
        return userService.getUserById(id);
    }

    public Result updateUserProfile(User user) throws Exception{
        return userService.updateUser(user);
    }
}
