package com.wxsk.platform.game.controller.response.wrapper;

import com.wxsk.platform.game.controller.response.vo.ResultVo;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ResultVoWrapper {

    private static final int FAIL = 0;

    private static final int SUCCESS = 1;

    /**
     * 成功
     * */
    public ResultVo buildSuccess() {
        return buildSuccess(null, null);
    }
    public ResultVo buildSuccess(Map<String, Object> data) {
        return buildSuccess(null, data);
    }
    public ResultVo buildSuccess(String message) {
        return buildSuccess(message, null);
    }
    public ResultVo buildSuccess(String message, Map<String, Object> data) {
        ResultVo vo = new ResultVo();
        vo.setStatus(SUCCESS);
        vo.setErrorCode(null);
        vo.setMessage(message);
        vo.setData(data);
        return vo;
    }

    /**
     * 失败
     * */
    public ResultVo buildFail() {
        return buildFail(null, (String) null);
    }
    public ResultVo buildFail(String errorCode) {
        return buildFail(errorCode, (String)null);
    }
    public ResultVo buildFail(String errorCode, String msg) {
        return buildFail(errorCode, msg, null);
    }
    public ResultVo buildFail(String errorCode, Map<String, Object> data) {
        return buildFail(errorCode, null, data);
    }
    public ResultVo buildFail(String errorCode, String message, Map<String, Object> data) {
        ResultVo vo = new ResultVo();
        vo.setStatus(FAIL);
        vo.setErrorCode(errorCode);
        vo.setMessage(message);
        vo.setData(data);
        return vo;
    }

}
