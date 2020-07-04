package com.ruoyi.project.zxsd.sys.controller;

import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.zxsd.sys.domain.CateEntity;
import com.ruoyi.project.zxsd.sys.domain.SystemMenuEntity;
import com.ruoyi.project.zxsd.sys.mapper.CateMapper;
import com.ruoyi.project.zxsd.sys.service.ICateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;
import java.util.List;

/**
 * @author gkm
 * @version 1.0
 * @date 2020/5/23 14:27
 */
@RestController
@RequestMapping("/cate")
@Api(tags = {"【后台端】7.1 分类管理"}, description = "分类管理")
public class CateController extends BaseController{
    @Autowired
    private final ICateService cateService;

    public CateController(ICateService cateService) {
        this.cateService=cateService;
    }
    @Log(title = "7.1.1 新增分类", businessType = BusinessType.INSERT)
    @PostMapping("/addcate")
    @ApiOperation(value = "7.1.1 新增分类", notes = "新增分类")
    public AjaxResult insertCate(@RequestBody CateEntity cateEntity){
        String cateName=cateEntity.getCateName();
        if(StringUtils.isEmpty(cateName)){
            return new AjaxResult().error("分类名称不能为空");
        }
        try{
            cateService.insertCate(cateEntity);
            return AjaxResult.success("新增分类成功");
        }
        catch (Exception e){
            logger.error("新增分类失败", e);
            return AjaxResult.error("新增分类失败");
        }
    }

    @Log(title = "7.1.2 查询分类", businessType = BusinessType.INSERT)
    @GetMapping("/getcate")
    @ApiOperation(value = "7.1.2 查询分类", notes = "查询分类")
    public AjaxResult getCate(){
        AjaxResult ajaxResult = null;
        List<CateEntity> cateList = cateService.selectCate();
        return ajaxResult.success("查询成功",cateList);
    }

    @Log(title = "7.1.3 查询分类信息", businessType = BusinessType.INSERT)
    @GetMapping("/getcateinfo")
    @ApiOperation(value = "7.1.3 查询分类信息", notes = "查询分类信息")
    public AjaxResult getCateInfo(String cateCode){

        if(StringUtils.isEmpty(cateCode)){
            return new AjaxResult().error("分类编码不能为空");
        }
        try{
            List<CateEntity> catelist=cateService.getCateInfo(cateCode);
            return AjaxResult.success("查询分类信息成功",catelist);
        }
        catch (Exception e){
            logger.error("查询分类信息失败", e);
            return AjaxResult.error("查询分类信息失败");
        }
    }

    @Log(title = "7.1.4 删除分类", businessType = BusinessType.INSERT)
    @PostMapping("/delcate")
    @ApiOperation(value = "7.1.4 删除分类", notes = "删除分类")
    public AjaxResult delCate(String cateCode){
        if(StringUtils.isEmpty(cateCode)){
            return new AjaxResult().error("分类编码不能为空");
        }
       if(cateService.delCate(cateCode)==1){
           return new AjaxResult().success("删除分类成功");
       }else if(cateService.delCate(cateCode)==-1){
           return new AjaxResult().error("删除分类失败");
       }
       else {
           return new AjaxResult().error("此分类下有商品不能直接删除");
       }
    }

    @Log(title = "7.1.5 修改分类", businessType = BusinessType.INSERT)
    @PostMapping("/editcate")
    @ApiOperation(value = "7.1.5 修改分类", notes = "修改分类")
    public AjaxResult editCate(@RequestBody CateEntity cateEntity) {
        String cateName=cateEntity.getCateName();
        if(StringUtils.isEmpty(cateName)){
            return new AjaxResult().error("分类名称不能为空");
        }
        String cateCode=cateEntity.getCateCode();
        if(StringUtils.isEmpty(cateCode)){
            return new AjaxResult().error("分类编码不能为空");
        }
        try{
            cateService.editCate(cateEntity);
            return AjaxResult.success("修改分类信息成功");
        }
        catch (Exception e){
            logger.error("修改分类信息失败", e);
            return AjaxResult.error("修改分类信息失败");
        }
    }

    @Log(title = "7.1.6 查询一级分类", businessType = BusinessType.INSERT)
    @GetMapping("/getfirst")
    @ApiOperation(value = "7.1.6 查询一级分类", notes = " 查询一级分类")
    public AjaxResult getFirst(){
        try{
            List<CateEntity> catelist=cateService.selectFirstCate();
            return AjaxResult.success("查询一级分类成功",catelist);
        }
        catch (Exception e){
            logger.error("查询一级分类失败", e);
            return AjaxResult.error("查询一级分类失败");
        }
    }

    @Log(title = "7.1.7 查询二级分类", businessType = BusinessType.INSERT)
    @GetMapping("/getsecond")
    @ApiOperation(value = "7.1.7 查询二级分类", notes = "查询二级分类")
    public AjaxResult getSecond(String cateCodeParent){
        if(StringUtils.isEmpty(cateCodeParent)){
            return new AjaxResult().error("父级分类编码不能为空");
        }
        try{
            List<CateEntity> secondlist=cateService.selectSecondCate(cateCodeParent);
            return AjaxResult.success("查询二级分类成功",secondlist);
        }
        catch (Exception e){
            logger.error("查询二级分类失败", e);
            return AjaxResult.error("查询二级分类失败");
        }
    }
}
