package com.demo.boot3.netty.demo;

import lombok.Data;

@Data
public class Response<T> {
    private long id;
    private T result;
}
