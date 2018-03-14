package com.wxsk.platform.game.controller.response.wrapper;

import com.wxsk.platform.game.controller.response.vo.GameVo;
import com.wxsk.platform.game.entity.Game;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class GameVoWrapper {

    public GameVo buildGameVo(Game game) {
        if(game == null) {
            return null;
        }
        GameVo gameVo = new GameVo();
        gameVo.setId(game.getId());
        gameVo.setName(game.getName());
        gameVo.setIndexPage(game.getIndexPage());
        gameVo.setIcon(game.getIcon());
        gameVo.setDescription(game.getDescription());
        if(game.getCreateTime() != null) {
            gameVo.setCreateTime(game.getCreateTime().getTime());
        }
        gameVo.setStatus(game.getStatus());
        gameVo.setStar(game.getStar());
        if(game.getLabel1() != null) {
            gameVo.getLabels().add(game.getLabel1());
        }
        if(game.getLabel2() != null) {
            gameVo.getLabels().add(game.getLabel2());
        }
        if(game.getLabel3() != null) {
            gameVo.getLabels().add(game.getLabel3());
        }
        return gameVo;
    }

    public List<GameVo> buildGameVoList(List<Game> gameList) {
        if(gameList == null || gameList.size() == 0) {
            return Collections.emptyList();
        }
        List<GameVo> gameVoList = new ArrayList<>(gameList.size());
        gameList.forEach(game -> {
            gameVoList.add(buildGameVo(game));
        });
        return gameVoList;
    }

}
