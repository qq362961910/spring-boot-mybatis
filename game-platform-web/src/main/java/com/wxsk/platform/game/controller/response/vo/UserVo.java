package com.wxsk.platform.game.controller.response.vo;

public class UserVo extends BaseVo {

    private static final String STRUCTURE_TYPE = "platform-user";
    /**
     * openId
     * */
    private String openId;

    /**
     * 用户昵称
     * */
    private String nickname;

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public UserVo() {
        super(STRUCTURE_TYPE);
    }
}
