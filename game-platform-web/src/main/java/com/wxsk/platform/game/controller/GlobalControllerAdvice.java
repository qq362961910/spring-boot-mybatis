package com.wxsk.platform.game.controller;

import com.wxsk.common.exception.BusinessException;
import com.wxsk.platform.game.controller.response.wrapper.ResultVoWrapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@ControllerAdvice
public class GlobalControllerAdvice {

    private static final Logger logger = LogManager.getLogger(GlobalControllerAdvice.class);

    private ResultVoWrapper resultVoWrapper;

    /**
     * 404
     */
    @ResponseBody
    @ExceptionHandler({NoHandlerFoundException.class})
    public Object noHandlerFound(NoHandlerFoundException e) {
        logger.error("no handler found", e);
        return resultVoWrapper.buildFail("404");
    }

    @ResponseBody
    @RequestMapping(value = "/404")
    public Object resourceNotFound(HttpServletRequest request, HttpServletResponse response) {
        logger.error("page not found, url: {}", request.getRequestURL());
        return resultVoWrapper.buildFail("page_not_found");
    }

    /**
     * 500
     */
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Object exception(Exception e) {
        if (e instanceof BusinessException) {
            BusinessException businessException = (BusinessException) e;
            logger.error("business exception, error code: {}, error msg: {}", businessException.getErrorCode(), businessException.getErrorMessage());
            return resultVoWrapper.buildFail(businessException.getErrorCode(), businessException.getErrorMessage());
        } else {
            logger.error("runtime exception", e);
            return resultVoWrapper.buildFail("500");
        }
    }

    public GlobalControllerAdvice(ResultVoWrapper resultVoWrapper) {
        this.resultVoWrapper = resultVoWrapper;
    }
}
