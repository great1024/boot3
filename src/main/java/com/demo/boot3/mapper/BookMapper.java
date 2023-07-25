package com.demo.boot3.mapper;

import com.demo.boot3.pojo.Book;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookMapper {
    @Insert("insert into book (name) values (${name})")
    public int insert(Book book);
    @Select("select * from book where name = #{name}")
    List<Book> findByName(String name);
}
