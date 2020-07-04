package com.ruoyi.project.zxsd.sys.domain;

import com.ruoyi.framework.web.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author gkm
 * @version 1.0
 * @date 2020/5/28 9:58
 */
@Getter
@Setter
@ApiModel(value = "购物车", description = "购物车")
public class CarEntity extends BaseEntity{
    /**
     * 序列化id
     */
    private static final long serialVersionUID = 1L;

    /**
     *下单人Id
     */
    @ApiModelProperty(value = "下单人Id")
    private String userCode;

    /**
     * 商品sku编码
     */
    @ApiModelProperty(value = "商品sku编码")
    private String skuNo;

    /**
     * 商品数量
     */
    @ApiModelProperty(value = "商品数量")
    private int cnt;
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
     *勾选状态
     */
    @ApiModelProperty(value = "勾选状态")
    private int isChecked;

    /**
     *商家编码
     */
    @ApiModelProperty(value = "商家编码")
    private String storeNo;
}
