package com.demo.boot3.netty.demo;

import lombok.Data;


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Data
public class RequestFuture<T> {
    public static Map<Long,RequestFuture> futureMap = new ConcurrentHashMap<>();
    private long id;
    private long timeout=5000;
    private T request;
    private T result;

    public static void addFuture(RequestFuture<?> requestFuture){
        futureMap.put(requestFuture.getId(),requestFuture);
    }

    public T get(){
        synchronized (this){
            while (this.result==null){
                try {
                    this.wait(timeout);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return this.result;
    }

    public static  void received(Response response){
        RequestFuture future = futureMap.remove(response.getId());
        if(future != null){
            future.setResult(response.getResult());
        }
        synchronized (future){
            future.notify();
        }
    }
}
