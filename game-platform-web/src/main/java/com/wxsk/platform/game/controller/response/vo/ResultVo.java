package com.wxsk.platform.game.controller.response.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Map;

@ApiModel(value = "响应")
@JsonInclude(JsonInclude.Include.ALWAYS)
public class ResultVo {

    @ApiModelProperty(value="状态标志",example="1")
    @JsonProperty("status")
    private int status;

    @ApiModelProperty(value="响应码",example="200")
    @JsonProperty("errorCode")
    private String errorCode;

    @ApiModelProperty(value="响应消息",example="成功")
    @JsonProperty("message")
    private String message;

    @ApiModelProperty(value="数据",example="{count: 1}")
    @JsonProperty("data")
    private Map<String, Object> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}
