package com.ruoyi.project.zxsd.sys.domain;

import com.ruoyi.framework.web.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author gkm
 * @version 1.0
 * @date 2020/6/3 11:07
 */
@Getter
@Setter
@ApiModel(value = "用户密码", description = "用户密码")
public class UserPasswordEntity extends BaseEntity{
    /**
     * 序列化id
     */
    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @ApiModelProperty(value = "用户ID")
    private String userId;

    /**
     * 原密码
     */
    @ApiModelProperty(value = "原密码")
    private String oldPassword;

    /**
     * 新密码
     */
    @ApiModelProperty(value = "新密码")
    private String newPassword;

    /**
     * 确认新密码
     */
    @ApiModelProperty(value = "确认新密码")
    private String checkPassword;
}
