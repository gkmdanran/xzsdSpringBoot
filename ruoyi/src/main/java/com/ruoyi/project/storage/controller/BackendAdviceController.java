package com.ruoyi.project.storage.controller;

import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.storage.domain.vo.AdviceVO;
import com.ruoyi.project.storage.service.IAdviceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

/**
 * 后台端意见Controller
 *
 * @author wangdong
 * @date 2020.05.01
 */
@ApiIgnore
@RestController
@RequestMapping("/backend/advice")
@Api(tags = {"【后台端】5.3.8 意见建议"}, description = "意见建议列表（分页）")
public class BackendAdviceController extends BaseController {

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
    public BackendAdviceController(IAdviceService adviceService) {
        this.adviceService = adviceService;
    }

    /**
     * 查询意见建议列表
     *
     * @param adviceVO 意见显示对象
     * @return 分页结果
     */
    @Log(title = "5.3.8.1 意见建议列表（分页）", businessType = BusinessType.OTHER)
    @GetMapping("/list")
    @ApiOperation(value = "5.3.8.1 意见建议列表（分页）", notes = "查询意见建议列表")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "pageNum", dataType = "int", value = "当前页数", defaultValue = "1"),
            @ApiImplicitParam(paramType = "query", name = "pageSize", dataType = "int", value = "每页显示记录数", defaultValue = "10")
    })
    public TableDataInfo list(AdviceVO adviceVO) {
        // 获取分页信息
        startPage();
        // 查询意见列表
        List<AdviceVO> list = adviceService.selectAdviceList(adviceVO);
        // 返回响应请求分页数据
        return getDataTable(list);
    }

}
