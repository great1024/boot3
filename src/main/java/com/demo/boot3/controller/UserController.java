package com.demo.boot3.controller;

import com.demo.boot3.pojo.User;
import com.demo.boot3.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {
    @Resource
    private UserService userService;
    @PostMapping
    public int user(){
        String username = "a";
        String password = "b";
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        return userService.insert(user);
    }
    @GetMapping
    public List<User> get(){
        return userService.query();
    }
}
