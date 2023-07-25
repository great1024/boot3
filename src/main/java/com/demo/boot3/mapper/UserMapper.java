package com.demo.boot3.mapper;

import com.demo.boot3.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {
    int insertUser(User user);
    @Select("select * from user")
    List<User> query();
}
