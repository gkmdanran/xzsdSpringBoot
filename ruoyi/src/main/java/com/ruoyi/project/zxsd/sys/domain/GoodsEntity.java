package com.ruoyi.project.zxsd.sys.domain;

import com.ruoyi.framework.web.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
/**
 * @author gkm
 * @version 1.0
 * @date 2020/5/25 9:23
 */
@Getter
@Setter
@ApiModel(value = "商品对象", description = "商品对象")
public class GoodsEntity extends BaseEntity {
    /**
     * 序列化id
     */
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    private String id;
    /**
     * sku编号
     */
    @ApiModelProperty(value = "sku编号")
    private String skuNo;
    /**
     * sku名称
     */
    @ApiModelProperty(value = "sku名称")
    private String skuName;
    /**
     * 门店编码
     */
    @ApiModelProperty(value = "门店编码")
    private String outsideNo;
    @ApiModelProperty(value = "门店名称")
    private String storeName;
    /**
     * 商品分类编码
     */
    @ApiModelProperty(value = "商品二级分类编码")
    private String cateCode;
    private String cateOneName;
    private String cateTwoName;

    /**
     * 定价
     */
    @ApiModelProperty(value = "定价")
    private double fixPrice;
    /**
     * 定成本价
     */
    @ApiModelProperty(value = "成本价")
    private double costPrice;
    /**
     * 售价
     */
    @ApiModelProperty(value = "售价")
    private double sellingPrice;
    /**
     * 销量
     */
    @ApiModelProperty(value = "销量")
    private int saleCnt;
    /**
     * 渠道(0行走书店 1 京东万家)
     */
    @ApiModelProperty(value = "渠道(0行走书店 1 京东万家)")
    private String channelType;
    /**
     * 序号
     */
    @ApiModelProperty(value = "序号")
    private int sortNo;
    /**
     * 介绍
     */
    @ApiModelProperty(value = "介绍")
    private String detail;
    /**
     * 评价星级
     */
    @ApiModelProperty(value = "评价星级")
    private String starLevel;
    /**
     * 上架时间
     */
    @ApiModelProperty(value = "上架时间")
    private Date putawayTime;
    /**
     * 状态；1上架、0下架
     */
    @ApiModelProperty(value = "状态；1上架、0下架")
    private String status;
    /**
     * 浏览量
     */
    @ApiModelProperty(value = "浏览量")
    private int browseCount;
    /**
     * 作者
     */
    @ApiModelProperty(value = "作者")
    private String author;
    /**
     * 广告词
     */
    @ApiModelProperty(value = "广告词")
    private String advertising;
    /**
     * isbn码
     */
    @ApiModelProperty(value = "isbn码")
    private String isbn;
    /**
     * 出版社
     */
    @ApiModelProperty(value = "出版社")
    private String press;
    /**
     * 库存数量
     */
    @ApiModelProperty(value = "库存数量")
    private int amountCnt;
    /**
     * 下架时间
     */
    @ApiModelProperty(value = "下架时间")
    private Date getoutTime;

    /**
     * 图片url
     */
    @ApiModelProperty(value = "图片url")
    private String surPicUrl;
}
