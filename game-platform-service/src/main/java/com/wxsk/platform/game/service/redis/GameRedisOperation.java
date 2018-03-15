package com.wxsk.platform.game.service.redis;

import com.wxsk.passport.model.User;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GameRedisOperation extends BaseRedisOperation{

    /**
     * 生成用户信息兑换code
     * */
    public String generateUserInfoExchangeCode(User user, Long gameId) {
        String token = UUID.randomUUID().toString().replace("-", "");
        redisTemplate.opsForValue().set(token, user);
        return token;
    }

}
