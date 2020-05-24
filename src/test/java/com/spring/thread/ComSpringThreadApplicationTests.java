package com.spring.thread;

import com.spring.thread.model.Apple;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@SpringBootTest
class ComSpringThreadApplicationTests {

    private static final Logger logger = LoggerFactory.getLogger(ComSpringThreadApplication.class);

    @Autowired
    private ThreadPoolTaskExecutor executor;

    @Test
    void contextLoads(){

        List<Apple> apples = getApples();
        List<Future<Apple>> appleFutureList = new ArrayList<>();
        CountDownLatch countDownLatch = new CountDownLatch(apples.size());

        for(int i = 0; i < apples.size(); i++){
            Apple apple = apples.get(i);
            Future<Apple> appleFuture = executor.submit(() -> {
                Double amount = apple.getWeight() * apple.getPrice();
                apple.setAmount(amount);
                return apple;
            });
            appleFutureList.add(appleFuture);
            countDownLatch.countDown();
        }
        try {
            countDownLatch.await();
            for(int i = 0; i < appleFutureList.size(); i++){
                Future<Apple> appleFuture = appleFutureList.get(i);
                Apple apple = appleFuture.get();
                logger.info("apple:{}",apple);
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    private List<Apple> getApples(){
        List<Apple> list = new ArrayList<>();
        list.add(new Apple(1.2,2.5));
        list.add(new Apple(1.0,2.25));
        list.add(new Apple(1.3,2.0));
        list.add(new Apple(1.5,3.0));
        list.add(new Apple(1.1,1.1));
        list.add(new Apple(1.2,1.2));
        list.add(new Apple(1.5,4.0));
        return list;
    }
}
