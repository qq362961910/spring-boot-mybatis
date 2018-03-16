package com.wxsk.platform.game.service;


import java.util.Map;

public interface SignService {

    /**
     * 获取签名
     * @param params
     * @param gameId
     * @return
     */
    String getSign(Map<String, String> params, Long gameId);
    /**
     * 验证签名
     * @param params(包含sign)
     * @param gameId
     * @return
     */
    boolean verifySign(Map<String, String> params, Long gameId);

}
