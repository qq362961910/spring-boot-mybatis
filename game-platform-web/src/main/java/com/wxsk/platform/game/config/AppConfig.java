package com.wxsk.platform.game.config;

import com.wxsk.common.util.mybatis.PrimarykeyGenerator;
import com.wxsk.common.util.mybatis.impl.JedisClusterPrimarykeyGeneratorCache;
import com.wxsk.common.util.spring.ApplicationUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisCluster;

@Configuration
public class AppConfig {

    @Bean
    public ApplicationUtil applicationUtil() {
        return new ApplicationUtil();
    }

    @Bean
    public PrimarykeyGenerator primarykeyGenerator(JedisCluster jedisCluster) {
        JedisClusterPrimarykeyGeneratorCache primaryKeyGenerator = new JedisClusterPrimarykeyGeneratorCache();
        primaryKeyGenerator.setJedisCluster(jedisCluster);
        primaryKeyGenerator.setUpercase(true);
        return primaryKeyGenerator;
    }

}
