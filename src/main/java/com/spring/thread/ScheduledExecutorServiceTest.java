package com.spring.thread;

import com.spring.thread.model.Apple;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorServiceTest {

    static {
        ScheduledExecutorService timer = Executors.newSingleThreadScheduledExecutor();
        final Apple apple = new Apple();
        apple.setPrice(123.1);
        // 延时 1 秒后，按 3 秒的周期执行任务
        timer.scheduleAtFixedRate(()->{
            Double price = apple.getPrice();
            System.out.println("任务开始当前时间:" + new Date()+" price:" + price);
        }, 1000, 3000, TimeUnit.MILLISECONDS);
    }

    public static void main(String[] args) throws Exception {
        System.out.println("===================");
    }
}
