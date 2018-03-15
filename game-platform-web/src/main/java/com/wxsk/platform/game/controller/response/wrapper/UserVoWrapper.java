package com.wxsk.platform.game.controller.response.wrapper;

import com.wxsk.passport.model.User;
import com.wxsk.platform.game.controller.response.vo.UserVo;
import org.springframework.stereotype.Component;

@Component
public class UserVoWrapper {

    public UserVo buildUserVo(User user) {
        if(user == null) {
            return null;
        }
        UserVo userVo = new UserVo();
        userVo.setOpenId(String.valueOf(user.getId()));
        userVo.setNickname(user.getNickname());
        return userVo;
    }
}
