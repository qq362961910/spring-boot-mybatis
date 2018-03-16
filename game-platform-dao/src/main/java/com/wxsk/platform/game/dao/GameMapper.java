package com.wxsk.platform.game.dao;

import com.wxsk.common.base.mapper.BaseMapper;
import com.wxsk.platform.game.dao.param.GameRequestParam;
import com.wxsk.platform.game.entity.Game;

import java.util.List;

public interface GameMapper extends BaseMapper<Game> {

    /**
     * 根据GameRequestParam查询游戏列表
     * */
    List<Game> queryByParamMap(GameRequestParam param);

    /**
     * 根据游戏id查询secret
     * */
    String querySecretByGameId(Long gameId);

    /**
     * 根据game id获取serverNotifyUrl
     * */
    String queryServerNotifyUrlByGameId(Long gameId);

    /**
     * 根据game id获取pageNotifyUrl
     * */
    String queryPageNotifyUrlByGameId(Long gameId);
}
