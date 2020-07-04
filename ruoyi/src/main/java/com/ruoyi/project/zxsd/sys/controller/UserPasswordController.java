package com.ruoyi.project.zxsd.sys.controller;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.zxsd.sys.domain.SystemMenuEntity;
import com.ruoyi.project.zxsd.sys.domain.UserPasswordEntity;
import com.ruoyi.project.zxsd.sys.service.IUserPasswordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author gkm
 * @version 1.0
 * @date 2020/6/3 11:08
 */
@RestController
@RequestMapping("/editpassword")
@Api(tags = {"【后台端】12.1 修改密码"}, description = "修改密码")
public class UserPasswordController extends BaseController{
    @Autowired
    private final IUserPasswordService userPasswordService;

    public UserPasswordController(IUserPasswordService userPasswordService) {
        this.userPasswordService = userPasswordService;
    }

    @Log(title = "12.1.1 修改用户", businessType = BusinessType.INSERT)
    @PutMapping("/editpassword")
    @ApiOperation(value = "12.1.1 修改用户", notes = "修改用户")
    public AjaxResult editPassword(@RequestBody UserPasswordEntity userPasswordEntity){
        String newPassword=userPasswordEntity.getNewPassword();
        String checkPassword=userPasswordEntity.getCheckPassword();
        List<UserPasswordEntity> passwordList=userPasswordService.selectPassword(userPasswordEntity);
        if(StringUtils.isEmpty(userPasswordEntity.getUserId())){
            return AjaxResult.error("用户id不能为空");
        }
        if(!SecurityUtils.matchesPassword(userPasswordEntity.getOldPassword(),passwordList.get(0).getOldPassword())){
            return new AjaxResult().error("原密码错误");
        }
        if(!newPassword.equals(checkPassword)){
            return new AjaxResult().error("两次密码请输入一致");
        }
        try{
            userPasswordService.editPassword(userPasswordEntity);
            return new AjaxResult().success("修改密码成功");
        }catch (Exception e){
            logger.error("修改密码失败",e);
            return new AjaxResult().error("修改密码失败");
        }
    }
}
