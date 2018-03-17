package com.wxsk.platform.game.controller.response.wrapper;

import com.alibaba.dubbo.config.annotation.Reference;
import com.wxsk.merchant.entity.Merchant;
import com.wxsk.merchant.service.remote.MerchantServiceRemote;
import com.wxsk.passport.model.User;
import com.wxsk.platform.game.controller.response.vo.UserVo;
import com.wxsk.platform.game.entity.Game;
import com.wxsk.platform.game.service.helper.SecretHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Component
public class UserVoWrapper {

    private static final Logger logger = LoggerFactory.getLogger(UserVoWrapper.class);

    private SecretHelper secretHelper;
    @Reference(version = "1.0", timeout = 5000)
    private MerchantServiceRemote merchantServiceRemote;

    public UserVo buildUserVo(User user, Game game) throws IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {
        if(user == null) {
            return null;
        }
        UserVo userVo = new UserVo();
        if(game != null) {
            Merchant merchant = merchantServiceRemote.getById(game.getMerchantId());
            if(merchant != null) {
                String openIdSalt = merchant.getOpenIdSalt();
                if(openIdSalt != null && openIdSalt.trim().length() > 0) {
                    userVo.setOpenId(secretHelper.encryptUserId(user.getId(), merchant.getOpenIdSalt()));
                } else {
                    logger.warn("merchant id: {} has not openIdSalt", game.getMerchantId());
                }
            } else {
                logger.warn("no merchant found, merchant id: {}", game.getMerchantId());
            }
        }
        userVo.setNickname(user.getNickname());
        userVo.setAvatar(user.getPhotoUrl());
        return userVo;
    }

    public UserVoWrapper(SecretHelper secretHelper) {
        this.secretHelper = secretHelper;
    }
}
