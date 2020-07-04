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
 * @date 2020/5/26 19:37
 */
@Getter
@Setter
@ApiModel(value = "订单详情", description = "订单详情")
public class OrderDetailEntity extends BaseEntity {
    /**
     * 序列化id
     */
    private static final long serialVersionUID = 1L;
    /**
     * 订单表id
     */
    @ApiModelProperty(value = "订单表id")
    private String orderDetailCode;

    /**
     * 商品sku编码
     */
    @ApiModelProperty(value = "商品sku编码")
    private String skuNo;
    /**
     * 商品名称
     */
    @ApiModelProperty(value = "商品名称")
    private String goodsName;

    /**
     * 商品购买数量
     */
    @ApiModelProperty(value = "商品购买数量")
    private int goodsCnt;

    /**
     * 购买商品单价
     */
    @ApiModelProperty(value = "购买商品单价")
    private double goodsPrice;

    /**
     * 优惠分摊金额
     */
    @ApiModelProperty(value = "优惠分摊金额")
    private double feeMoney;
    /**
     *id
     */
    @ApiModelProperty(value = "id")
    private String id;

    /**
     * 序号
     */
    @ApiModelProperty(value = "序号")
    private int sortNo;
    /**
     * 订单编码
     */
    @ApiModelProperty(value = "订单编码")
    private String orderNo;
    /**
     * 评价状态
     */
    @ApiModelProperty(value = "评价状态")
    private int isAppraise;
    /**
     * 取货时间
     */
    @ApiModelProperty(value = "取货时间")
    private Date receiveTime;
    /**
     * 确认取货标记
     */
    @ApiModelProperty(value = "确认取货标记")
    private int isReceived;
    /**
     * 到货时间
     */
    @ApiModelProperty(value = "到货时间")
    private Date shippingTime;
    /**
     * 商品sku编码
     */
    @ApiModelProperty(value = "到货标记")
    private int isShipped;
    /**
     *操作标记
     */
    @ApiModelProperty(value = "操作标记")
    private String OdFlag;


}
