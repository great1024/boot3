package com.demo.boot3.netty.demo;



import java.util.ArrayList;
import java.util.List;

public class FutureMain {
    public static void main(String[] args) {
        List<RequestFuture<String>> requestFutureList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            long id =i;
            RequestFuture<String> requestFuture = new RequestFuture<>();
            requestFuture.setId(id);
            requestFuture.setRequest("hello world");
            RequestFuture.addFuture(requestFuture);
            requestFutureList.add(requestFuture);
            sendMsg(requestFuture);
            SubThread subThread = new SubThread(requestFuture);
            subThread.start();
        }
    }

    private static void sendMsg(RequestFuture<String> requestFuture) {
        System.out.println("向客户端发送iqngqiu，请求ID = " + requestFuture.getId());
    }
}
