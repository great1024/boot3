package com.demo.boot3.repository;

import com.demo.boot3.pojo.Book;
//import com.demo.boot3.pojo.QBook;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book,Integer> {
}
