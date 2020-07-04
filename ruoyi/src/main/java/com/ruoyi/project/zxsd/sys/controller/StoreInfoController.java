package com.ruoyi.project.zxsd.sys.controller;

import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.zxsd.sys.domain.CountryEntity;
import com.ruoyi.project.zxsd.sys.domain.ProvinceEntity;
import com.ruoyi.project.zxsd.sys.domain.StoreInfoEntity;
import com.ruoyi.project.zxsd.sys.domain.SystemMenuEntity;
import com.ruoyi.project.zxsd.sys.service.IStoreInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.catalina.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author gkm
 * @version 1.0
 * @date 2020/5/19 9:05
 */
@RestController
@RequestMapping("/storeinfo")
@Api(tags = {"【后台端】3.1 门店信息管理"}, description = "门店信息管理")
public class StoreInfoController extends BaseController{
    private final IStoreInfoService storeInfoService;
    @Autowired
    public StoreInfoController(IStoreInfoService storeInfoService){
        this.storeInfoService=storeInfoService;
    }

    /**
     * 查询省
     */
    @Log(title = "3.1.1 查询省", businessType = BusinessType.INSERT)
    @PostMapping("/selectprovince")
    @ApiOperation(value = "3.1.1 查询省", notes = "查询省")
    public AjaxResult selectProvince(){

        try{
            List<ProvinceEntity> provinceList= storeInfoService.selectProvince();
            return new AjaxResult().success("查询省成功",provinceList);
        }catch (Exception e){
            logger.error("查询省失败",e);
            return new AjaxResult().error("查询省失败");
        }
    }

    /**
     * 查询市区
     */
    @Log(title = "3.1.2 查询市区", businessType = BusinessType.INSERT)
    @PostMapping("/selectcounty")
    @ApiOperation(value = "3.1.2 查询市区", notes = "查询市区")
    public AjaxResult selectCounty(String provinceCode) {
        try{
            List<CountryEntity> countyList= storeInfoService.selectCounty(provinceCode);
            return new AjaxResult().success("查询市区成功",countyList);
        }catch (Exception e){
            logger.error("查询市区失败",e);
            return new AjaxResult().error("查询市区失败");
        }
    }

    /**
     * 查询门店信息
     */
    @Log(title = "3.1.3 查询门店信息", businessType = BusinessType.INSERT)
    @PostMapping("/selectstore")
    @ApiOperation(value = "3.1.3 查询门店信息", notes = "查询门店信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name="pageNum",dataType = "int",value="当前页数",defaultValue = "1"),
            @ApiImplicitParam(paramType="query",name="pageSize",dataType = "int",value="每页显示记录数",defaultValue = "10")
    })
    public TableDataInfo getListStoreInfo( StoreInfoEntity storeInfoEntity){
        startPage();
        try {

            List<StoreInfoEntity> storeList=storeInfoService.getListStoreInfo(storeInfoEntity);
            return getDataTable(storeList);
        }
        catch (Exception e){
            logger.error("门店查询失败", e);
            TableDataInfo rspData=new TableDataInfo();
            rspData.setTotal(0L);
            rspData.setMsg("门店息查询失败");
            return rspData;
        }

    }

    @Log(title = "3.1.4 删除门店信息", businessType = BusinessType.INSERT)
    @DeleteMapping("/delstore")
    @ApiOperation(value = "3.1.4 删除门店信息", notes = "删除门店信息")
    public AjaxResult delStoreInfo(@RequestBody StoreInfoEntity storeInfoEntity){
        String id=storeInfoEntity.getId();
        if(StringUtils.isEmpty(id)){
            return new AjaxResult().error("门店id不能为空");
        }
        try{
            storeInfoService.delStoreInfo(storeInfoEntity);
            return new AjaxResult().success("删除门店成功");
        }catch (Exception e){
            logger.error("删除门店失败",e);
            return new AjaxResult().error("删除门店失败");
        }
    }

    /**
     * 新增门店
     */
    @Log(title = "3.1.5 新增门店", businessType = BusinessType.INSERT)
    @PostMapping("/addstore")
    @ApiOperation(value = "3.1.5 新增门店", notes = "新增门店")
    public AjaxResult insertStoreInfo(@RequestBody StoreInfoEntity storeInfoEntity){
        String storeName=storeInfoEntity.getStoreName();
        if(StringUtils.isEmpty(storeName)){
            return new AjaxResult().error("门店名称不能为空");
        }
        String storeAddress=storeInfoEntity.getStoreAddress();
        if(StringUtils.isEmpty(storeAddress)){
            return new AjaxResult().error("门店地址不能为空");
        }
        try{
            storeInfoService.insertStoreInfo(storeInfoEntity);
            return new AjaxResult().success("门店新增成功");
        }catch (Exception e){
            logger.error("门店新增失败",e);
            return new AjaxResult().error("门店新增失败");
        }
    }

    /**
     * 修改门店
     */
    @Log(title = "3.1.6 修改门店", businessType = BusinessType.INSERT)
    @PostMapping("/editstore")
    @ApiOperation(value = "3.1.5 修改门店", notes = "修改门店")
    public AjaxResult editStoreInfo(@RequestBody StoreInfoEntity storeInfoEntity){
        try{
            storeInfoService.editStoreInfo(storeInfoEntity);
            return new AjaxResult().success("门店修改成功");
        }catch(Exception e){
            logger.error("门店修改失败",e);
            return new AjaxResult().error("门店修改失败");
        }
    }
}
