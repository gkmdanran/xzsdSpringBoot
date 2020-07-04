package com.ruoyi.project.zxsd.sys.controller;

import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.zxsd.sys.domain.CommentEntity;
import com.ruoyi.project.zxsd.sys.domain.OrderDetailEntity;
import com.ruoyi.project.zxsd.sys.service.ICommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author gkm
 * @version 1.0
 * @date 2020/6/7 13:25
 */
@RestController
@RequestMapping("/comment")
@Api(tags = {"【后台端】13.1 评论管理"}, description = "评论管理")
public class CommentController extends BaseController{
    @Autowired
    private ICommentService commentService;

    public CommentController(ICommentService commentService) {
        this.commentService=commentService;
    }
    @Log(title = "13.1.1 新增评论", businessType = BusinessType.INSERT)
    @PostMapping("/addcomment")
    @ApiOperation(value = "13.1.1 新增评论", notes = "新增评论")
    public AjaxResult insertComment(@RequestBody CommentEntity commentEntity){
        try{
            commentService.insertComment(commentEntity);
            return AjaxResult.success("新增评论成功");
        }
        catch (Exception e){
            logger.error("新增评论失败", e);
            return AjaxResult.error("新增评论失败");
        }
    }

    @Log(title = "13.1.2 查询评论", businessType = BusinessType.INSERT)
    @PostMapping("/selectcomment")
    @ApiOperation(value = "13.1.2 查询评论", notes = "查询评论")
    public AjaxResult selectDetails(@RequestBody CommentEntity commentEntity){
        String skuNo=commentEntity.getSkuNo();
        if(StringUtils.isEmpty(skuNo)){
           return AjaxResult.error("商品编号不能为空");
       }
        try{
            List<CommentEntity> commentLists=commentService.selectComment(commentEntity);
            return AjaxResult.success("查询评论成功",commentLists);
        }
        catch (Exception e){
            logger.error("查询评论失败", e);
            return AjaxResult.error("查询评论失败");
        }
    }
}
