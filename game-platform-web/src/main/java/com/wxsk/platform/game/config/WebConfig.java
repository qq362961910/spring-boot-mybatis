package com.wxsk.platform.game.config;

import com.wxsk.cas.client.interceptor.AccessRequiredInteceptorWeChat;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private AccessRequiredInteceptorWeChat accessRequiredInteceptorWeChat;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(accessRequiredInteceptorWeChat);
    }

    public WebConfig(AccessRequiredInteceptorWeChat accessRequiredInteceptorWeChat) {
        this.accessRequiredInteceptorWeChat = accessRequiredInteceptorWeChat;
    }
}
