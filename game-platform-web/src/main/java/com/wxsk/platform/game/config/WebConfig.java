package com.wxsk.platform.game.config;

import com.wxsk.platform.game.controller.interceptor.WxUserInfoInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private WxUserInfoInterceptor wxUserInfoInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(wxUserInfoInterceptor);
    }

    public WebConfig(WxUserInfoInterceptor wxUserInfoInterceptor) {
        this.wxUserInfoInterceptor = wxUserInfoInterceptor;
    }

}
