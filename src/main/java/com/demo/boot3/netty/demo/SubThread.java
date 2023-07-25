package com.demo.boot3.netty.demo;

import lombok.Data;

@Data
public class SubThread extends Thread{
    private RequestFuture requestFuture;
    public SubThread(RequestFuture requestFuture){
        this.requestFuture = requestFuture;
    }

    @Override
    public void run() {
        Response<Object> objectResponse = new Response<>();
        objectResponse.setId(requestFuture.getId());
        objectResponse.setResult("server res:"+Thread.currentThread().getId());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        RequestFuture.received(objectResponse);
    }
}
