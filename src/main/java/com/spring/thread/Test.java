package com.spring.thread;

import com.spring.thread.model.Apple;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Test {
    public static void main(String[] args) {

        Map<Apple,AtomicInteger> map = new ConcurrentHashMap<>();

        Apple apple = new Apple(1.0,1.0);
        map.put(apple,new AtomicInteger(1));
        Apple apple2 = new Apple(2.0,2.0);
        /**
         * 如果mappingFunction(key)返回的值为null或抛出异常，则不会有记录存入map
         * 此方法首先判断缓存MAP中是否存在指定key的值,
         * 如果不存在，会自动调用mappingFunction(key)计算key的value，然后将key与value放入到缓存Map。
         */
        /*map.computeIfAbsent(apple,a -> {
            System.out.println("apple: " + apple);
            System.out.println("a: " + a);
            return new AtomicInteger(2);
        });*/
        map.compute(apple,(i,j) -> {
            System.out.println("apple: " + apple);
            System.out.println("i: " + i);
            System.out.println("j: " + j);
            if(j == null){
                return new AtomicInteger(1);
            }
            j.incrementAndGet();
            return j;
        });

        System.out.println("map: " + map);
    }
}
