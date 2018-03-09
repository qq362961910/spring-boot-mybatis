package com.wxsk.platform.game.controller.response.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.ALWAYS)
public class ResultVo {

    @JsonProperty("status")
    private int status;

    @JsonProperty("status")
    private String errorCode;

    @JsonProperty("status")
    private String message;

    @JsonProperty("data")
    private BaseVo data;

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

    public BaseVo getData() {
        return data;
    }

    public void setData(BaseVo data) {
        this.data = data;
    }
}
