package com.ruoyi.project.zxsd.sys.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author gkm
 * @version 1.0
 * @date 2020/5/22 16:44
 */

@Getter
@Setter
@ApiModel(value = "客户", description = "客户")
public class ClientEntity extends BannerEntity{
    /**
     * 序列化id
     */
    private static final long serialVersionUID = 1L;

    /**
     * 客户账号
     */
    @ApiModelProperty(value = "客户账号")
    private String userName;

    /**
     * 客户姓名
     */
    @ApiModelProperty(value = "客户姓名")
    private String userRealName;

    /**
     * 客户性别
     */
    @ApiModelProperty(value = "客户性别")
    private int sex;

    /**
     * 客户手机号
     */
    @ApiModelProperty(value = "客户手机号")
    private String phone;

    /**
     * 客户邮箱
     */
    @ApiModelProperty(value = "客户邮箱")
    private String email;

    /**
     * 客户身份证
     */
    @ApiModelProperty(value = "客户身份证")
    private String IdCard;

    /**
     * 客户邀请码
     */
    @ApiModelProperty(value = "邀请码")
    private String inviteCode;

}
