package com.wxsk.platform.game.service.remote;

import com.wxsk.common.base.service.IBaseServiceRemote;
import com.wxsk.platform.game.entity.Game;

import java.util.Map;

public interface GameServiceRemote extends IBaseServiceRemote<Game> {

    /**
     * 获取签名
     * @param params
     * @param gameId
     * @return
     */
    public String getSign(Map<String, String> params, Long gameId);

    /**
     * 验证签名
     * @param params
     * @param gameId
     * @return
     */
    public boolean verifySign(Map<String, String> params, Long gameId);
}
