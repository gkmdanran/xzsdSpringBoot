package com.ruoyi.project.storage.controller;

import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.storage.domain.Address;
import com.ruoyi.project.storage.service.IAddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

/**
 * 手机端地址Controller
 *
 * @author wangdong
 * @date 2020.05.10
 */
@ApiIgnore
@RestController
@RequestMapping("/app/address")
@Api(tags = {"【手机端】5.2.6 地址管理"}, description = "地址列表（分页）、地址新增、地址编辑、地址删除、设置默认地址")
public class AppAddressController extends BaseController {

    /**
     * 地址Service
     */
    private final IAddressService addressService;

    /**
     * 构造方法注入
     *
     * @param addressService 地址Service
     */
    @Autowired
    public AppAddressController(IAddressService addressService) {
        this.addressService = addressService;
    }

    /**
     * 查询地址列表
     *
     * @return 分页结果
     */
    @Log(title = "5.2.6.1 地址列表（分页）", businessType = BusinessType.OTHER)
    @GetMapping("/list")
    @ApiOperation(value = "5.2.6.1 地址列表（分页）", notes = "查询地址列表")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "pageNum", dataType = "int", value = "当前页数", defaultValue = "1"),
            @ApiImplicitParam(paramType = "query", name = "pageSize", dataType = "int", value = "每页显示记录数", defaultValue = "10")
    })
    public TableDataInfo list() {
        // 获取分页信息
        startPage();
        // 查询地址列表
        List<Address> list = addressService.selectAddressList();
        // 返回响应请求分页数据
        return getDataTable(list);
    }

    /**
     * 新增地址
     */
    @Log(title = "5.2.6.2 地址新增", businessType = BusinessType.INSERT)
    @PostMapping("/create")
    @ApiOperation(value = "5.2.6.2 地址新增", notes = "新增地址")
    public AjaxResult add(@RequestBody Address address) {
        // 返回响应结果
        return toAjax(addressService.insertAddress(address));
    }

    /**
     * 修改地址
     */
    @Log(title = "5.2.6.3 地址编辑", businessType = BusinessType.UPDATE)
    @PutMapping("/update")
    @ApiOperation(value = "5.2.6.3 地址编辑", notes = "修改地址")
    public AjaxResult edit(@RequestBody Address address) {
        // 返回响应结果
        return toAjax(addressService.updateAddress(address));
    }

    /**
     * 删除地址
     */
    @Log(title = "5.2.6.4 地址删除", businessType = BusinessType.DELETE)
    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "5.2.6.4 地址删除", notes = "删除地址")
    public AjaxResult remove(@PathVariable Long id) {
        // 返回响应结果
        return toAjax(addressService.deleteAddressById(id));
    }

    /**
     * 设置默认地址
     */
    @Log(title = "5.2.6.5 设置默认地址", businessType = BusinessType.UPDATE)
    @PutMapping("/default/{id}")
    @ApiOperation(value = "5.2.6.5 设置默认地址", notes = "设置默认地址")
    public AjaxResult setDefault(@PathVariable Long id) {
        // 返回响应结果
        return toAjax(addressService.setDefaultAddressById(id));
    }

}
