package com.wxsk.platform.game.controller.response.vo;

public abstract class BaseVo {

    /**
     * 结构类型
     */
    protected String structureType;

    public String getStructureType() {
        return structureType;
    }

    public void setStructureType(String structureType) {
        this.structureType = structureType;
    }

    public BaseVo(String structureType) {
        this.structureType = structureType;
    }
}
