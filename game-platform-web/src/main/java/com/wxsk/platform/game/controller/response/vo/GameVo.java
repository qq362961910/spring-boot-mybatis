package com.wxsk.platform.game.controller.response.vo;

import java.util.ArrayList;
import java.util.List;

public class GameVo extends BaseVo {

    private static final String STRUCTURE_TYPE = "platform-game";

    /**
     * primary key
     * */
    private Long id;

    /**
     * 游戏名称
     * */
    private String name;

    /**
     * 首页
     * */
    private String indexPage;

    /**
     * icon地址
     * */
    private String icon;

    /**
     * 描述
     * */
    private String description;

    /**
     * 创建时间
     * */
    private Long createTime;

    /**
     * 状态
     * */
    private Integer status;

    /**
     * 星级
     * 满星为10
     * */
    private Integer star;


    /**
     * 标签
     * */
    private List<String> labels = new ArrayList<>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIndexPage() {
        return indexPage;
    }

    public void setIndexPage(String indexPage) {
        this.indexPage = indexPage;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getStar() {
        return star;
    }

    public void setStar(Integer star) {
        this.star = star;
    }

    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }

    public GameVo() {
        super(STRUCTURE_TYPE);
    }
}
