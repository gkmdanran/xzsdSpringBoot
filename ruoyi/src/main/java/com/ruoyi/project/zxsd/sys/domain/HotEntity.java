package com.ruoyi.project.zxsd.sys.domain;

import com.ruoyi.framework.web.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author gkm
 * @version 1.0
 * @date 2020/5/26 11:09
 */
@Getter
@Setter
@ApiModel(value = "热门商品", description = "热门商品")
public class HotEntity extends BaseEntity{
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
     * 售价
     */
    @ApiModelProperty(value = "售价")
    private double sellingPrice;

    /**
     * 定价
     */
    @ApiModelProperty(value = "定价")
    private double fixPrice;

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
    * 图片url
    */
   @ApiModelProperty(value = "图片url")
   private String imageUrl;
}
