package com.wxsk.platform.game.service;

import com.wxsk.common.base.service.IBaseService;
import com.wxsk.passport.model.User;
import com.wxsk.platform.game.dao.GameMapper;
import com.wxsk.platform.game.dao.param.GameRequestParam;
import com.wxsk.platform.game.entity.Game;

import java.util.List;

public interface GameService extends IBaseService<Game,GameMapper> {

    List<Game> queryByParamMap(GameRequestParam param);

    User exchangeUserByCode(Long gameId, String code, String sign);

}
