package com.demo.boot3.netty.config;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Controller;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * 当Spring容器启动并完成Bean的初始化后，可以运用上下文刷新事件ContextRefreshedEvent，
 * 在事件中循环遍历容器中的Bean，获取带有Controller的注解对象及其@Remote注解方法。并把它们放入缓存容器Mediator.methodBeans中。
 * 由于Netty服务的启动也是在ContextRefreshedEvent事件中完成的，所以两个动作的执行有先后顺序，
 * 为了保证在Netty服务启动前所有接口方法都已放入缓存容器中，Spring容器提供了Ordered接口，
 * 用来处理相同接口实现类的优先级问题。
 */
public class InitLoadRemoteMethod implements ApplicationListener<ContextRefreshedEvent>, Ordered {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        Map<String, Object> beansWithAnnotation = event.getApplicationContext().getBeansWithAnnotation(Controller.class);
        for (String key : beansWithAnnotation.keySet()) {
            Object o = beansWithAnnotation.get(key);
            Method[] declaredMethods = o.getClass().getDeclaredMethods();
            for (Method declaredMethod : declaredMethods) {
                if (declaredMethod.isAnnotationPresent(Remote.class)) {
                    Remote annotation = declaredMethod.getAnnotation(Remote.class);
                    String value = annotation.value();
                    Mediator.MethodBean methodBean = new Mediator.MethodBean();
                    methodBean.setBean(o);
                    methodBean.setMethod(declaredMethod);
                    Mediator.methodBean.put(value,methodBean);
                }
            }
        }
    }

    @Override
    public int getOrder() {
        return -1;
    }
}
