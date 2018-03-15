package com.wxsk.platform.game.controller.interceptor;

import com.wxsk.cas.client.interceptor.AccessRequiredInteceptorWeChat;
import com.wxsk.passport.model.User;
import com.wxsk.platform.game.util.WebUtil;
import org.springframework.lang.Nullable;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WxUserInfoInterceptor extends AccessRequiredInteceptorWeChat {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean ok;
        if (handler instanceof HandlerMethod) {
            ok = super.preHandle(request, response, handler);
            if (ok) {
                WebUtil.setCurrentUser((User)request.getAttribute("curent_login_user"));
            }
        }
        else {
            ok = true;
        }
        return ok;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        WebUtil.removeCurrentUser();
    }
}
