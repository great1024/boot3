package com.demo.boot3.netty.config;

import com.demo.boot3.netty.demo.RequestFuture;
import com.demo.boot3.netty.demo.Response;
import lombok.Data;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class Mediator {
    public static Map<String ,MethodBean> methodBean;
    static {
        methodBean = new HashMap<>();
    }

    public static Response process(RequestFuture requestFuture){
        Response<Object> objectResponse = new Response<>();
//        todo
        return null;
    }
    @Data
    public static class MethodBean{
        private Object bean;
        private Method method;

    }
}
