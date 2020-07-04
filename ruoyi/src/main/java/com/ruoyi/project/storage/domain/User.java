package com.ruoyi.project.storage.domain;

import com.ruoyi.framework.web.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 用户对象 sys_user
 *
 * @author wangdong
 * @date 2020-04-30
 */
@Getter
@Setter
@ApiModel(value = "用户对象", description = "用户对象")
public class User extends BaseEntity {

    /**
     * 序列化id
     */
    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @ApiModelProperty(value = "用户ID")
    private Long userId;

    /**
     * 部门ID
     */
    @ApiModelProperty(value = "部门ID")
    private Long deptId;

    /**
     * 用户账号
     */
    @ApiModelProperty(value = "用户账号")
    private String userName;

    /**
     * 用户姓名
     */
    @ApiModelProperty(value = "用户姓名")
    private String nickName;

    /**
     * 用户类型（00系统用户；01后台端用户；02手机端用户）
     */
    @ApiModelProperty(value = "用户类型（00系统用户；01后台端用户；02手机端用户）")
    private String userType;

    /**
     * 用户邮箱
     */
    @ApiModelProperty(value = "用户邮箱")
    private String email;

    /**
     * 手机号码
     */
    @ApiModelProperty(value = "手机号码")
    private String phonenumber;

    /**
     * 用户性别（0男 1女 2未知）
     */
    @ApiModelProperty(value = "用户性别（0男 1女 2未知）")
    private String sex;

    /**
     * 头像地址
     */
    @ApiModelProperty(value = "头像地址")
    private String avatar;

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码")
    private String password;

    /**
     * 帐号状态（0正常 1停用）
     */
    @ApiModelProperty(value = "帐号状态（0正常 1停用）")
    private String status;

    /**
     * 最后登陆IP
     */
    @ApiModelProperty(value = "最后登陆IP")
    private String loginIp;

    /**
     * 最后登陆时间
     */
    @ApiModelProperty(value = "最后登陆时间")
    private Date loginDate;

    /**
     * 当前积分
     */
    @ApiModelProperty(value = "当前积分")
    private Long currentPoints;

}
