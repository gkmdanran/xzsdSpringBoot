package com.ruoyi.project.common.util;

import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.storage.domain.User;
import com.ruoyi.project.system.domain.SysUser;
import com.ruoyi.project.system.service.ISysUserService;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 校验工具类
 *
 * @author wangdong
 * @date 2020.05.03
 */
@Slf4j
@Component
public class CheckUtil {

    /**
     * 定义ISysUserService
     */
    private static ISysUserService sysUserService;

    /**
     * 定义iSysUserServiceUtil
     */
    @Setter
    private static ISysUserService sysUserServiceUtil;

    /**
     * 构造方法注入ISysUserService
     *
     * @param iSysUserService 若依用户Service接口
     */
    @Autowired
    public CheckUtil(ISysUserService iSysUserService) {
        sysUserService = iSysUserService;
    }

    /**
     * 在依赖注入（注入ISysUserService）完成后，进行初始化
     */
    @PostConstruct
    public void initCheckUtil() {
        // 调用上面定义的@Setter，进行ISysUserService的初始化
        setSysUserServiceUtil(sysUserService);
    }

    /**
     * 校验用户唯一
     *
     * @param user        用户对象
     * @param operateType 操作类型（新增：1；修改：2）
     * @return 通用返回实体（非分页）
     */
    @SuppressWarnings("Duplicates")
    public static AjaxResult checkUserUnique(User user, int operateType) {
        // 定义sysUser
        SysUser sysUser = new SysUser();
        // 赋值用户id
        sysUser.setUserId(user.getUserId());
        // 赋值用户账号
        sysUser.setUserName(user.getUserName());
        // 赋值手机号
        sysUser.setPhonenumber(user.getPhonenumber());
        // 赋值邮箱
        sysUser.setEmail(user.getEmail());
        // 新增则校验登录帐号
        if (operateType == 1) {
            // 校验
            if (UserConstants.NOT_UNIQUE.equals(sysUserService.checkUserNameUnique(sysUser.getUserName()))) {
                // 返回登录账号已存在
                return AjaxResult.error("新增用户'" + sysUser.getUserName() + "'失败，登录账号已存在");
            }
        }
        // 定义操作名
        String operate = String.valueOf(operateType).replace("1", "新增").replace("2", "修改");
        // 校验
        if (UserConstants.NOT_UNIQUE.equals(sysUserService.checkPhoneUnique(sysUser))) {
            // 返回手机号码已存在
            return AjaxResult.error(operate + "用户'" + sysUser.getUserName() + "'失败，手机号码已存在");
        } else if (UserConstants.NOT_UNIQUE.equals(sysUserService.checkEmailUnique(sysUser))) {
            // 返回邮箱账号已存在
            return AjaxResult.error(operate + "用户'" + sysUser.getUserName() + "'失败，邮箱账号已存在");
        }
        // 不重复，则返回空
        return null;
    }

}
