package com.wxsk.platform.game.entity;

import com.wxsk.common.base.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 游戏元素
 * */
public class Game extends BaseEntity {

    /**
     * 游戏名称
     * */
    @ApiModelProperty(value="游戏名称",example="热血江湖")
    private String name;
    /**
     * 首页
     * */
    @ApiModelProperty(value="游戏首页地址",example="http://h5.aidou.51play.com/zwwj_two")
    private String indexPage;
    /**
     * icon地址
     * */
    @ApiModelProperty(value="游戏icon",example="img/xiuxian.png")
    private String icon;
    /**
     * 秘钥
     * */
    @ApiModelProperty(value="密钥",example="abc")
    private String secret;

    /**
     * 服务端通知地址
     * */
    @ApiModelProperty(value="服务器后端通知地址",example="http://h5.aidou.51play.com/zwwj_two")
    private String serverNotifyUrl;

    /**
     * 前端通知地址
     * */
    @ApiModelProperty(value="服务器前端通知地址",example="http://h5.aidou.51play.com/zwwj_two")
    private String pageNotifyUrl;

    /**
     * 描述
     * */
    @ApiModelProperty(value="游戏简述",example="这是一个很好玩的游戏")
    private String description;

    /**
     * 商户id
     * */
    @ApiModelProperty(value="游戏所属商户id",example="1000", readOnly = true)
    private Long merchantId;

    /**
     * 创建时间
     * */
    @ApiModelProperty(value="游戏创建时间",example="2010-10-01 10:10:10", readOnly = true)
    private Date createTime;

    /**
     * 状态
     * */
    @ApiModelProperty(value="游戏状态",example="1")
    private Integer status;

    /**
     * 星级
     * 满星为10
     * */
    @ApiModelProperty(value="游戏星级",example="1")
    private Integer star;

    /**
     * 标签1
     * */
    @ApiModelProperty(value="标签1",example="标签1")
    private String label1;

    /**
     * 标签2
     * */
    @ApiModelProperty(value="标签2",example="标签2")
    private String label2;

    /**
     * 标签3
     * */
    @ApiModelProperty(value="标签3",example="标签3")
    private String label3;

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

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getServerNotifyUrl() {
        return serverNotifyUrl;
    }

    public void setServerNotifyUrl(String serverNotifyUrl) {
        this.serverNotifyUrl = serverNotifyUrl;
    }

    public String getPageNotifyUrl() {
        return pageNotifyUrl;
    }

    public void setPageNotifyUrl(String pageNotifyUrl) {
        this.pageNotifyUrl = pageNotifyUrl;
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
