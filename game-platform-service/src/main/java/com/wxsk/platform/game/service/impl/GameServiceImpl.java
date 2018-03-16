package com.wxsk.platform.game.service.impl;

import com.wxsk.common.base.service.impl.BaseServiceImpl;
import com.wxsk.passport.model.User;
import com.wxsk.platform.game.client.GamePlatFormClient;
import com.wxsk.platform.game.dao.GameMapper;
import com.wxsk.platform.game.dao.param.GameRequestParam;
import com.wxsk.platform.game.entity.Game;
import com.wxsk.platform.game.service.GameService;
import com.wxsk.platform.game.service.redis.GameRedisOperation;
import com.wxsk.platform.game.service.remote.GameServiceRemote;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Transactional(rollbackFor = Exception.class)
@Service
public class GameServiceImpl extends BaseServiceImpl<Game,GameMapper> implements GameService, GameServiceRemote {

    private GameMapper gameMapper;

    private GameRedisOperation gameRedisOperation;

    @Override
    public List<Game> queryByParamMap(GameRequestParam param) {
        return gameMapper.queryByParamMap(param);
    }

    @Override
    public Long insert(Game entity) {
        checkInsert(entity);
        return super.insert(entity);
    }

    @Override
    public Long insertBatch(List<? extends Game> entityList) {
        entityList.forEach(this::checkUpdate);
        return super.insertBatch(entityList);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public User exchangeUserByCode(Long gameId, String code, String sign) {
        return (User)gameRedisOperation.get(code);
    }

    @Override
    public GameMapper getDefaulteMapper() {
        return gameMapper;
    }

    public GameServiceImpl(GameMapper gameMapper, GameRedisOperation gameRedisOperation) {
        this.gameMapper = gameMapper;
        this.gameRedisOperation = gameRedisOperation;
    }

    private void checkInsert(Game game) {
        game.setCreateTime(new Date());
        game.setStar(0);
    }
    private void checkUpdate(Game game) {
        checkInsert(game);
    }

    /**
     * 获取签名
     * @param params
     * @param gameId
     * @return
     */
    public String getSign(Map<String, String> params, Long gameId) {

        Game game = gameMapper.selectByPrimaryKey(gameId);
        if(game == null){
            return null;
        }
        return GamePlatFormClient.createSign(params, game.getSecret());
    }

    /**
     * 验证签名
     * @param params(包含sign)
     * @param gameId
     * @return
     */
    public boolean verifySign(Map<String, String> params, Long gameId) {

        Game game = gameMapper.selectByPrimaryKey(gameId);
        if(game == null){
            return false;
        }
        return GamePlatFormClient.verifySign(params, game.getSecret());
    }
}
