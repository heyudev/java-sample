package com.heyudev.lock;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

import java.util.concurrent.TimeUnit;

/**
 * redisson 实现分布式锁
 *
 * @author heyudev
 * @date 2021/03/05
 */
public class RedissonLock {

    public RLock getLock(RedissonClient redissonClient, String key) {
        return redissonClient.getLock(key);
    }

    public Boolean lock(RLock lock, long timeout) {
        lock.lock(timeout, TimeUnit.MILLISECONDS);
        return true;
    }

    public Boolean unlock(RLock lock) {
        lock.unlock();
        return true;
    }
}
