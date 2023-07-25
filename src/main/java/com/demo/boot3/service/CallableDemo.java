package com.demo.boot3.service;

import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.*;

public class CallableDemo implements Callable<Integer>{
    private static final ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        CallableDemo callableDemo = new CallableDemo();
        Future<Integer> submit = scheduledExecutorService.submit(callableDemo);
//        submit.cancel(true);
        while (true){
            if(submit.isCancelled()){
                System.out.println("线程取消执行");
            }
            if(submit.isDone()){
                System.out.println("线程执行结束,结果："+submit.get());
                break;
            }
        }
        scheduledExecutorService.shutdownNow();
    }

    @Override
    public Integer call() throws Exception {
        System.out.println("线程开始执行");
        Thread.sleep(3000);
        return 12;
    }
    public class Man<V> implements Callable<V> {
//        private V data;
        private V value;
        @Override
        public V call() throws Exception {
            System.out.println("洗茶壶");
            Thread.sleep(1000);
            System.out.println("洗茶杯");
            Thread.sleep(1000);
            System.out.println("拿茶叶");
            Thread.sleep(1000);
            this.value = (V) "万事俱备";
            return this.value;
        }
    }

    public class Tool<V> implements Callable<V> {
//        private V data;
        private V value;
        @Override
        public V call() throws Exception {
            System.out.println("洗水壶");
            Thread.sleep(1000);
            System.out.println("烧水");
            Thread.sleep(10000);
            this.value = (V) "热水好了";
            return this.value;
        }
    }
}
