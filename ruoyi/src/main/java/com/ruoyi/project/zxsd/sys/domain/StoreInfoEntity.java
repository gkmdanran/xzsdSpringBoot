package com.ruoyi.project.zxsd.sys.domain;

import com.ruoyi.framework.web.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author gkm
 * @version 1.0
 * @date 2020/5/19 8:07
 */
@Getter
@Setter
@ApiModel(value = "门店对象", description = "门店对象")
public class StoreInfoEntity extends BaseEntity{
    /**
     * 序列化id
     */
    private static final long serialVersionUID = 1L;

    /**
     * 门店编码
     */
    @ApiModelProperty(value = "门店编码")
    private String storeNo;

    /**
     * 门店名称
     */
    @ApiModelProperty(value = "门店名称")
    private String storeName;

    /**
     * 门店地址
     */
    @ApiModelProperty(value = "门店地址")
    private String storeAddress;

    /**
     * 门店电话
     */
    @ApiModelProperty(value = "门店电话")
    private String storePhone;

    /**
     * 用户编码
     */
    @ApiModelProperty(value = "用户编码")
    private String userCode;

    /**
     * 店长姓名
     */
    @ApiModelProperty(value = "店长姓名")
    private String userRealName;

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
     * 省份
     */
    @ApiModelProperty(value = "省")
    private String province;

    /**
     * 省份编码
     */
    @ApiModelProperty(value = "省份编码")
    private String provinceNo;

    /**
     * 市
     */
    @ApiModelProperty(value = "市")
    private String county;

    /**
     * 市编码
     */
    @ApiModelProperty(value = "市编码")
    private String countyNo;

    /**
     * 星级
     */
    @ApiModelProperty(value = "星级")
    private int starLevel;

    /**
     * 营业号码
     */
    @ApiModelProperty(value = "营业号码")
    private String businessLicense;

    /**
     * 身份证
     */
    @ApiModelProperty(value = "身份证")
    private String identityCard;

    /**
     * 邀请码
     */
    @ApiModelProperty(value = "邀请码")
    private String inviteCode;
}
