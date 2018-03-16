package com.wxsk.platform.game.config;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ConsumerConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.wxsk.platform.game.config.properties.DubboProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableConfigurationProperties(DubboProperties.class)
@Configuration
public class DubboConfig {

    private DubboProperties dubboProperties;


    /*与<dubbo:application/>相当.*/
    @Bean
    public ApplicationConfig applicationConfig() {
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setLogger("slf4j");
        applicationConfig.setName(dubboProperties.getApplicationName());
        return applicationConfig;
    }

    /*与<dubbo:registry/>相当*/
    @Bean
    public RegistryConfig registryConfig() {
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setAddress(dubboProperties.getRegistryAddress());
        return registryConfig;
    }

    @Bean
    public ConsumerConfig consumerConfig() {
        ConsumerConfig consumerConfig = new ConsumerConfig();
        consumerConfig.setDefault(true);
        consumerConfig.setCheck(false);
        return consumerConfig;
    }


    public DubboConfig(DubboProperties dubboProperties) {
        this.dubboProperties = dubboProperties;
    }
}
