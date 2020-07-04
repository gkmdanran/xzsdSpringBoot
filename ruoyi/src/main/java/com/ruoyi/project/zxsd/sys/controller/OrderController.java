package com.ruoyi.project.zxsd.sys.controller;

import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.zxsd.sys.domain.OrderDetailEntity;
import com.ruoyi.project.zxsd.sys.domain.OrderEntity;
import com.ruoyi.project.zxsd.sys.domain.StoreInfoEntity;
import com.ruoyi.project.zxsd.sys.service.IOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author gkm
 * @version 1.0
 * @date 2020/5/26 20:08
 */
@RestController
@RequestMapping("/order")
@Api(tags = {"【后台端】10.1 订单"}, description = "订单")
public class OrderController extends BaseController{
    @Autowired
    private final IOrderService orderService;

    public OrderController(IOrderService orderService) {
        this.orderService = orderService;
    }

    @Log(title = "10.1.1 产生订单", businessType = BusinessType.INSERT)
    @PostMapping("/insertorder")
    @ApiOperation(value = "10.1.1 产生订单", notes = "产生订单")
    public AjaxResult insertOrder(@RequestBody List<OrderEntity> orderList){
        try{
            orderService.insertOrder(orderList);
            return new AjaxResult().success("订单新增成功");
        }catch (Exception e){
            logger.error("订单新增失败",e);
            return new AjaxResult().error("订单新增失败");
        }
    }

    @Log(title = "10.1.2 查询订单", businessType = BusinessType.INSERT)
    @GetMapping("/selectorder")
    @ApiOperation(value = "10.1.1 查询订单", notes = "产生订单")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name="pageNum",dataType = "int",value="当前页数",defaultValue = "1"),
            @ApiImplicitParam(paramType="query",name="pageSize",dataType = "int",value="每页显示记录数",defaultValue = "10")
    })
    public TableDataInfo getListStoreInfo(OrderEntity orderEntity){
        startPage();
        try {

            List<OrderEntity> orderList=orderService.selectOrder(orderEntity);
            return getDataTable(orderList);
        }
        catch (Exception e){
            logger.error("订单查询失败", e);
            TableDataInfo rspData=new TableDataInfo();
            rspData.setTotal(0L);
            rspData.setMsg("订单息查询失败");
            return rspData;
        }

    }

    @Log(title = "10.1.3 查询详细订单", businessType = BusinessType.INSERT)
    @PostMapping("/selectdetails")
    @ApiOperation(value = "10.1.3 查询详细订单", notes = "查询详细订单")
    public AjaxResult selectDetails(@RequestBody OrderDetailEntity orderDetailEntity){
//        String orderNo=orderDetailEntity.getOrderNo();
//        if(StringUtils.isEmpty(orderNo)){
//            return AjaxResult.error("主订单号不能为空");
//        }
        try{
            List<OrderDetailEntity>  detailLists=orderService.selectDetails(orderDetailEntity);
            return AjaxResult.success("查询详情订单成功",detailLists);
        }
        catch (Exception e){
            logger.error("查询详情订单失败", e);
            return AjaxResult.error("查询详情订单失败");
        }
    }

    @Log(title = "10.1.4 改变订单状态", businessType = BusinessType.INSERT)
    @PostMapping("/changestatus")
    @ApiOperation(value = "10.1.4 改变订单状态", notes = "改变订单状态")
    public AjaxResult changeStatus(@RequestBody OrderEntity orderEntity){
        String orderNo=orderEntity.getOrderNo();
        if(StringUtils.isEmpty(orderNo)){
            return AjaxResult.error("主订单号不能为空");
        }
        String flag=orderEntity.getOdFlag();
        if(StringUtils.isEmpty(flag)){
            return AjaxResult.error("操作标志不能为空");
        }
        try{
            orderService.changeOrderStatus(orderEntity);
            return AjaxResult.success("修改订单状态成功");
        }
        catch (Exception e){
            logger.error("修改订单状态失败", e);
            return AjaxResult.error("修改订单状态失败");
        }
    }

}
