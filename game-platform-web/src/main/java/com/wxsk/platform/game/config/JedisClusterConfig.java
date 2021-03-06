package com.wxsk.platform.game.config;

import com.wxsk.common.redis.StringRedisClusterUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;

@Configuration
@ConditionalOnClass({ JedisCluster.class })
public class JedisClusterConfig {

    @Value("${spring.redis.cluster.nodes}")
    private String clusterNodes;

    @Bean
    public JedisCluster jedisCluster() {
        String[] serverArray = clusterNodes.split(",");
        Set<HostAndPort> nodes = new HashSet<>();
        for (String ipPort : serverArray) {
            String[] ipPortPair = ipPort.split(":");
            nodes.add(new HostAndPort(ipPortPair[0].trim(), Integer.valueOf(ipPortPair[1].trim())));
        }
        return new JedisCluster(nodes, 2000);
    }

    @Bean
    public StringRedisClusterUtil stringRedisClusterUtil(JedisCluster jedisCluster) {
        StringRedisClusterUtil stringRedisClusterUtil = new StringRedisClusterUtil();
        stringRedisClusterUtil.setJedisCluster(jedisCluster);
        return stringRedisClusterUtil;
    }
}
