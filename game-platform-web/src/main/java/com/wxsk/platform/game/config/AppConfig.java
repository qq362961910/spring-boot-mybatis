package com.wxsk.platform.game.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wxsk.cas.client.interceptor.AccessRequiredInteceptorWeChat;
import com.wxsk.common.redis.StringRedisClusterUtil;
import com.wxsk.common.util.mybatis.PrimarykeyGenerator;
import com.wxsk.common.util.mybatis.impl.JedisClusterPrimarykeyGeneratorCache;
import com.wxsk.common.util.spring.ApplicationUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisCluster;

import java.text.SimpleDateFormat;

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

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        return objectMapper;
    }

    @Bean
    public AccessRequiredInteceptorWeChat accessRequiredInteceptorWeChat(StringRedisClusterUtil stringRedisClusterUtil) {
        AccessRequiredInteceptorWeChat accessRequiredInteceptorWeChat = new AccessRequiredInteceptorWeChat();
        accessRequiredInteceptorWeChat.setStringRedisClusterUtil(stringRedisClusterUtil);
        return accessRequiredInteceptorWeChat;
    }

}
