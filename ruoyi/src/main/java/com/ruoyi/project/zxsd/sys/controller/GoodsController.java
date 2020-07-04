package com.ruoyi.project.zxsd.sys.controller;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.file.FileUtils;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.config.RuoYiConfig;
import com.ruoyi.framework.security.LoginUser;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.zxsd.sys.domain.DriverEntity;
import com.ruoyi.project.zxsd.sys.domain.GoodsEntity;
import com.ruoyi.project.zxsd.sys.domain.StoreInfoEntity;
import com.ruoyi.project.zxsd.sys.domain.SystemUserEntity;
import com.ruoyi.project.zxsd.sys.service.IGoodsService;
import com.ruoyi.project.zxsd.sys.service.IStoreInfoService;
import com.ruoyi.project.zxsd.sys.util.ExcelReader;
import com.ruoyi.project.zxsd.sys.util.ExcelWriter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * @author gkm
 * @version 1.0
 * @date 2020/5/25 10:42
 */
@RestController
@RequestMapping("/goods")
@Api(tags = {"【后台端】8.1 商品管理"}, description = "商品管理")
public class GoodsController extends BaseController{
    @Autowired
    private final IGoodsService goodsService;
    @Autowired
    private final IStoreInfoService storeInfoService;
    public GoodsController(IGoodsService goodsService,IStoreInfoService storeInfoService) {
        this.goodsService = goodsService;
        this.storeInfoService=storeInfoService;
    }

    @Log(title = "8.1.1 导入商品", businessType = BusinessType.INSERT)
    @PostMapping("/importgoods")
    @ApiOperation(value = "8.1.1  导入商品", notes = "导入商品")
    public AjaxResult importGoods(MultipartFile file){
        try{
            List<GoodsEntity> goodsList= ExcelReader.readExcel(file);
            if(goodsList !=null){
                goodsService.addGoodsList(goodsList);
                return new AjaxResult().success("商品导入成功");
            }else {
                logger.error("商品导入失败，解析后集合为空" );
                return new AjaxResult().error("商品导入失败");
            }
        }catch (Exception e){
            logger.error("商品导入失败",e );
            return new AjaxResult().error("商品导入失败");
        }
    }

