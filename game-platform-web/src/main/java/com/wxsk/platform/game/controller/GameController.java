package com.wxsk.platform.game.controller;

import com.wxsk.platform.game.controller.request.dto.GameDto;
import com.wxsk.platform.game.controller.request.dto.GameDtoList;
import com.wxsk.platform.game.controller.response.vo.ResultVo;
import com.wxsk.platform.game.controller.response.wrapper.GameVoWrapper;
import com.wxsk.platform.game.controller.response.wrapper.ResultVoWrapper;
import com.wxsk.platform.game.controller.validator.operation.Insert;
import com.wxsk.platform.game.controller.validator.operation.Update;
import com.wxsk.platform.game.dao.param.GameRequestParam;
import com.wxsk.platform.game.entity.Game;
import com.wxsk.platform.game.service.GameService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 游戏相关api
 * */
@Validated
@RequestMapping("game/v1")
@RestController
public class GameController {

    private static final Logger logger = LoggerFactory.getLogger(GameController.class);

    private GameService gameService;
    private ResultVoWrapper resultVoWrapper;
    private GameVoWrapper gameVoWrapper;

    @ApiOperation("新增游戏")
    @PostMapping
    public Object saveGame(@Validated(Insert.class) @RequestBody GameDto gameDto, BindingResult errors) {
        ResultVo vo = dealErrors(errors);
        if(vo != null) {
            return vo;
        }
        Long id = gameService.insert(gameDto);
        Game newGame = gameService.getById(id);
        if(newGame == null) {
            return resultVoWrapper.buildFail();
        }
        Map<String, Object> data = new HashMap<>();
        data.put("game", gameVoWrapper.buildGameVo(newGame));
        return resultVoWrapper.buildSuccess(data);
    }

    @ApiOperation("删除游戏")
    @DeleteMapping(value = "/{id:\\d+}")
    public Object removeGameById(@PathVariable("id") Long gameId){
        gameService.delete(gameId);
        return resultVoWrapper.buildSuccess();
    }

    @ApiOperation("修改游戏")
    @PutMapping
    public Object modifyGame(@Validated({Insert.class, Update.class}) @RequestBody GameDto gameDto, BindingResult errors) {
        ResultVo vo = dealErrors(errors);
        if(vo != null) {
            return vo;
        }
        gameService.updateByPrimaryKey(gameDto);
        Game game = gameService.getById(gameDto.getId());
        Map<String, Object> data = new HashMap<>();
        data.put("game", gameVoWrapper.buildGameVo(game));
        return resultVoWrapper.buildSuccess(data);
    }

    @ApiOperation("根据Id查询游戏")
    @GetMapping(value = "/{id:\\d+}")
    public Object queryGameById(@PathVariable("id") Long gameId) {
        Game game = gameService.getById(gameId);
        if(game == null) {
            return resultVoWrapper.buildFail();
        }
        Map<String, Object> data = new HashMap<>();
        data.put("game", gameVoWrapper.buildGameVo(game));
        return resultVoWrapper.buildSuccess(data);
    }
    @ApiOperation("根据param查询游戏")
    @PostMapping("list")
    public Object queryGameByParam(@RequestBody GameRequestParam requestParam) {
        if(requestParam.getPageNumber() < 0) {
            requestParam.setPageNumber(0);
        }
        if(requestParam.getPageSize() < 1 || requestParam.getPageSize() > 20) {
            requestParam.setPageSize(5);
        }
        List<Game> gameList = gameService.queryByParamMap(requestParam);
        Map<String, Object> data = new HashMap<>();
        data.put("gameList", gameVoWrapper.buildGameVoList(gameList));
        return resultVoWrapper.buildSuccess(data);
    }

    @ApiOperation("批量插入游戏")
    @PostMapping("insert_batch")
    public Object insertBatch(@Valid @RequestBody GameDtoList list, BindingResult errors) {
        ResultVo vo = dealErrors(errors);
        if(vo != null) {
            return vo;
        }
        List<GameDto> gameDtoList = list.getGameDtoList();
        if(gameDtoList != null && gameDtoList.size() > 0) {
            gameService.insertBatch(gameDtoList);
        }
        return resultVoWrapper.buildSuccess();
    }


    private ResultVo dealErrors(BindingResult errors) {
        if(errors.hasErrors()) {
            StringBuilder sb = new StringBuilder();
            errors.getAllErrors().forEach(error -> sb.append(error.getDefaultMessage()).append(","));
            if(sb.length() > 0) {
                sb.deleteCharAt(sb.length() - 1);
            }
            logger.error("error codes: {}", sb);
            return resultVoWrapper.buildFail(sb.toString());
        }
        return null;
    }

    public GameController(GameService gameService, ResultVoWrapper resultVoWrapper, GameVoWrapper gameVoWrapper) {
        this.gameService = gameService;
        this.resultVoWrapper = resultVoWrapper;
        this.gameVoWrapper = gameVoWrapper;
    }

}
