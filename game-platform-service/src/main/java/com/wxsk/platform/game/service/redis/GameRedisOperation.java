package com.wxsk.platform.game.service.redis;

import com.wxsk.passport.model.User;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class GameRedisOperation {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 生成用户信息兑换code
     * */
    public String generateUserInfoExchangeCode(User user, Long gameId) {
        String token = UUID.randomUUID().toString().replace("-", "");
        redisTemplate.opsForValue().set(token, user, 60, TimeUnit.SECONDS);
        return token;
    }

}