    @Log(title = "8.1.2 导出商品", businessType = BusinessType.INSERT)
    @PostMapping("/exportgoods")
    @ApiOperation(value = "8.1.2  导出商品", notes = "导出商品")
    public void exportGoods(@RequestBody GoodsEntity goodsEntity, HttpServletRequest request, HttpServletResponse response){
        try{
            List<GoodsEntity> goodsList=goodsService.getGoodsList(goodsEntity);
            Workbook workbook= ExcelWriter.exportData(goodsList);
            try{
                response.setCharacterEncoding("utf-8");
                response.setContentType("multipart/form-data");
                response.setHeader("Content-Disposition", "attachment;fileName="+ FileUtils.setFileDownloadHeader(request,"goodsList.xlsx"));
                OutputStream output=response.getOutputStream();
                BufferedOutputStream bufferedOutput=new BufferedOutputStream(output);
                workbook.write(bufferedOutput);
                bufferedOutput.flush();
                bufferedOutput.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }catch (Exception e){
            logger.error("商品导出失败", e);
        }
    }

    @Log(title = "8.1.3 上架商品", businessType = BusinessType.INSERT)
    @PostMapping("/upgoods")
    @ApiOperation(value = "8.1.3 上架商品", notes = "上架商品")
    public AjaxResult upGoods(String id){

        if(StringUtils.isEmpty(id)){
            return new AjaxResult().error("商品id不能为空");
        }
        try{
            goodsService.upGoods(id);
            return AjaxResult.success("上架商品成功");
        }
        catch (Exception e){
            logger.error("上架商品失败", e);
            return AjaxResult.error("上架商品失败");
        }
    }

    @Log(title = "8.1.4 下架商品", businessType = BusinessType.INSERT)
    @PostMapping("/downgoods")
    @ApiOperation(value = "8.1.4 下架商品", notes = "下架商品")
    public  AjaxResult downGoods(String id){

        if(StringUtils.isEmpty(id)){
            return new AjaxResult().error("商品id不能为空");
        }
        try{
            goodsService.downGoods(id);
            return AjaxResult.success("下架商品成功");
        }
        catch (Exception e){
            logger.error("下架商品失败", e);
            return AjaxResult.error("下架商品失败");
        }
    }

    @Log(title = "8.1.5 删除商品", businessType = BusinessType.INSERT)
    @PostMapping("/delgoods")
    @ApiOperation(value = "8.1.5 删除商品", notes = "删除商品")
    public  AjaxResult delGoods(String id){

        if(StringUtils.isEmpty(id)){
            return new AjaxResult().error("商品id不能为空");
        }
        try{
            goodsService.delGoods(id);
            return AjaxResult.success("删除商品成功");
        }
        catch (Exception e){
            logger.error("删除商品失败", e);
            return AjaxResult.error("删除商品失败");
        }
    }

    @Log(title = "8.1.6 查询商品", businessType = BusinessType.INSERT)
    @GetMapping("/selectgoods")
    @ApiOperation(value = "8.1.6 查询商品", notes = "查询商品")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name="pageNum",dataType = "int",value="当前页数",defaultValue = "1"),
            @ApiImplicitParam(paramType="query",name="pageSize",dataType = "int",value="每页显示记录数",defaultValue = "10")
    })
    public TableDataInfo selectGoods(GoodsEntity goodsEntity){

        try{
            LoginUser loginUser = SecurityUtils.getLoginUser();
            SystemUserEntity systemUserEntity=loginUser.getSystemUserEntity();
            String outsideNo = "";
            if(systemUserEntity.getIsAdmin() == 0&&systemUserEntity.getRole()==1){
                StoreInfoEntity storeInfoEntity=storeInfoService.getStoreInfoByUserCode(systemUserEntity.getUserId());
                outsideNo=storeInfoEntity.getStoreNo();
            }else if(systemUserEntity.getIsAdmin()==1){
//            outsideNo = "";
            }
            goodsEntity.setOutsideNo(outsideNo);
            startPage();
            List<GoodsEntity> goodsList=goodsService.getGoodsList(goodsEntity);
            return getDataTable(goodsList);
        }catch (Exception e){
            logger.error("商品信息查询失败", e);
            TableDataInfo rspData=new TableDataInfo();
            rspData.setTotal(0L);
            rspData.setMsg("商品信息查询失败");
            return rspData;
        }
    }


    @Log(title = "8.1.10 查询商品", businessType = BusinessType.INSERT)
    @GetMapping("/selectall")
    @ApiOperation(value = "8.1.10 查询商品", notes = "查询商品")

    public  AjaxResult selectAll(GoodsEntity goodsEntity){

        try{
            List<GoodsEntity> goodsList=goodsService.getGoodsList(goodsEntity);
            return AjaxResult.success("查询商品成功",goodsList);
        }catch (Exception e){
            logger.error("商品信息查询失败", e);
            return AjaxResult.error("商品信息查询失败");
        }
    }

    @Log(title = "8.1.7 下载模板", businessType = BusinessType.INSERT)
    @PostMapping("/downexcel")
    @ApiOperation(value = "8.1.7 下载模板", notes = "下载模板")
    public void downExcel(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String fileName="goodsExcel.xlsx";
        try{
            String realFileName=System.currentTimeMillis()+fileName.substring(fileName.indexOf("_")+1);
            String filePath= RuoYiConfig.getDownloadPath()+fileName;
            response.setCharacterEncoding("utf-8");
            response.setContentType("multipart/form-data");
            response.setHeader("Content-Disposition", "attachment;fileName="+ FileUtils.setFileDownloadHeader(request,realFileName));
            FileUtils.writeBytes(filePath, response.getOutputStream());
        }catch (Exception e){
            logger.error("下载文件失败", e);
        }
    }

    @Log(title = "8.1.8 插入商品", businessType = BusinessType.INSERT)
    @PostMapping("/insertgood")
    @ApiOperation(value = "8.1.8 插入商品", notes = "插入商品")
    public AjaxResult insertGood(@RequestBody GoodsEntity goodsEntity){
        try{
            goodsService.insertGood(goodsEntity);
            return new AjaxResult().success("商品新增成功");
        }catch (Exception e){
            logger.error("商品新增失败",e);
            return new AjaxResult().error("商品新增失败");
        }
    }

    @Log(title = "8.1.9 修改商品", businessType = BusinessType.INSERT)
    @PostMapping("/editgood")
    @ApiOperation(value = "8.1.8 修改商品", notes = "修改商品")
    public AjaxResult editGood(@RequestBody GoodsEntity goodsEntity){
        try{
            goodsService.editGood(goodsEntity);
            return new AjaxResult().success("商品修改成功");
        }catch (Exception e){
            logger.error("商品修改失败",e);
            return new AjaxResult().error("商品修改失败");
        }
    }
}
