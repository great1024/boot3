package com.demo.boot3.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;


@Service
public class TaskService {
    public void task(){
        Executors.newCachedThreadPool();

    }
}
