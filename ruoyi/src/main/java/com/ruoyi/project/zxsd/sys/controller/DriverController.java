package com.ruoyi.project.zxsd.sys.controller;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.security.LoginUser;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.zxsd.sys.domain.DriverEntity;
import com.ruoyi.project.zxsd.sys.domain.StoreInfoEntity;
import com.ruoyi.project.zxsd.sys.domain.SystemMenuEntity;
import com.ruoyi.project.zxsd.sys.service.IDriverService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author gkm
 * @version 1.0
 * @date 2020/5/21 9:24
 */
@RestController
@RequestMapping("/driver")
@Api(tags = {"【后台端】4.1 司机信息管理"}, description = "司机信息管理")
public class DriverController extends BaseController{
    private final IDriverService driverService;
    @Autowired
    public DriverController(IDriverService driverService) {
        this.driverService = driverService;
    }

    @Log(title = "4.1.1 新增司机", businessType = BusinessType.INSERT)
    @PostMapping("/adddriver")
    @ApiOperation(value = "4.1.1 新增司机", notes = "新增司机")
    public AjaxResult insertDriver(@RequestBody DriverEntity driverEntity){
        String userCode=driverEntity.getUserCode();
        if(StringUtils.isEmpty(userCode)){
            return AjaxResult.error("司机编号不能为空");
        }
        try{
            driverService.insertDriver(driverEntity);
            return AjaxResult.success("添加司机成功");
        }
        catch (Exception e){
            logger.error("添加司机失败", e);
            return AjaxResult.error("添加司机失败");
        }
    }

    @Log(title = "4.1.2 删除司机", businessType = BusinessType.INSERT)
    @PostMapping("/deldriver")
    @ApiOperation(value = "4.1.2 删除司机", notes = "删除司机")
    public AjaxResult delDriver(@RequestBody DriverEntity driverEntity){
        String userCode=driverEntity.getUserCode();
        if(StringUtils.isEmpty(userCode)){
            return AjaxResult.error("司机编号不能为空");
        }
        try{
            driverService.delDriver(userCode);
            return AjaxResult.success("删除司机成功");
        }
        catch (Exception e){
            logger.error("删除司机失败", e);
            return AjaxResult.error("删除司机失败");
        }
    }

    @Log(title = "4.1.3 查询司机", businessType = BusinessType.INSERT)
    @PostMapping("/selectdriver")
    @ApiOperation(value = "4.1.3 查询司机", notes = "查询司机")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name="pageNum",dataType = "int",value="当前页数",defaultValue = "1"),
            @ApiImplicitParam(paramType="query",name="pageSize",dataType = "int",value="每页显示记录数",defaultValue = "10")
    })
    public TableDataInfo selectDriver(DriverEntity driverEntity){
        startPage();
        try{
            List<DriverEntity> driverList=driverService.selectDriver(driverEntity);
            return getDataTable(driverList);
        }catch (Exception e){
            logger.error("司机信息查询失败", e);
            TableDataInfo rspData=new TableDataInfo();
            rspData.setTotal(0L);
            rspData.setMsg("司机信息查询失败");
            return rspData;
        }

    }

    @Log(title = "4.1.4 修改司机", businessType = BusinessType.INSERT)
    @PostMapping("/editdriver")
    @ApiOperation(value = "4.1.4 修改司机", notes = "修改司机")
    public AjaxResult editDriver(@RequestBody DriverEntity driverEntity){
        String userCode=driverEntity.getUserCode();
        if(StringUtils.isEmpty(userCode)){
            return AjaxResult.error("司机编号不能为空");
        }
        try{
            driverService.editDriver(driverEntity);
            return AjaxResult.success("修改司机成功");
        }
        catch (Exception e){
            logger.error("修改司机失败", e);
            return AjaxResult.error("修改司机失败");
        }
    }
}
