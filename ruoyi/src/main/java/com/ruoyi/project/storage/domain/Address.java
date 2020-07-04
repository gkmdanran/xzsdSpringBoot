package com.ruoyi.project.storage.domain;

import com.ruoyi.framework.web.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 客户地址对象 t_customer_address
 *
 * @author wangdong
 * @date 2020-05-10
 */
@Getter
@Setter
@ApiModel(value = "客户地址对象", description = "客户地址对象")
public class Address extends BaseEntity {

    /**
     * 序列化id
     */
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ApiModelProperty(value = "id")
    private Long id;

    /**
     * 手机端用户id
     */
    @ApiModelProperty(value = "手机端用户id")
    private Long userId;

    /**
     * 联系人
     */
    @ApiModelProperty(value = "联系人")
    private String contacts;

    /**
     * 联系人电话
     */
    @ApiModelProperty(value = "联系人电话")
    private String contactsPhone;

    /**
     * 省编号
     */
    @ApiModelProperty(value = "省编号")
    private String province;

    /**
     * 省名称
     */
    @ApiModelProperty(value = "省名称")
    private String provinceName;

    /**
     * 市编号
     */
    @ApiModelProperty(value = "市编号")
    private String city;

    /**
     * 市名称
     */
    @ApiModelProperty(value = "市名称")
    private String cityName;

    /**
     * 区编号
     */
    @ApiModelProperty(value = "区编号")
    private String area;

    /**
     * 区名称
     */
    @ApiModelProperty(value = "区名称")
    private String areaName;

    /**
     * 详细地址
     */
    @ApiModelProperty(value = "详细地址")
    private String address;

    /**
     * 是否是默认地址（0：是；1：否）
     */
    @ApiModelProperty(value = "是否是默认地址（0：是；1：否）")
    private Integer isDefault;

    /**
     * 序号
     */
    @ApiModelProperty(value = "序号")
    private Long sortNo;

}
