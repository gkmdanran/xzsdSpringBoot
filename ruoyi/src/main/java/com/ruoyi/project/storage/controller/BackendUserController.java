package com.ruoyi.project.storage.controller;

import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.common.enums.TerminalEnum;
import com.ruoyi.project.common.util.CheckUtil;
import com.ruoyi.project.storage.domain.User;
import com.ruoyi.project.storage.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

/**
 * 后台端用户Controller
 *
 * @author wangdong
 * @date 2020.04.30
 */
@ApiIgnore
@RestController
@RequestMapping("/backend/user")
@Api(tags = {"【后台端】5.3.2 用户管理"}, description = "用户列表（分页）、用户新增、用户编辑、用户删除、用户重置密码、用户启用/停用")
public class BackendUserController extends BaseController {

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
    public BackendUserController(IUserService userService) {
        this.userService = userService;
    }

    /**
     * 查询用户列表
     *
     * @param user 用户
     * @return 分页结果
     */
    @Log(title = "5.3.2.1 用户列表（分页）", businessType = BusinessType.OTHER)
    @GetMapping("/list")
    @ApiOperation(value = "5.3.2.1 用户列表（分页）", notes = "查询用户列表")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "pageNum", dataType = "int", value = "当前页数", defaultValue = "1"),
            @ApiImplicitParam(paramType = "query", name = "pageSize", dataType = "int", value = "每页显示记录数", defaultValue = "10")
    })
    public TableDataInfo list(User user) {
        // 获取分页信息
        startPage();
        // 查询后台端用户列表
        List<User> list = userService.selectUserList(TerminalEnum.BACKEND, user);
        // 返回响应请求分页数据
        return getDataTable(list);
    }

    /**
     * 新增用户
     */
    @Log(title = "5.3.2.2 用户新增", businessType = BusinessType.INSERT)
    @PostMapping("/create")
    @ApiOperation(value = "5.3.2.2 用户新增", notes = "新增用户")
    public AjaxResult add(@RequestBody User user) {
        // 校验用户唯一
        AjaxResult ajaxResult = CheckUtil.checkUserUnique(user, 1);
        // 返回通用返回实体（非分页）
        return ajaxResult == null ? toAjax(userService.insertUser(TerminalEnum.BACKEND, user)) : ajaxResult;
    }

    /**
     * 修改用户
     */
    @Log(title = "5.3.2.3 用户编辑", businessType = BusinessType.UPDATE)
    @PutMapping("/update")
    @ApiOperation(value = "5.3.2.3 用户编辑", notes = "修改用户")
    public AjaxResult edit(@RequestBody User user) {
        // 校验用户唯一
        AjaxResult ajaxResult = CheckUtil.checkUserUnique(user, 2);
        // 返回通用返回实体（非分页）
        return ajaxResult == null ? toAjax(userService.updateUser(TerminalEnum.BACKEND, user)) : ajaxResult;
    }

    /**
     * 删除用户
     */
    @Log(title = "5.3.2.4 用户删除", businessType = BusinessType.DELETE)
    @DeleteMapping("/delete/{userIds}")
    @ApiOperation(value = "5.3.2.4 用户删除", notes = "删除用户")
    public AjaxResult remove(@PathVariable Long[] userIds) {
        // 返回响应结果
        return toAjax(userService.deleteUserByUserIds(TerminalEnum.BACKEND, userIds));
    }

    /**
     * 用户重置密码
     */
    @Log(title = "5.3.2.5 用户重置密码", businessType = BusinessType.UPDATE)
    @PutMapping("/reset/{userIds}")
    @ApiOperation(value = "5.3.2.5 用户重置密码", notes = "用户重置密码")
    public AjaxResult reset(@PathVariable Long[] userIds) {
        // 返回响应结果
        return toAjax(userService.resetUserByUserIds(TerminalEnum.BACKEND, userIds));
    }

    /**
     * 启用/停用用户
     */
    @Log(title = "5.3.2.6 用户启用/停用", businessType = BusinessType.UPDATE)
    @PutMapping("/{operate}/{userIds}")
    @ApiOperation(value = "5.3.2.6 用户启用/停用", notes = "启用/停用用户")
    public AjaxResult remove(@PathVariable String operate, @PathVariable Long[] userIds) {
        // 返回响应结果
        return toAjax(userService.operateUserByIds(TerminalEnum.BACKEND, operate, userIds));
    }

}
