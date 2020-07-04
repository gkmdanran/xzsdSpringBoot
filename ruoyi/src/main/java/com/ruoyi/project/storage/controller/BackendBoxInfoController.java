package com.ruoyi.project.storage.controller;

import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.storage.domain.BoxInfo;
import com.ruoyi.project.storage.domain.vo.BoxInfoVO;
import com.ruoyi.project.storage.service.IBoxInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

/**
 * 后台端箱子信息Controller
 *
 * @author wangdong
 * @date 2020.05.09
 */
@ApiIgnore
@RestController
@RequestMapping("/backend/box/info")
@Api(tags = {"【后台端】5.3.5 箱子信息"}, description = "箱子信息列表（分页）、箱子信息新增、箱子信息删除")
public class BackendBoxInfoController extends BaseController {

    /**
     * 箱子信息Service
     */
    private final IBoxInfoService boxInfoService;

    /**
     * 构造方法注入
     *
     * @param boxInfoService 箱子信息Service
     */
    @Autowired
    public BackendBoxInfoController(IBoxInfoService boxInfoService) {
        this.boxInfoService = boxInfoService;
    }

    /**
     * 查询箱子信息列表
     *
     * @param boxInfoVO 箱子信息显示对象
     * @return 分页结果
     */
    @Log(title = "5.3.5.1 箱子信息列表（分页）", businessType = BusinessType.OTHER)
    @GetMapping("/list")
    @ApiOperation(value = "5.3.5.1 箱子信息列表（分页）", notes = "查询箱子信息列表")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "pageNum", dataType = "int", value = "当前页数", defaultValue = "1"),
            @ApiImplicitParam(paramType = "query", name = "pageSize", dataType = "int", value = "每页显示记录数", defaultValue = "10")
    })
    public TableDataInfo list(BoxInfoVO boxInfoVO) {
        // 获取分页信息
        startPage();
        // 查询箱子信息列表
        List<BoxInfoVO> list = boxInfoService.selectBoxInfoList(boxInfoVO);
        // 返回响应请求分页数据
        return getDataTable(list);
    }

    /**
     * 新增箱子信息
     */
    @Log(title = "5.3.5.2 箱子信息新增", businessType = BusinessType.INSERT)
    @PostMapping("/create")
    @ApiOperation(value = "5.3.5.2 箱子信息新增", notes = "新增箱子信息")
    public AjaxResult add(@RequestBody BoxInfo boxInfo) {
        // 返回响应结果
        return toAjax(boxInfoService.insertBoxInfo(boxInfo));
    }

    /**
     * 删除箱子信息
     */
    @Log(title = "5.3.5.3 箱子信息删除", businessType = BusinessType.DELETE)
    @DeleteMapping("/delete/{ids}")
    @ApiOperation(value = "5.3.5.3 箱子信息删除", notes = "删除箱子信息")
    public AjaxResult remove(@PathVariable Long[] ids) {
        // 返回响应结果
        return toAjax(boxInfoService.deleteBoxInfoByIds(ids));
    }

}
