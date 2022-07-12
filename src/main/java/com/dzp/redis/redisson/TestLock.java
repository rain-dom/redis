package com.dzp.redis.redisson;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
public class TestLock {

    @Autowired
    RedissonClient redissonClient;

    @GetMapping("/lock")
    public String testLock() {
        RLock rLock = redissonClient.getLock("orderId");
        rLock.lock();
        try {
            System.out.println("lock success,thread: " + Thread.currentThread().getId());
            Thread.sleep(40000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            rLock.unlock();
            System.out.println("finally release the lock,thread: " + Thread.currentThread().getId());
        }
        return "ok";
    }


}
