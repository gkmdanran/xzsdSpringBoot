package com.ruoyi.project.storage.controller;

import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.storage.domain.Advertisement;
import com.ruoyi.project.storage.service.IAdvertisementService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 手机端首页Controller
 *
 * @author wangdong
 * @date 2020.05.03
 */
@ApiIgnore
@RestController
@RequestMapping("/app/advertisement")
@Api(tags = {"【手机端】5.2.2 首页"}, description = "广告列表（非分页）、广告积分获取")
public class AppAdvertisementController extends BaseController {

    /**
     * 广告Service
     */
    private final IAdvertisementService advertisementService;

    /**
     * 构造方法注入
     *
     * @param advertisementService 广告Service
     */
    @Autowired
    public AppAdvertisementController(IAdvertisementService advertisementService) {
        this.advertisementService = advertisementService;
    }

    /**
     * 查询广告列表
     *
     * @return 返回通用返回实体（非分页）
     */
    @Log(title = "5.2.2.1 广告列表（非分页）", businessType = BusinessType.OTHER)
    @GetMapping("/list")
    @ApiOperation(value = "5.2.2.1 广告列表（非分页）", notes = "查询广告列表")
    public AjaxResult list() {
        // 实例化广告对象
        Advertisement advertisement = new Advertisement();
        // 设置只查询启用的广告
        advertisement.setIsEnable(0);
        // 返回通用返回实体（非分页）
        return AjaxResult.success(advertisementService.selectAdvertisementList(advertisement));
    }

    /**
     * 广告积分获取
     */
    @Log(title = "5.2.2.2 广告积分获取", businessType = BusinessType.OTHER)
    @PostMapping("/points")
    @ApiOperation(value = "5.2.2.2 广告积分获取", notes = "广告积分获取")
    public AjaxResult points(@RequestBody Advertisement advertisement) {
        // 返回响应结果
        return toAjax(advertisementService.getPointsForUser(advertisement));
    }

}
