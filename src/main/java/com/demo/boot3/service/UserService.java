package com.demo.boot3.service;

import com.demo.boot3.mapper.UserMapper;
import com.demo.boot3.pojo.User;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Resource
    private UserMapper userMapper;

    public int insert(User user){
      return userMapper.insertUser(user);
    }

    public List<User> query() {
        return userMapper.query();
    }
}
