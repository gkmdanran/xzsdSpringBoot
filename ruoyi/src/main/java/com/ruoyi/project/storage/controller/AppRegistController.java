package com.ruoyi.project.storage.controller;

import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.common.enums.TerminalEnum;
import com.ruoyi.project.common.util.CheckUtil;
import com.ruoyi.project.storage.domain.User;
import com.ruoyi.project.storage.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 手机端注册Controller
 *
 * @author wangdong
 * @date 2020.04.30
 */
@ApiIgnore
@RestController
@RequestMapping("/app/regist")
@Api(tags = {"【手机端】5.2.1 注册"}, description = "注册")
public class AppRegistController extends BaseController {

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
    public AppRegistController(IUserService userService) {
        this.userService = userService;
    }

    /**
     * 注册
     */
    @Log(title = "注册", businessType = BusinessType.INSERT)
    @PostMapping
    @ApiOperation(value = "注册", notes = "注册")
    public AjaxResult regist(@RequestBody User user) {
        // 校验用户唯一
        AjaxResult ajaxResult = CheckUtil.checkUserUnique(user, 1);
        // 返回通用返回实体（非分页）
        return ajaxResult == null ? toAjax(userService.insertUser(TerminalEnum.REGIST, user)) : ajaxResult;
    }

}
