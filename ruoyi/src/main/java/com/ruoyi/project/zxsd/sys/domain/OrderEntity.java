package com.ruoyi.project.zxsd.sys.domain;

import com.ruoyi.framework.web.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 * @author gkm
 * @version 1.0
 * @date 2020/5/26 19:04
 */
@Getter
@Setter
@ApiModel(value = "订单", description = "订单")
public class OrderEntity extends BaseEntity {
    /**
     * 序列化id
     */
    private static final long serialVersionUID = 1L;


    /**
     * 订单编号
     */
    @ApiModelProperty(value = "订单编号")
    private String orderNo;

    /**
     *下单人Id
     */
    @ApiModelProperty(value = "下单人Id")
    private String userCode;

    /**
     *收货人姓名
     */
    @ApiModelProperty(value = "收货人姓名")
    private String shippingUser;

    /**
     *订单金额
     */
    @ApiModelProperty(value = "订单金额")
    private double orderMoney;

    /**
     *优惠金额
     */
    @ApiModelProperty(value = "优惠金额")
    private double districtMoney;

    /**
     *运费金额
     */
    @ApiModelProperty(value = "运费金额")
    private double shippingMoney;

    /**
     *支付金额
     */
    @ApiModelProperty(value = "支付金额")
    private double paymentMoney;

    /**
     *发货时间
     */
    @ApiModelProperty(value = "发货时间")
    private Date shippingTime;
    /**
     *支付时间
     */
    @ApiModelProperty(value = "支付时间")
    private Date payTime;
    /**
     *收货时间
     */
    @ApiModelProperty(value = "收货时间")
    private Date receiveTime;
    /**
     *订单状态
     */
    @ApiModelProperty(value = "订单状态")
    private int orderStatus;
    /**
     *收货校边店编号
     */
    @ApiModelProperty(value = "收货校边店编号")
    private String receiverNo;
    /**
     *id
     */
    @ApiModelProperty(value = "id")
    private String id;
    /**
     *序号
     */
    @ApiModelProperty(value = "序号")
    private int sortNo;
    /**
     *备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;
    /**
     *门店编号
     */
    @ApiModelProperty(value = "门店编号")
    private String storeNo;

    /**
     *门店名称
     */
    @ApiModelProperty(value = "门店名称")
    private String storeName;

    /**
     *司机
     */
    @ApiModelProperty(value = "司机")
    private String driver;

    /**
     *开始
     */
    @ApiModelProperty(value = "开始")
    private Date orderStart;

    /**
     *结束
     */
    @ApiModelProperty(value = "结束")
    private Date orderOver;

    /**
     *详情订单
     */
    @ApiModelProperty(value = "门店名称")
    private List<OrderDetailEntity> orderDetails;

    /**
     *操作标记
     */
    @ApiModelProperty(value = "操作标记")
    private String OdFlag;
    /**
     *下单人姓名
     */
    @ApiModelProperty(value = "下单人姓名")
    private String userRealName;


    /**
     *下单人手机
     */
    @ApiModelProperty(value = "下单人手机")
    private String phone;

    /**
     *角色
     */
    @ApiModelProperty(value = "角色")
    private int role;
}
