package com.ruoyi.project.storage.controller;

import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.storage.service.IAddressService;
import com.ruoyi.project.storage.service.IBoxStandardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 手机端预约Controller
 *
 * @author wangdong
 * @date 2020.05.10
 */
@ApiIgnore
@RestController
@RequestMapping("/app")
@Api(tags = {"【手机端】5.2.3 空箱预约"}, description = "获取当前用户默认收货地址、有效箱子规格选择（非分页）、预约")
@Slf4j
public class AppMakeOrderController extends BaseController {

    /**
     * 箱子规格Service接口
     */
    private final IBoxStandardService boxStandardService;

    /**
     * 地址Service接口
     */
    private final IAddressService addressService;


    /**
     * 构造方法注入
     *
     * @param boxStandardService 箱子规格Service接口
     * @param addressService     地址Service接口
     */
    @Autowired
    public AppMakeOrderController(IBoxStandardService boxStandardService, IAddressService addressService) {
        this.boxStandardService = boxStandardService;
        this.addressService = addressService;
    }

    /**
     * 获取当前用户默认收货地址
     *
     * @return 当前用户默认收货地址
     */
    @Log(title = "5.2.3.1 获取当前用户默认收货地址", businessType = BusinessType.OTHER)
    @GetMapping("/box/defaultAddress")
    @ApiOperation(value = "5.2.3.1 获取当前用户默认收货地址", notes = "获取当前用户默认收货地址")
    public AjaxResult defaultAddress() {
        // 返回通用返回实体（非分页）
        return AjaxResult.success(addressService.selectDefaultAddress());
    }

    /**
     * 有效箱子规格选择（非分页）
     *
     * @return 有效箱子规格（非分页）
     */
    @Log(title = "5.2.3.2 有效箱子规格选择（非分页）", businessType = BusinessType.OTHER)
    @GetMapping("/box/standard/select")
    @ApiOperation(value = "5.2.3.2 有效箱子规格选择（非分页）", notes = "有效箱子规格选择（非分页）")
    public AjaxResult select() {
        // 返回通用返回实体（非分页）
        return AjaxResult.success(boxStandardService.selectBoxStandardEffectiveList());
    }

}
