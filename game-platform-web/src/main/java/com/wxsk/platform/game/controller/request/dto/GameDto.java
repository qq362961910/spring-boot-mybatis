package com.wxsk.platform.game.controller.request.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.wxsk.platform.game.controller.validator.operation.Insert;
import com.wxsk.platform.game.controller.validator.operation.Update;
import com.wxsk.platform.game.entity.Game;
import com.wxsk.platform.game.json.deserializer.CustomJsonDateDeserializer;
import io.swagger.annotations.ApiModel;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@ApiModel(value = "Game对象")
public class GameDto extends Game {

    @Override
    public void setDescription(String description) {
        super.setDescription(description);
    }

    @NotNull(message = "game_platform:game_id_null", groups = Update.class)
    @Override
    public Long getId() {
        return super.getId();
    }

    @NotBlank(message = "game_platform:game_name_empty", groups = {Insert.class, Update.class})
    @Override
    public String getName() {
        return super.getName();
    }

    @NotBlank(message = "game_platform:game_icon_empty", groups = {Insert.class, Update.class})
    @Override
    public String getIcon() {
        return super.getIcon();
    }

    @NotBlank(message = "game_platform:game_description_empty", groups = {Insert.class, Update.class})
    @Override
    public String getDescription() {
        return super.getDescription();
    }

    @NotBlank(message = "game_platform:game_index_page_empty", groups = {Insert.class, Update.class})
    @Override
    public String getIndexPage() {
        return super.getIndexPage();
    }

    @JsonDeserialize(using = CustomJsonDateDeserializer.class)
    @Override
    public void setCreateTime(Date createTime) {
        super.setCreateTime(createTime);
    }
}
