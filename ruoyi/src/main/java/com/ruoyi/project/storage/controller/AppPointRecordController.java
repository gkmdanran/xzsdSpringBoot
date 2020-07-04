package com.ruoyi.project.storage.controller;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.storage.domain.PointRecord;
import com.ruoyi.project.storage.service.IPointRecordService;
import com.ruoyi.project.storage.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

/**
 * 手机端积分记录Controller
 *
 * @author wangdong
 * @date 2020.04.30
 */
@ApiIgnore
@RestController
@RequestMapping("/app/point")
@Api(tags = {"【手机端】5.2.7 积分记录"}, description = "积分记录列表（分页）、获取当前用户积分")
@Slf4j
public class AppPointRecordController extends BaseController {

    /**
     * 用户Service接口
     */
    private final IUserService userService;

    /**
     * 积分记录Service接口
     */
    private final IPointRecordService pointRecordService;


    /**
     * 构造方法注入
     *
     * @param userService        用户Service接口
     * @param pointRecordService 积分记录Service接口
     */
    @Autowired
    public AppPointRecordController(IUserService userService, IPointRecordService pointRecordService) {
        this.userService = userService;
        this.pointRecordService = pointRecordService;
    }

    /**
     * 查询积分记录列表
     *
     * @return 分页结果
     */
    @Log(title = "5.2.7.1 积分记录列表（分页）", businessType = BusinessType.OTHER)
    @GetMapping("/list")
    @ApiOperation(value = "5.2.7.1 积分记录列表（分页）", notes = "查询积分记录列表")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "pageNum", dataType = "int", value = "当前页数", defaultValue = "1"),
            @ApiImplicitParam(paramType = "query", name = "pageSize", dataType = "int", value = "每页显示记录数", defaultValue = "10")
    })
    public TableDataInfo list() {
        // 获取分页信息
        startPage();
        // 查询积分记录列表
        List<PointRecord> list = pointRecordService.selectPointRecordList(SecurityUtils.getUserId());
        // 返回响应请求分页数据
        return getDataTable(list);
    }

    /**
     * 获取当前用户积分
     *
     * @return 通用返回实体（非分页）
     */
    @Log(title = "5.2.7.2 获取当前用户积分", businessType = BusinessType.OTHER)
    @GetMapping("/select")
    @ApiOperation(value = "5.2.7.2 获取当前用户积分", notes = "获取当前用户积分")
    public AjaxResult select() {
        // 返回通用返回实体（非分页）
        return AjaxResult.success(userService.getUserPoints(SecurityUtils.getUserId()));
    }

}
