package com.demo.boot3.controller;

import com.demo.boot3.pojo.Book;
import com.demo.boot3.service.BookService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("book")
public class BookController {
    @Resource
    private BookService bookService;
    @GetMapping
    public List<Book> get(){
       return bookService.get();
    }

    @PostMapping
    public int post(String name){
        if(name == null || "".equals(name)){
            return 0;
        }
        return bookService.save(name);
    }
}
