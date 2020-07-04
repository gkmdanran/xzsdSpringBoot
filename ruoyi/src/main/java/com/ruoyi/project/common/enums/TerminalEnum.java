package com.ruoyi.project.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 终端
 *
 * @author wangdong
 * @date 2020-05-02
 */
@AllArgsConstructor
@Getter
public enum TerminalEnum {

    /**
     * 后台端
     */
    BACKEND("01", "backend", 3L, "Abc,123456", 113L, 0L),

    /**
     * 手机端
     */
    APP("02", "app", 4L, "12345678", 114L, 0L),

    /**
     * 系统端
     */
    SYSTEM("00", "system", 1L, "admin123", 103L, 0L),

    /**
     * 手机注册端
     */
    REGIST("02", "app", 4L, "", 114L, 10000L);

    /**
     * 类型
     */
    private String type;

    /**
     * 代码
     */
    private String code;

    /**
     * 角色
     */
    private Long role;

    /**
     * 初始密码
     */
    private String password;

    /**
     * 部门
     */
    private Long dept;

    /**
     * 积分
     */
    private Long point;

}
