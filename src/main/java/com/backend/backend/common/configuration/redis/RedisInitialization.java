package com.backend.backend.common.configuration.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class RedisInitialization {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @PostConstruct
    public void init() {
        initRedisStringTemplate();
    }

    private void initRedisStringTemplate() {
        Set<String> redisKeys = redisTemplate.keys("*");
        Set<String> stringRedisKeys = stringRedisTemplate.keys("*");

        if (redisKeys == null) {
            redisKeys = new HashSet<>();
        }

        if (stringRedisKeys == null) {
            stringRedisKeys = new HashSet<>();
        }

        for (String key : redisKeys) {
            redisTemplate.delete(key);
        }

        for (String key : stringRedisKeys) {
            stringRedisTemplate.delete(key);
        }
    }
}
