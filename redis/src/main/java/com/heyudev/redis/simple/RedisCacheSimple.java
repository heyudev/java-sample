package com.heyudev.redis.simple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.StaticScriptSource;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * @author supeng
 * @date 2022/03/21
 */
@Component
public class RedisCacheSimple {

    @Autowired
    RedisTemplate redisTemplate;

    String lpopScript = "local list=redis.call('lrange', KEYS[1], 0, ARGV[1]-1);" +
            "redis.call('ltrim', KEYS[1], ARGV[1], -1);" +
            "return list;";

    String key = "test:list:pop";


    public void lpush(long count) {
        for (long i = 0L; i < count; i++) {
            redisTemplate.opsForList().leftPush(key, String.valueOf(i));
        }
        System.out.println(redisTemplate.opsForList().size(key));
    }

    /**
     * redis 版本大于6 支持count
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
