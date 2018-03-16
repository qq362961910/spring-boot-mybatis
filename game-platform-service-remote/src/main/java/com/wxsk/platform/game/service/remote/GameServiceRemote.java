package com.wxsk.platform.game.service.remote;

public interface GameServiceRemote {

    /**
     * 根据game id获取serverNotifyUrl
     * */
    String getServerNotifyUrlByGameId(Long gameId);

    /**
     * 根据game id获取pageNotifyUrl
     * */
    String getPageNotifyUrlByGameId(Long gameId);
}
