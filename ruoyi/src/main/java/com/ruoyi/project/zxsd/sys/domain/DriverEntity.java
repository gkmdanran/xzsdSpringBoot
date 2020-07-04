package com.ruoyi.project.zxsd.sys.domain;

import com.ruoyi.framework.web.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author gkm
 * @version 1.0
 * @date 2020/5/21 9:11
 */
@Getter
@Setter
@ApiModel(value = "司机", description = "司机")
public class DriverEntity extends BaseEntity{
    /**
     * 序列化id
     */
    private static final long serialVersionUID = 1L;

    /**
     * 司机编号
     */
    @ApiModelProperty(value = "司机编号")
    private String driverNo;

    /**
     * 司机姓名
     */
    @ApiModelProperty(value = "司机姓名")
    private String driverName;

    /**
     * 司机电话
     */
    @ApiModelProperty(value = "司机电话")
    private String driverPhone;

    /**
     * 司机身份证
     */
    @ApiModelProperty(value = "司机身份证")
    private String driverIdCard;

    /**
     * 用户编码
     */
    @ApiModelProperty(value = "用户编码")
    private String userCode;

    /**
     * 用户账号
     */
    @ApiModelProperty(value = "用户账号")
    private String userName;

    /**
     * id
     */
    @ApiModelProperty(value = "id")
    private String id;

    /**
     * 序号
     */
    @ApiModelProperty(value = "序号")
    private int sortNo;

    /**
     * 省份编码
     */
    @ApiModelProperty(value = "省份编码")
    private String provinceNo;
    /**
     * 省份
     */
    @ApiModelProperty(value = "省份")
    private String provinceName;

    /**
     * 市编码
     */
    @ApiModelProperty(value = "市编码")
    private String countyNo;
    /**
     * 市
     */
    @ApiModelProperty(value = "市")
    private String countyName;
    /**
     * 门店编码
     */
    @ApiModelProperty(value = "门店编码")
    private String storeNo;

}
