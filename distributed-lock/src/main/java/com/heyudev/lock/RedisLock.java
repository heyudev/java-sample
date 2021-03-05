package com.heyudev.lock;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.StaticScriptSource;

import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * redis 实现分布式锁
 *
 * @author heyudev
 * @date 2021/03/05
 */
public class RedisLock {

    /**
     * 加锁lua脚本
     */
    private static final String LOCK = "if (redis.call('exists', KEYS[1]) == 0) then " +
            "redis.call('hset', KEYS[1], ARGV[2], 1); " +
            "redis.call('pexpire', KEYS[1], ARGV[1]); " +
            "return 1; " +
            "end; " +
            "if (redis.call('hexists', KEYS[1], ARGV[2]) == 1) then " +
            "redis.call('hincrby', KEYS[1], ARGV[2], 1); " +
            "redis.call('pexpire', KEYS[1], ARGV[1]); " +
            "return 1; " +
            "end; " +
            "return 0;";

    /**
     * 解锁 lua 脚本
     */
    public static final String UNLOCK = "if (redis.call('hexists', KEYS[1], ARGV[1]) == 0) then " +
            "return nil; " +
            "end; " +
            "local counter = redis.call('hincrby', KEYS[1], ARGV[1], -1); " +
            "if (counter > 0) then " +
            "return 0; " +
            "else " +
            "redis.call('del', KEYS[1]); " +
            "return 1; " +
            "end; " +
            "return nil;";

    /**
     * 加锁
     *
     * @param redisTemplate
     * @param key
     * @param timeout
     * @return
     */
    public Boolean lock(RedisTemplate redisTemplate, String key, long timeout) {
        return redisTemplate.opsForValue().setIfAbsent(key, "1", timeout, TimeUnit.MILLISECONDS);
    }

    /**
     * 解锁
     *
     * @param redisTemplate
     * @param key
     * @return
     */
    public Boolean unlock(RedisTemplate redisTemplate, String key) {
        return redisTemplate.delete(key);
    }

    /**
     * 加锁  可重入
     *
     * @param redisTemplate
     * @param key
     * @param reentrantId
     * @param timeout
     * @return
     */
    public Boolean lock(RedisTemplate redisTemplate, String key, String reentrantId, long timeout) {
        DefaultRedisScript<Long> script = new DefaultRedisScript<>();
        script.setResultType(Long.class);
        script.setScriptSource(new StaticScriptSource(LOCK));
        Object result = redisTemplate.execute(script, Arrays.asList(key), String.valueOf(timeout), reentrantId);
        if (Objects.nonNull(result) && Objects.equals(1L, Long.valueOf(result.toString()))) {
            return true;
        }
        return false;
    }

    /**
     * 解锁 可重入
     *
     * @param redisTemplate
     * @param key
     * @param reentrantId
     * @return
     */
    public Boolean unlock(RedisTemplate redisTemplate, String key, String reentrantId) {
        DefaultRedisScript<Long> script = new DefaultRedisScript<>();
        script.setResultType(Long.class);
        script.setScriptSource(new StaticScriptSource(UNLOCK));
        Object result = redisTemplate.execute(script, Arrays.asList(key), reentrantId);
        if (Objects.isNull(result)) {
            return null;
        }
        if (Objects.equals(1L, Long.valueOf(result.toString()))) {
            return true;
        }
        return false;
    }
}
