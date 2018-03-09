package com.wxsk.platform.game.controller.aspect;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
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
            //url
            logger.info("url: {}", request.getRequestURL());
            //method
            logger.info("method: {}", request.getMethod());
            //ip
            logger.info("ip: {}", request.getRemoteAddr());
            //类方法
            logger.info("class-method: {}", joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
            //参数
            logger.info("args: {}", joinPoint.getArgs());
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
