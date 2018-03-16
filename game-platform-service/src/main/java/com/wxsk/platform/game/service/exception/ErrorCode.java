package com.wxsk.platform.game.service.exception;

public enum ErrorCode {

    /**
     * 用户未登录
     * */
    NON_USER_LOGIN(buildErrorCode("NON_USER_LOGIN")),

    /**
     * 游戏不存在
     * */
    GAME_NOT_EXIST(buildErrorCode("GAME_NOT_EXIST")),

    /**
     * game secret不存在
     * */
    GAME_SECRET_NOT_EXIST(buildErrorCode("GAME_SECRET_NOT_EXIST")),

    /**
     * code无效
     * */
    CODE_INVALID(buildErrorCode("CODE_INVALID"))

    ;

    private static final String PREFIX = "GAME_PLATFORM";
    private String code;


    private static String buildErrorCode(String code){
        return PREFIX + ":" + code;
    }
    ErrorCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
