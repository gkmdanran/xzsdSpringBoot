package com.ruoyi.project.zxsd.sys.controller;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.security.LoginUser;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.zxsd.sys.domain.ClientEntity;
import com.ruoyi.project.zxsd.sys.domain.DriverEntity;
import com.ruoyi.project.zxsd.sys.domain.StoreInfoEntity;
import com.ruoyi.project.zxsd.sys.domain.SystemUserEntity;
import com.ruoyi.project.zxsd.sys.service.IClientService;
import com.ruoyi.project.zxsd.sys.service.IStoreInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author gkm
 * @version 1.0
 * @date 2020/5/22 18:44
 */
@RestController
@RequestMapping("/client")
@Api(tags = {"【后台端】6.1 客户管理"}, description = "客户管理")
public class ClientController extends BaseController{
    @Autowired
    private IClientService clientService;
    @Autowired
    private  IStoreInfoService storeInfoService;
    public ClientController(IClientService clientService,IStoreInfoService storeInfoService) {
        this.clientService = clientService;
        this.storeInfoService=storeInfoService;
    }

    @Log(title = "6.1.1 查询客户", businessType = BusinessType.INSERT)
    @PostMapping("/selectclient")
    @ApiOperation(value = "6.1.1 查询客户", notes = "查询客户")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name="pageNum",dataType = "int",value="当前页数",defaultValue = "1"),
            @ApiImplicitParam(paramType="query",name="pageSize",dataType = "int",value="每页显示记录数",defaultValue = "10")
    })
    public TableDataInfo selectClient(ClientEntity clientEntity){

        try{
            LoginUser loginUser = SecurityUtils.getLoginUser();
            SystemUserEntity systemUserEntity=loginUser.getSystemUserEntity();
            String inviteCode = "";
            if(systemUserEntity.getIsAdmin() == 0&&systemUserEntity.getRole()==1){
                StoreInfoEntity storeInfoEntity=storeInfoService.getStoreInfoByUserCode(systemUserEntity.getUserId());
                inviteCode=storeInfoEntity.getInviteCode();
            }else if(systemUserEntity.getIsAdmin()==1){
                inviteCode = "";
            }
            clientEntity.setInviteCode(inviteCode);
            startPage();
            List<ClientEntity> clientList=clientService.selectClient(clientEntity);
            return getDataTable(clientList);
        }catch (Exception e){
            logger.error("客户信息查询失败", e);
            TableDataInfo rspData=new TableDataInfo();
            rspData.setTotal(0L);
            rspData.setMsg("客户信息查询失败");
            return rspData;
        }
    }
}
