package com.heyudev.redis.simple;

import com.github.benmanes.caffeine.cache.CacheLoader;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import com.google.common.base.Stopwatch;
import com.google.common.cache.CacheBuilder;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.cache.Cache;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.StaticScriptSource;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author supeng
 * @date 2022/03/21
 */
@Component
public class RedisCacheSimple {

    @Autowired
    RedisTemplate redisTemplate;

    String lpopScript = "local list=redis.call('lrange', KEYS[1], 0, ARGV[1]-1);" +
            "redis.call" +
            "('ltrim', KEYS[1], ARGV[1], -1);" +
            "return list;";

    String key = "test:list:pop";


    private com.google.common.cache.LoadingCache<String, String> cache = CacheBuilder.newBuilder()
            .maximumSize(5000)
            .refreshAfterWrite(20, TimeUnit.SECONDS)
            .expireAfterWrite(5, TimeUnit.SECONDS)
            .expireAfterAccess(10, TimeUnit.SECONDS)
            .build(new com.google.common.cache.CacheLoader<String, String>() {
                @Override
                public String load(String s) throws Exception {
                    String r = String.valueOf(redisTemplate.opsForValue().get(s));
                    System.out.println("cache load = " + r);
                    return r;
                }
            });

    private LoadingCache<String, String> caffeineCache = Caffeine.newBuilder()
            .maximumSize(5000)
            .expireAfterAccess(5000, TimeUnit.MILLISECONDS)
            .expireAfterWrite(10000, TimeUnit.MILLISECONDS).build(new CacheLoader<String, String>() {
                @Override
                public @Nullable String load(String s) throws Exception {
                    String r = String.valueOf(redisTemplate.opsForValue().get(s));
                    System.out.println("caffeineCache load = " + r);
                    return r;
                }
            });


    public void testCache() throws InterruptedException, ExecutionException {
        String key1 = "guava:cache:test";
        String key2 = "caffeine:cache:test";
        new Thread(() -> {
            for (int i = 10; i < 10; i++) {
                redisTemplate.opsForValue().set(key1, i + "", 60, TimeUnit.SECONDS);
                redisTemplate.opsForValue().set(key2, i + "", 60, TimeUnit.SECONDS);
                System.out.println("i = "+i);
                try {
                    TimeUnit.MILLISECONDS.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();




        System.out.println("cache 1 " + cache.get(key1) + " redis " + redisTemplate.opsForValue().get(key1));
        System.out.println("caffeine 1 " + caffeineCache.get(key2)+ " redis " + redisTemplate.opsForValue().get(key2));
        TimeUnit.MILLISECONDS.sleep(6000);
        System.out.println("cache 2 " + cache.get(key1)+ " redis " + redisTemplate.opsForValue().get(key1));
        System.out.println("caffeine 2  " + caffeineCache.get(key2)+ " redis " + redisTemplate.opsForValue().get(key2));
        TimeUnit.MILLISECONDS.sleep(11000);
        System.out.println("cache 3 " + cache.get(key1)+ " redis " + redisTemplate.opsForValue().get(key1));
        System.out.println("caffeine 3 " + caffeineCache.get(key2)+ " redis " + redisTemplate.opsForValue().get(key2));
        TimeUnit.MILLISECONDS.sleep(50000);
        System.out.println("cache 4 " + cache.get(key1)+ " redis " + redisTemplate.opsForValue().get(key1));
        System.out.println("caffeine 4 " + caffeineCache.get(key2)+ " redis " + redisTemplate.opsForValue().get(key2));
    }

    public Map<Object, Object> hmget(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    public void lpush(long count) {
        for (long i = 0L; i < count; i++) {
            redisTemplate.opsForList().leftPush(key, String.valueOf(i));
        }
        System.out.println(redisTemplate.opsForList().size(key));
    }

    /**
     * redis 版本大于6 支持count
     *
     * @param count
     */
    public void lpopList(long count) {
        redisTemplate.opsForList().leftPop(key, count);
        System.out.println(redisTemplate.opsForList().size(key));
    }

    public void lpopList2(long count) {
        DefaultRedisScript<List> script = new DefaultRedisScript<>();
        script.setResultType(List.class);
        script.setScriptSource(new StaticScriptSource(lpopScript));
        Object result = redisTemplate.execute(script, Arrays.asList(key), String.valueOf(count));
        System.out.println(result);
        System.out.println(redisTemplate.opsForList().size(key));
    }

    public void del() {
        redisTemplate.delete(key);
    }

}
