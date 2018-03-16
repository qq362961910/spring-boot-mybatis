package com.wxsk.platform.game.service.impl;

import com.wxsk.common.exception.ServiceException;
import com.wxsk.platform.game.dao.GameMapper;
import com.wxsk.platform.game.service.SignService;
import com.wxsk.platform.game.service.exception.ErrorCode;
import com.wxsk.platform.game.service.helper.SecretHelper;
import com.wxsk.platform.game.service.remote.SignServiceRemote;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Transactional(readOnly = true)
@Service
public class SignServiceImpl implements SignService, SignServiceRemote {

    private GameMapper gameMapper;
    private SecretHelper secretHelper;

    /**
     * 获取签名
     * @param params 参数
     * @param gameId 游戏id
     * @return 签名
     */
    public String getSign(Map<String, String> params, Long gameId) {
        String secret = gameMapper.querySecretByGameId(gameId);
        if(secret == null){
            throw new ServiceException(ErrorCode.GAME_NOT_EXIST.getCode());
        }
        return secretHelper.createSign(params, secret);
    }

    /**
     * 验证签名
     * @param params 参数(包含sign)
     * @param gameId 游戏id
     * @return 验证结果
     */
    public boolean verifySign(Map<String, String> params, Long gameId) {
        String secret = gameMapper.querySecretByGameId(gameId);
        return secret != null && secretHelper.verifySign(params, secret);
    }

    public SignServiceImpl(GameMapper gameMapper, SecretHelper secretHelper) {
        this.gameMapper = gameMapper;
        this.secretHelper = secretHelper;
    }
}
