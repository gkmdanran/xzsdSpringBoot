package com.ruoyi.project.storage.controller;

import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.storage.domain.Advice;
import com.ruoyi.project.storage.service.IAdviceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 手机端意见Controller
 *
 * @author wangdong
 * @date 2020.05.01
 */
@ApiIgnore
@RestController
@RequestMapping("/app/personal/advice")
@Api(tags = {"【手机端】5.2.8 意见建议"}, description = "意见建议新增")
public class AppAdviceController extends BaseController {

    /**
     * 意见Service
     */
    private final IAdviceService adviceService;

    /**
     * 构造方法注入
     *
     * @param adviceService 意见Service
     */
    @Autowired
    public AppAdviceController(IAdviceService adviceService) {
        this.adviceService = adviceService;
    }

    /**
     * 意见建议新增
     *
     * @param advice 意见建议
     * @return 通用返回实体（非分页）
     */
    @Log(title = "意见建议新增", businessType = BusinessType.INSERT)
    @PostMapping
    @ApiOperation(value = "意见建议新增", notes = "意见建议新增")
    public AjaxResult list(Advice advice) {
        // 返回响应结果
        return toAjax(adviceService.insertAdvice(advice));
    }

}
