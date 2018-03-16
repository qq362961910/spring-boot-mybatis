package com.wxsk.platform.game.controller.response.wrapper;

import com.wxsk.passport.model.User;
import com.wxsk.platform.game.controller.response.vo.UserVo;
import com.wxsk.platform.game.service.helper.SecretHelper;
import org.springframework.stereotype.Component;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Component
public class UserVoWrapper {

    private SecretHelper secretHelper;

    public UserVo buildUserVo(User user, String openIdSalt) throws IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {
        if(user == null) {
            return null;
        }
        UserVo userVo = new UserVo();
        userVo.setOpenId(secretHelper.encryptUserId(user.getId(), openIdSalt));
        userVo.setNickname(user.getNickname());
        userVo.setAvatar(user.getPhotoUrl());
        return userVo;
    }

    public UserVoWrapper(SecretHelper secretHelper) {
        this.secretHelper = secretHelper;
    }
}
