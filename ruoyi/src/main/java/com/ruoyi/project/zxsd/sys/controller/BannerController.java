package com.ruoyi.project.zxsd.sys.controller;

import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.common.utils.file.MimeTypeUtils;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.config.RuoYiConfig;
import com.ruoyi.framework.config.ServerConfig;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.zxsd.sys.domain.BannerEntity;
import com.ruoyi.project.zxsd.sys.domain.DriverEntity;
import com.ruoyi.project.zxsd.sys.domain.SystemUserEntity;
import com.ruoyi.project.zxsd.sys.mapper.BannerMapper;
import com.ruoyi.project.zxsd.sys.service.IBannerService;
import com.ruoyi.project.zxsd.sys.service.impl.BannerServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author gkm
 * @version 1.0
 * @date 2020/5/22 8:55
 */
@RestController
@RequestMapping("/banner")
@Api(tags = {"【后台端】5.1 轮播图管理"}, description = "轮播图管理")
public class BannerController extends BaseController{
    /**
     * 获取完整的请求路径，包括：域名，端口，上下文访问路径
     */
    private final ServerConfig serverConfig;
    @Autowired
    private final IBannerService bannerService;
    public BannerController(ServerConfig serverConfig,IBannerService bannerService) {
        this.serverConfig = serverConfig;
        this.bannerService=bannerService;
    }

    /**
     * 文件上传
     *
     * @param file 文件
     * @return 结果
     * @throws Exception 异常
     */
    @Log(title = "5.1.1 上传", businessType = BusinessType.OTHER)
    @PostMapping("/upload")
    @SuppressWarnings("all")
    @ApiOperation(value = "5.1.1 上传", notes = "文件上传")
    public AjaxResult uploadFile(MultipartFile file) throws Exception {
        try {
            // 上传文件路径
            String filePath = RuoYiConfig.getUploadPath();
            // 获取文件名后缀
            String exName = FileUploadUtils.getExtension(file);
            String[] a = MimeTypeUtils.IMAGE_EXTENSION;
            boolean flag = false;
            for (int i = 0; i < a.length; i++) {
                if(a[i].equals(exName)){
                    flag = true;
                }
            }
            if(!flag){
                return AjaxResult.error("文件类型不合法");
            }

            // 文件路径
            String fileName = FileUploadUtils.upload(filePath, file);
            // 完整的请求路径
            String url = serverConfig.getUrl() + fileName;
            // 返回成功消息
            AjaxResult ajax = AjaxResult.success();
            // 文件路径放入map
            ajax.put("fileName", fileName);
            // 完整的请求路径放入map
            ajax.put("url", url);
            // 返回
            return ajax;
        } catch (Exception e) {
            // 返回错误信息
            return AjaxResult.error(e.getMessage());
        }
    }

    @Log(title = "5.1.2 新增轮播图", businessType = BusinessType.INSERT)
    @PostMapping("/addbanner")
    @ApiOperation(value = "5.1.2 新增轮播图", notes = "新增轮播图")
    public AjaxResult insertBannerPic(@RequestBody BannerEntity bannerEntity){
        String imageUrl=bannerEntity.getImageUrl();
        if(StringUtils.isEmpty(imageUrl)){
            return new AjaxResult().error("访问路径不能为空");
        }
        try{
            bannerService.insertBannerPic(bannerEntity);
            return new AjaxResult().success("新增成功");
        }catch (Exception e){
            logger.error("新增失败");
            return new AjaxResult().error("新增失败");
        }
    }

    @Log(title = "5.1.3 启动轮播图", businessType = BusinessType.INSERT)
    @PostMapping("/startbanner")
    @ApiOperation(value = "5.1.3 启动轮播图", notes = "启动轮播图")
    public AjaxResult startStatus(@RequestBody BannerEntity bannerEntity){
        String bannerCode=bannerEntity.getBannerCode();

        if(StringUtils.isEmpty(bannerCode)){
            return AjaxResult.error("轮播图编号不能为空");
        }
        try{
            bannerService.startStatus(bannerCode);
            return AjaxResult.success("启动轮播图成功");
        }
        catch (Exception e){
            logger.error("启动轮播图失败", e);
            return AjaxResult.error("启动轮播图失败");
        }
    }

    @Log(title = "5.1.4 禁用轮播图", businessType = BusinessType.INSERT)
    @PostMapping("/stopbanner")
    @ApiOperation(value = "5.1.4 禁用轮播图", notes = "禁用轮播图")
    public AjaxResult stopStatus(@RequestBody BannerEntity bannerEntity){
        String bannerCode=bannerEntity.getBannerCode();

        if(StringUtils.isEmpty(bannerCode)){
            return AjaxResult.error("轮播图编号不能为空");
        }
        try{
            bannerService.stopStatus(bannerCode);
            return AjaxResult.success("禁用轮播图成功");
        }
        catch (Exception e){
            logger.error("禁用轮播图失败", e);
            return AjaxResult.error("禁用轮播图失败");
        }
    }
    @Log(title = "5.1.5 删除轮播图", businessType = BusinessType.INSERT)
    @PostMapping("/delbanner")
    @ApiOperation(value = "5.1.5 删除轮播图", notes = "删除轮播图")
    public AjaxResult delBanner(@RequestBody BannerEntity bannerEntity){
        String bannerCode=bannerEntity.getBannerCode();
        if(StringUtils.isEmpty(bannerCode)){
            return AjaxResult.error("轮播图编号不能为空");
        }
        try{
            bannerService.delBannerPic(bannerCode);
            return AjaxResult.success("删除轮播图成功");
        }
        catch (Exception e){
            logger.error("删除轮播图失败", e);
            return AjaxResult.error("删除轮播图失败");
        }
    }

    @Log(title = "5.1.6 查询轮播图", businessType = BusinessType.INSERT)
    @GetMapping("/selectbanner")
    @ApiOperation(value = "5.1.6 查询轮播图", notes = " 查询轮播图")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name="pageNum",dataType = "int",value="当前页数",defaultValue = "1"),
            @ApiImplicitParam(paramType="query",name="pageSize",dataType = "int",value="每页显示记录数",defaultValue = "10")
    })
    public TableDataInfo selectBannerPic(BannerEntity bannerEntity){
        startPage();
        try{
            List<BannerEntity> bannerList=bannerService.selectBannerPic(bannerEntity);
            return getDataTable(bannerList);
        }catch (Exception e){
            logger.error("查询失败", e);
            TableDataInfo rspData=new TableDataInfo();
            rspData.setTotal(0L);
            rspData.setMsg("司机信息查询失败");
            return rspData;
        }
    }

}
