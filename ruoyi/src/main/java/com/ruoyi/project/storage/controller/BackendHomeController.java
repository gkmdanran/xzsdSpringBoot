package com.ruoyi.project.storage.controller;

import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.common.enums.TerminalEnum;
import com.ruoyi.project.storage.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 后台端首页Controller
 *
 * @author wangdong
 * @date 2020.05.03
 */
@ApiIgnore
@RestController
@RequestMapping("/backend/home")
@Api(tags = {"【后台端】5.3.1 首页"}, description = "修改密码")
@Slf4j
public class BackendHomeController extends BaseController {

    /**
     * 用户Service接口
     */
    private final IUserService userService;

    /**
     * 构造方法注入
     *
     * @param userService 用户Service接口
     */
    @Autowired
    public BackendHomeController(IUserService userService) {
        this.userService = userService;
    }

    /**
     * 修改密码
     *
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @return 通用返回实体（非分页）
     */
    @Log(title = "5.3.1.1 修改密码", businessType = BusinessType.UPDATE)
    @PutMapping("/updatePassword/{oldPassword}/{newPassword}")
    @ApiOperation(value = "5.3.1.1 修改密码", notes = "修改密码")
    public AjaxResult updatePassword(@PathVariable String oldPassword, @PathVariable String newPassword) {
        // 返回响应结果
        return toAjax(userService.updateUserPassword(TerminalEnum.BACKEND, oldPassword, newPassword));
    }

}
