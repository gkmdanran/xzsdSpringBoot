package com.ruoyi.project.zxsd.sys.controller;

import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.zxsd.sys.domain.DriverEntity;
import com.ruoyi.project.zxsd.sys.domain.HotEntity;
import com.ruoyi.project.zxsd.sys.service.IHotService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author gkm
 * @version 1.0
 * @date 2020/5/26 11:07
 */
@RestController
@RequestMapping("/hot")
@Api(tags = {"【后台端】9.1 热门商品管理"}, description = "热门商品管理")
public class HotController extends BaseController{
    @Autowired
    private final IHotService hotService;

    public HotController(IHotService hotService) {
        this.hotService = hotService;
    }

    @Log(title = "9.1.1 插入热门商品", businessType = BusinessType.INSERT)
    @PostMapping("/inserthot")
    @ApiOperation(value = "9.1.1 插入热门商品", notes = "插入热门商品")
    public AjaxResult insertHot(@RequestBody HotEntity hotEntity){
        try{
            hotService.insertHot(hotEntity);
            return new AjaxResult().success("热门商品新增成功");
        }catch (Exception e){
            logger.error("热门商品新增失败",e);
            return new AjaxResult().error("热门商品新增失败");
        }
    }

    @Log(title = "9.1.2 查询商品", businessType = BusinessType.INSERT)
    @PostMapping("/selecthot")
    @ApiOperation(value = "9.1.2 查询商品", notes = "查询商品")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name="pageNum",dataType = "int",value="当前页数",defaultValue = "1"),
            @ApiImplicitParam(paramType="query",name="pageSize",dataType = "int",value="每页显示记录数",defaultValue = "10")
    })
    public TableDataInfo selectHot(HotEntity hotEntity){
        startPage();
        try{
            List<HotEntity> hotList=hotService.selectHot(hotEntity);
            return getDataTable(hotList);
        }catch (Exception e){
            logger.error("热门信息查询失败", e);
            TableDataInfo rspData=new TableDataInfo();
            rspData.setTotal(0L);
            rspData.setMsg("热门信息查询失败");
            return rspData;
        }
    }

    @Log(title = "9.1.3 删除热门商品", businessType = BusinessType.INSERT)
    @PostMapping("/delhot")
    @ApiOperation(value = "9.1.3 删除热门商品", notes = "删除热门商品")
    public AjaxResult delHot(String id){
        if(StringUtils.isEmpty(id)){
            return new AjaxResult().error("商品id不能为空");
        }
        try{
            hotService.delHot(id);
            return AjaxResult.success("删除热门商品成功");
        }
        catch (Exception e){
            logger.error("删除热门商品失败", e);
            return AjaxResult.error("删除热门商品失败");
        }
    }
    @Log(title = "9.1.4 修改热门商品", businessType = BusinessType.INSERT)
    @PostMapping("/edithot")
    @ApiOperation(value = "9.1.4 修改热门商品", notes = "修改热门商品")
    public AjaxResult delHot(@RequestBody HotEntity hotEntity){
        String id=hotEntity.getId();
        if(StringUtils.isEmpty(id)){
            return new AjaxResult().error("商品id不能为空");
        }
        try{
            hotService.editHot(hotEntity);
            return AjaxResult.success("修改热门商品成功");
        }
        catch (Exception e){
            logger.error("修改热门商品失败", e);
            return AjaxResult.error("修改热门商品失败");
        }
    }
}
