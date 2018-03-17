package com.wxsk.platform.game.controller;

import com.wxsk.cas.client.annotation.AccessRequired;
import com.wxsk.common.base.controller.BaseController;
import com.wxsk.passport.model.User;
import com.wxsk.platform.game.controller.request.dto.GameDto;
import com.wxsk.platform.game.controller.request.dto.GameDtoList;
import com.wxsk.platform.game.controller.response.vo.ResultVo;
import com.wxsk.platform.game.controller.response.wrapper.GameVoWrapper;
import com.wxsk.platform.game.controller.response.wrapper.ResultVoWrapper;
import com.wxsk.platform.game.controller.response.wrapper.UserVoWrapper;
import com.wxsk.platform.game.controller.validator.operation.Insert;
import com.wxsk.platform.game.controller.validator.operation.Update;
import com.wxsk.platform.game.dao.param.GameRequestParam;
import com.wxsk.platform.game.entity.Game;
import com.wxsk.platform.game.service.GameService;
import com.wxsk.platform.game.service.exception.ErrorCode;
import com.wxsk.platform.game.service.redis.GameRedisOperation;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 游戏相关api
 * */
@Api(tags = "游戏相关接口")
@RequestMapping("game/v1")
@RestController
public class GameController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(GameController.class);

    private GameService gameService;
    private ResultVoWrapper resultVoWrapper;
    private GameVoWrapper gameVoWrapper;
    private GameRedisOperation gameRedisOperation;
    private UserVoWrapper userVoWrapper;

    @ApiResponse(code = HttpStatus.SC_OK, message = "新增游戏成功")
    @ApiOperation(value = "新增游戏", consumes = "application/json", produces = "application/json")
    @PostMapping
    public ResultVo saveGame(@Validated(Insert.class) @RequestBody GameDto gameDto, BindingResult errors) {
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

    @ApiResponse(code = HttpStatus.SC_OK, message = "删除游戏成功")
    @ApiOperation(value = "删除游戏", produces = "application/json")
    @DeleteMapping(value = "/{id:\\d+}")
    public ResultVo removeGameById(@PathVariable("id") Long gameId){
        gameService.delete(gameId);
        return resultVoWrapper.buildSuccess();
    }

    @ApiResponse(code = HttpStatus.SC_OK, message = "修改游戏成功")
    @ApiOperation(value = "修改游戏", consumes = "application/json", produces = "application/json")
    @PutMapping
    public ResultVo modifyGame(@Validated({Insert.class, Update.class}) @RequestBody GameDto gameDto, BindingResult errors) {
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

    @ApiResponse(code = HttpStatus.SC_OK, message = "根据Id查询游戏成功")
    @ApiOperation(value = "根据Id查询游戏", produces = "application/json")
    @AccessRequired(respongseType = AccessRequired.RespongseType.JSON)
    @GetMapping(value = "/{id:\\d+}")
    public ResultVo queryGameById(@PathVariable("id") Long gameId) {
        Game game = gameService.getById(gameId);
        if(game == null) {
            return resultVoWrapper.buildFail();
        }
        Map<String, Object> data = new HashMap<>();
        data.put("game", gameVoWrapper.buildGameVo(game));
        return resultVoWrapper.buildSuccess(data);
    }

    @ApiResponse(code = HttpStatus.SC_OK, message = "根据param查询游戏成功")
    @ApiOperation(value = "根据param查询游戏", consumes = "application/json", produces = "application/json")
    @AccessRequired(respongseType = AccessRequired.RespongseType.JSON)
    @PostMapping("list")
    public ResultVo queryGameByParam(@RequestBody GameRequestParam requestParam) {
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

    @ApiResponse(code = HttpStatus.SC_OK, message = "批量插入游戏成功")
    @ApiOperation(value = "批量插入游戏", consumes = "application/json", produces = "application/json")
    @PostMapping("insert_batch")
    public ResultVo insertBatch(@Valid @RequestBody GameDtoList list, BindingResult errors) {
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

    @ApiResponse(code = HttpStatus.SC_OK, message = "获取token成功")
    @ApiOperation(value = "获取token", produces = "application/json")
    @AccessRequired(respongseType = AccessRequired.RespongseType.JSON)
    @GetMapping("user_info_exchange_code")
    public ResultVo getUserInfoExchangeCode(@RequestParam("gameId") Long gameId, HttpServletRequest request) {
        User user = getLoginUser(request);
        if(user == null) {
            logger.warn("获取用户信息交换token失败, 当前用户未登录或登录失效");
            return resultVoWrapper.buildFail(ErrorCode.NON_USER_LOGIN.getCode());
        }
        Game game = gameService.getById(gameId);
        if(game == null) {
            logger.warn("获取用户信息交换token失败, 游戏不存在");
            return resultVoWrapper.buildFail(ErrorCode.GAME_NOT_EXIST.getCode());
        }
        String code = gameRedisOperation.generateUserInfoExchangeCode(user, gameId);
        logger.info("user: {} login game: {} exchange-code: {}", user.getId(), gameId, code);
        Map<String, Object> data = new HashMap<>();
        data.put("index", game.getIndexPage());
        data.put("token", code);
        data.put("gameId", gameId);
        return resultVoWrapper.buildSuccess(data);
    }

    @ApiResponse(code = HttpStatus.SC_OK, message = "获取用户信息成功")
    @ApiOperation(value = "获取用户信息", produces = "application/json")
    @GetMapping("exchange_user_info")
    public ResultVo exchangeUserInfo(@RequestParam("gameId") Long gameId, @RequestParam("token") String token, @RequestParam("sign") String sign) throws InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, NoSuchPaddingException {
        Game game = gameService.getById(gameId);
        if(game == null) {
            logger.warn("使用token兑换用户信息失败, 游戏不存在");
            return resultVoWrapper.buildFail(ErrorCode.GAME_NOT_EXIST.getCode());
        }
        User user = gameService.exchangeUserByCode(gameId, token, sign);
        if(user == null) {
            logger.warn("使用token兑换用户信息失败, token失效或签名错误");
            return resultVoWrapper.buildFail(ErrorCode.CODE_INVALID.getCode());
        }
        Map<String, Object> data = new HashMap<>();
        data.put("gameId", gameId);
        data.put("user", userVoWrapper.buildUserVo(user, game));
        return resultVoWrapper.buildSuccess(data);
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

    public GameController(GameService gameService,
                          ResultVoWrapper resultVoWrapper,
                          GameVoWrapper gameVoWrapper,
                          GameRedisOperation gameRedisOperation,
                          UserVoWrapper userVoWrapper) {
        this.gameService = gameService;
        this.resultVoWrapper = resultVoWrapper;
        this.gameVoWrapper = gameVoWrapper;
        this.gameRedisOperation = gameRedisOperation;
        this.userVoWrapper = userVoWrapper;
    }
}
