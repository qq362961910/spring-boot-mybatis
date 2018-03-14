package com.wxsk.platform.game.service.exception;

public enum ErrorCode {

    /**
     * 用户未登录
     * */
    NON_USER_LOGIN(buildErrorCode("non_user_login")),
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
