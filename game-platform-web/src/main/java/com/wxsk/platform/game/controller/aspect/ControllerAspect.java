package com.wxsk.platform.game.controller.aspect;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class ControllerAspect {

    private static Logger logger = LoggerFactory.getLogger(ControllerAspect.class);
    @Autowired
    private ObjectMapper objectMapper;

    @Pointcut("execution(public * com.wxsk.platform.game.controller.*.*(..))")
    public void log() {
    }

    @Before("log()")
    public void doBefore(JoinPoint joinPoint) {
        try {
            ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = requestAttributes.getRequest();
            //url, method, ip, 类方法, 参数
            logger.info("\r\nurl: {}\r\nmethod: {}\r\nip: {}\r\nclass-method: {}\r\nargs: {}",
                    request.getRequestURL(),
                    request.getMethod(),
                    request.getRemoteAddr(),
                    joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName(),
                    joinPoint.getArgs());
        } catch (Exception e) {
            logger.error("", e);
        }

    }

    @After("log()")
    public void doAfter() {
    }

    @AfterReturning(pointcut = "log()", returning = "obj")
    public void afterReturning(Object obj) throws Exception {
        logger.info("response: {}", objectMapper.writeValueAsString(obj));
    }

}
