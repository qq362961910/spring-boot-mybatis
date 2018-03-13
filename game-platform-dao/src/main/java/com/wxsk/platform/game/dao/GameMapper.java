package com.wxsk.platform.game.dao;

import com.wxsk.common.base.mapper.BaseMapper;
import com.wxsk.platform.game.dao.param.GameRequestParam;
import com.wxsk.platform.game.entity.Game;

import java.util.List;

public interface GameMapper extends BaseMapper<Game> {
    List<Game> queryByParamMap(GameRequestParam param);
}
