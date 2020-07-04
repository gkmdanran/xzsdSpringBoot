package com.ruoyi.project.storage.controller;

import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.storage.domain.vo.BoxStandardVO;
import com.ruoyi.project.storage.service.IBoxStandardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.List;

/**
 * 后台端箱子规格Controller
 *
 * @author wangdong
 * @date 2020.05.06
 */
@ApiIgnore
@RestController
@RequestMapping("/backend/box/standard")
@Api(tags = {"【后台端】5.3.4 箱子规格"}, description = "箱子规格列表（分页）、箱子规格新增、箱子规格删除、箱子规格下拉列表（非分页）")
@Validated
public class BackendBoxStandardController extends BaseController {

    /**
     * 箱子规格Service
     */
    private final IBoxStandardService boxStandardService;

    /**
     * 构造方法注入
     *
     * @param boxStandardService 箱子规格Service
     */
    @Autowired
    public BackendBoxStandardController(IBoxStandardService boxStandardService) {
        this.boxStandardService = boxStandardService;
    }

    /**
     * 查询箱子规格列表
     *
     * @param boxStandardVO 箱子规格显示对象
     * @return 分页结果
     */
    @Log(title = "5.3.4.1 箱子规格列表（分页）", businessType = BusinessType.OTHER)
    @GetMapping("/list")
    @ApiOperation(value = "5.3.4.1 箱子规格列表（分页）", notes = "查询箱子规格列表")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "pageNum", dataType = "int", value = "当前页数", defaultValue = "1"),
            @ApiImplicitParam(paramType = "query", name = "pageSize", dataType = "int", value = "每页显示记录数", defaultValue = "10")
    })
    public TableDataInfo list(BoxStandardVO boxStandardVO) {
        // 获取分页信息
        startPage();
        // 查询箱子规格列表
        List<BoxStandardVO> list = boxStandardService.selectBoxStandardList(boxStandardVO);
        // 返回响应请求分页数据
        return getDataTable(list);
    }

    /**
     * 新增箱子规格
     */
    @Log(title = "5.3.4.2 箱子规格新增", businessType = BusinessType.INSERT)
    @PostMapping("/create")
    @ApiOperation(value = "5.3.4.2 箱子规格新增", notes = "新增箱子规格")
    public AjaxResult add(@Valid @RequestBody BoxStandardVO boxStandardVO) {
        // 返回响应结果
        return toAjax(boxStandardService.insertBoxStandard(boxStandardVO));
    }

    /**
     * 删除箱子规格
     */
    @Log(title = "5.3.4.3 箱子规格删除", businessType = BusinessType.DELETE)
    @DeleteMapping("/delete/{ids}")
    @ApiOperation(value = "5.3.4.3 箱子规格删除", notes = "删除箱子规格")
    public AjaxResult remove(@PathVariable Long[] ids) {
        // 返回响应结果
        return toAjax(boxStandardService.deleteBoxStandardByIds(ids));
    }

    /**
     * 查询箱子规格下拉列表
     *
     * @return 返回通用返回实体（非分页）
     */
    @Log(title = "5.3.4.4 箱子规格下拉列表（非分页）", businessType = BusinessType.OTHER)
    @GetMapping("/select")
    @ApiOperation(value = "5.3.4.4 箱子规格下拉列表（非分页）", notes = "查询箱子规格下拉列表")
    public AjaxResult select() {
        // 返回通用返回实体（非分页）
        return AjaxResult.success(boxStandardService.selectBoxStandardSelectList());
    }

}
