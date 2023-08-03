package com.demo.boot3;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication
@EnableAsync
//@EnableWebFlux
@MapperScan("com.demo.boot3.mapper")
public class Boot3Application {
    private static volatile boolean running = true;

    public static void main(String[] args) {
        SpringApplication.run(Boot3Application.class, args);
        try {
            AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext("com.demo.boot3.netty");
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                annotationConfigApplicationContext.stop();
                synchronized (Boot3Application.class) {
                    running = false;
                    Boot3Application.class.notify();
                }
            }));
            annotationConfigApplicationContext.start();
        }catch (Exception e){
            e.printStackTrace();
            System.exit(1);
        }

        synchronized (Boot3Application.class){
            while (running){
                try {
                    Boot3Application.class.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }

}
