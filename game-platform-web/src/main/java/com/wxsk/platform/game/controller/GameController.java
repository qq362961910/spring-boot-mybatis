package com.wxsk.platform.game.controller;

import com.wxsk.platform.game.controller.request.dto.GameDto;
import com.wxsk.platform.game.controller.response.wrapper.ResultVoWrapper;
import com.wxsk.platform.game.service.GameService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 游戏相关api
 * */
@RequestMapping("game/v1")
@RestController
public class GameController {

    private GameService gameService;
    private ResultVoWrapper resultVoWrapper;

    @PostMapping
    public Object saveGame(@RequestBody GameDto gameDto) {
        gameService.insert(gameDto);
        return resultVoWrapper.buildSuccess();
    }

    public GameController(GameService gameService, ResultVoWrapper resultVoWrapper) {
        this.gameService = gameService;
        this.resultVoWrapper = resultVoWrapper;
    }
}
