package com.ruoyi.project.zxsd.sys.controller;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.security.LoginUser;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.system.domain.SysUser;
import com.ruoyi.project.zxsd.sys.domain.SystemMenuEntity;
import com.ruoyi.project.zxsd.sys.domain.SystemUserEntity;
import com.ruoyi.project.zxsd.sys.service.ISystemMenuService;
import com.ruoyi.project.zxsd.sys.service.ISystemUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 后台端用户Controller
 *
 * @author wangdong
 * @date 2020.04.30
 */
@RestController
@RequestMapping("/systemuser")
@Api(tags = {"【后台端】3.1 用户管理"}, description = "用户管理")
public class SystemUserController extends BaseController {
    private final ISystemUserService systemUserService;
    @Autowired
    public SystemUserController(ISystemUserService systemUserService) {

        this.systemUserService = systemUserService;
    }

    /**
     * 新增用户
     */
    @Log(title = "3.1.1 新增用户", businessType = BusinessType.INSERT)
    @PostMapping("/adduser")
    @ApiOperation(value = "3.1.1 新增用户", notes = "新增用户")


    public AjaxResult adduser(@RequestBody SystemUserEntity systemUserEntity) {
        String userName = systemUserEntity.getUserName();
        if(StringUtils.isEmpty(userName)){
            return new AjaxResult().error("账号不能为空");
        }
        String userRealName = systemUserEntity.getUserRealname();
        if(StringUtils.isEmpty(userRealName)){
            return new AjaxResult().error("姓名不能为空");
        }
        String password = systemUserEntity.getPassword();
        if(StringUtils.isEmpty(password)){
            return new AjaxResult().error("密码不能为空");
        }
        String idCard = systemUserEntity.getIdCard();
        if(StringUtils.isEmpty(idCard)){
            return new AjaxResult().error("身份证号不能为空");
        }
        int userCount = 0;
        try{
            SystemUserEntity userInfo = systemUserService.getSystemUserByUserName(userName);
            if(userInfo != null){
                return new AjaxResult().error("用户已存在");
            }
        }catch (Exception e){
            logger.error("查询用户信息异常，新增用户失败",e);
            return new AjaxResult().error("查询用户信息异常，新增用户失败");
        }
        try{
            systemUserService.insertSystemUser(systemUserEntity);
        }catch (Exception e){
            logger.error("新增用户失败",e);
            return new AjaxResult().error("新增用户失败");
        }
        return new AjaxResult().success("新增用户成功");
    }
    /**
     * 修改用户
     */
    @Log(title = "3.1.2 修改用户", businessType = BusinessType.INSERT)
    @PutMapping("/edituser")
    @ApiOperation(value = "3.1.2 修改用户", notes = "修改用户")
    public AjaxResult edituser(@RequestBody SystemUserEntity systemUserEntity) {
        String userId = systemUserEntity.getUserId();
        if(StringUtils.isEmpty(userId)){
            return new AjaxResult().error("用户ID不能为空");
        }
        String userRealName = systemUserEntity.getUserRealname();
        if(StringUtils.isEmpty(userRealName)){
            return new AjaxResult().error("姓名不能为空");
        }
        String idCard = systemUserEntity.getIdCard();
        if(StringUtils.isEmpty(idCard)){
            return new AjaxResult().error("身份证号不能为空");
        }
        try{
            systemUserService.editSystemUser(systemUserEntity);
        }catch (Exception e){
            logger.error("修改用户失败",e);
            return new AjaxResult().error("修改用户失败");
        }
        return new AjaxResult().success("修改用户成功");
    }

    /**
     * 删除用户
     */
    @Log(title = "3.1.3 删除用户", businessType = BusinessType.INSERT)
    @DeleteMapping("/deluser")
    @ApiOperation(value = "3.1.3 删除用户", notes = "删除用户")
    public AjaxResult deluser(@RequestBody SystemUserEntity systemUserEntity) {
        String userId = systemUserEntity.getUserId();
        if(StringUtils.isEmpty(userId)){
            return new AjaxResult().error("用户ID不能为空");
        }
        try{
            systemUserService.deleteSystemUser(systemUserEntity);
        }catch (Exception e){
            logger.error("删除用户失败",e);
            return new AjaxResult().error("删除用户失败");
        }
        return new AjaxResult().success("删除用户成功");
    }

    //查询和修改密码
    /**
     * 查询用户
     */
    @Log(title = "3.1.4 查询用户", businessType = BusinessType.INSERT)
    @GetMapping("/selectuser")
    @ApiOperation(value = "3.1.4 查询用户", notes = "查询用户")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name="pageNum",dataType = "int",value="当前页数",defaultValue = "1"),
            @ApiImplicitParam(paramType="query",name="pageSize",dataType = "int",value="每页显示记录数",defaultValue = "10")
    })
    public TableDataInfo selectUser(SystemUserEntity systemUserEntity){
        startPage();
        List<SystemUserEntity> userList=systemUserService.selectUser(systemUserEntity);
        return getDataTable(userList);
    }

    /**
     * 修改邀请码
     */
    @Log(title = "3.1.5 修改邀请码", businessType = BusinessType.INSERT)
    @PostMapping("/editinvitecode")
    @ApiOperation(value = "3.1.5 修改邀请码", notes = "修改邀请码")
    public AjaxResult editInviteCode(@RequestBody SystemUserEntity systemUserEntity) {
        String userId = systemUserEntity.getUserId();
        if(StringUtils.isEmpty(userId)){
            return new AjaxResult().error("用户ID不能为空");
        }
        try{
            systemUserService.editInviteCode(systemUserEntity);
        }catch (Exception e){
            logger.error("修改邀请码失败",e);
            return new AjaxResult().error("修改邀请码失败");
        }
        return new AjaxResult().success("修改邀请码成功");
    }

}
