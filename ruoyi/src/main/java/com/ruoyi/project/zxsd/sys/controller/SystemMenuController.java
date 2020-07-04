package com.ruoyi.project.zxsd.sys.controller;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.security.LoginUser;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.system.domain.SysUser;
import com.ruoyi.project.zxsd.sys.domain.SystemMenuEntity;
import com.ruoyi.project.zxsd.sys.domain.SystemUserEntity;
import com.ruoyi.project.zxsd.sys.service.ISystemMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Security;
import java.util.List;

/**
 * 后台端用户Controller
 *
 * @author wangdong
 * @date 2020.04.30
 */
@RestController
@RequestMapping("/sysmenu")
@Api(tags = {"【后台端】2.1 菜单管理"}, description = "菜单管理")
public class SystemMenuController extends BaseController {
    private final ISystemMenuService sysMenuService;
    @Autowired
    public SystemMenuController(ISystemMenuService sysMenuService) {
        this.sysMenuService = sysMenuService;
    }

    /**
     * 新增菜单
     */
    @Log(title = "2.1.1 新增菜单", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ApiOperation(value = "2.1.1 新增菜单", notes = "新增菜单")
    public AjaxResult add(@RequestBody SystemMenuEntity systemMenuEntity) {
        AjaxResult ajaxResult = null;
        try{
            //新增菜单
            sysMenuService.insertSysMenu(systemMenuEntity);
            return new AjaxResult().success("菜单新增成功");
        }catch (Exception e){
            logger.error("菜单新增失败",e);
            return new AjaxResult().error("菜单新增失败");
        }

    }

    /**
     * 查询菜单
     */
    @Log(title = "2.1.2 查询菜单", businessType = BusinessType.INSERT)
    @GetMapping("/getMenu")
    @ApiOperation(value = "2.1.2 查询菜单", notes = "查询菜单")
    public AjaxResult getMenu() {
        AjaxResult ajaxResult = null;
        //获取当前登录人的基本信息
        LoginUser loginUser = SecurityUtils.getLoginUser();
        SystemUserEntity user = loginUser.getSystemUserEntity();
        int userRole = user.getRole();
        List<SystemMenuEntity> menuList = sysMenuService.getMenuByUserRole(userRole);

        return ajaxResult.success("查询成功",menuList);

    }
    /**
     * 修改菜单
     */
    @Log(title = "2.1.3 修改菜单", businessType = BusinessType.INSERT)
    @PutMapping("/editmenu")
    @ApiOperation(value = "2.1.3 修改菜单", notes = "修改菜单")
    public AjaxResult editMenu(@RequestBody SystemMenuEntity systemMenuEntity) {
//        AjaxResult ajaxResult = null;
        String menuCode = systemMenuEntity.getMenuCode();
        if(StringUtils.isEmpty(menuCode)){
            return new AjaxResult().error("菜单编码不能为空");
        }
        String menuName = systemMenuEntity.getMenuName();
        if(StringUtils.isEmpty(menuName)){
            return new AjaxResult().error("菜单名称不能为空");
        }
        String menuUrl = systemMenuEntity.getMenuUrl();
        if(StringUtils.isEmpty(menuUrl)){
            return new AjaxResult().error("菜单路径不能为空");
        }
        try{
            sysMenuService.editSysMenu(systemMenuEntity);
        }catch (Exception e){
            logger.error("修改菜单失败",e);
            return new AjaxResult().error("修改菜单失败");
        }
        return new AjaxResult().success("修改菜单成功");
    }


    /**
     * 删除菜单
     */
    @Log(title = "2.1.4 删除菜单", businessType = BusinessType.INSERT)
    @DeleteMapping("/delmenu")
    @ApiOperation(value = "2.1.4 删除菜单", notes = "删除菜单")
    public AjaxResult delmenu(@RequestBody SystemMenuEntity systemMenuEntity) {
        String menuCode = systemMenuEntity.getMenuCode();
        if(StringUtils.isEmpty(menuCode)){
            return new AjaxResult().error("菜单编码不能为空");
        }
       if(sysMenuService.delSysMenu(systemMenuEntity)==1){
           return new AjaxResult().success("删除菜单成功");
       }
        else{
           return new AjaxResult().error("删除菜单失败");
       }
    }
}

