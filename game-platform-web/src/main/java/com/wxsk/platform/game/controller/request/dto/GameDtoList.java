package com.wxsk.platform.game.controller.request.dto;

import com.wxsk.platform.game.controller.validator.ListContentValidate;

import java.util.List;

public class GameDtoList {

    @ListContentValidate(message = "validate failed")
    private List<GameDto> gameDtoList;

    public List<GameDto> getGameDtoList() {
        return gameDtoList;
    }

    public void setGameDtoList(List<GameDto> gameDtoList) {
        this.gameDtoList = gameDtoList;
    }
}
