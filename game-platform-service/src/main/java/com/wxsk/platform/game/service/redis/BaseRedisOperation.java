package com.wxsk.platform.game.service.redis;

import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;

public abstract class BaseRedisOperation {

    @Resource
    protected RedisTemplate<String, Object> redisTemplate;

    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public void removeKey(String key) {
        redisTemplate.delete(key);
    }

}
