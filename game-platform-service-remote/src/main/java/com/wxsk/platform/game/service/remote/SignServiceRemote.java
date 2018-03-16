package com.wxsk.platform.game.service.remote;

import java.util.Map;

public interface SignServiceRemote {

    /**
     * 获取签名
     * @param params 参数
     * @param gameId 游戏id
     * @return 签名
     */
    String getSign(Map<String, String> params, Long gameId);

    /**
     * 验证签名
     * @param params 参数
     * @param gameId 游戏id
     * @return 验签结果
     */
    boolean verifySign(Map<String, String> params, Long gameId);
}
