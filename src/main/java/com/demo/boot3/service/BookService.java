package com.demo.boot3.service;

import com.demo.boot3.pojo.Book;
import com.demo.boot3.repository.BookRepository;
import graphql.com.google.common.collect.Lists;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Resource
    private BookRepository bookRepository;

    public List<Book> get(){
        Iterable<Book> bookIterable = bookRepository.findAll();
        return Lists.newArrayList(bookIterable.iterator());
    }

    public int save(String name) {
        Book book = new Book();
        book.setName(name);
        Book save = bookRepository.save(book);
        return 0;
    }
}
