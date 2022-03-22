package com.heyudev.redis;

import com.heyudev.redis.simple.RedisCacheSimple;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RedisApplicationTests {

    @Autowired
    RedisCacheSimple redisCacheSimple;

    @Test
    void contextLoads() {
        redisCacheSimple.lpush(20);
//        redisCacheSimple.lpopList(5);
        redisCacheSimple.lpopList2(5);
        redisCacheSimple.del();
    }


}
