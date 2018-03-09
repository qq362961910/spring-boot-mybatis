package com.wxsk.platform.game.controller.response.wrapper;

import com.wxsk.platform.game.controller.response.vo.BaseVo;
import com.wxsk.platform.game.controller.response.vo.ResultVo;
import org.springframework.stereotype.Component;

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
    public ResultVo buildSuccess(BaseVo data) {
        return buildSuccess(null, data);
    }
    public ResultVo buildSuccess(String message) {
        return buildSuccess(message, null);
    }
    public ResultVo buildSuccess(String message, BaseVo data) {
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
    private ResultVo buildFail(BaseVo data) {
        return buildFail(null, data);
    }
    private ResultVo buildFail(String errorCode, BaseVo data) {
        return buildFail(errorCode, null, data);
    }
    private ResultVo buildFail(String errorCode, String message, BaseVo data) {
        ResultVo vo = new ResultVo();
        vo.setStatus(FAIL);
        vo.setErrorCode(errorCode);
        vo.setMessage(message);
        vo.setData(data);
        return vo;
    }

}
