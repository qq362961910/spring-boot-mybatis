package com.wxsk.platform.game.entity;

import com.wxsk.common.base.entity.BaseEntity;

import java.util.Date;

/**
 * 游戏元素
 * */
public class Game extends BaseEntity {

    /**
     * 游戏名称
     * */
    private String name;

    /**
     * icon地址
     * */
    private String icon;

    /**
     * 描述
     * */
    private String description;

    /**
     * 商户id
     * */
    private Long merchantId;

    /**
     * 创建时间
     * */
    private Date createTime;

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
     * 标签1
     * */
    private String label1;

    /**
     * 标签2
     * */
    private String label2;

    /**
     * 标签3
     * */
    private String label3;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
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

    public String getLabel1() {
        return label1;
    }

    public void setLabel1(String label1) {
        this.label1 = label1;
    }

    public String getLabel2() {
        return label2;
    }

    public void setLabel2(String label2) {
        this.label2 = label2;
    }

    public String getLabel3() {
        return label3;
    }

    public void setLabel3(String label3) {
        this.label3 = label3;
    }
}
