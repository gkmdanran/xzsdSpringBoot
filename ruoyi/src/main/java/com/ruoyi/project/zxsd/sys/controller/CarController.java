package com.ruoyi.project.zxsd.sys.controller;

import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.zxsd.sys.domain.CarEntity;
import com.ruoyi.project.zxsd.sys.service.ICarService;
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
 * @date 2020/5/28 10:19
 */
@RestController
@RequestMapping("/car")
@Api(tags = {"【后台端】11.1 购物车"}, description = "购物车")
public class CarController extends BaseController{
    @Autowired
    private final ICarService carService;

    public CarController(ICarService carService) {
        this.carService = carService;
    }
    @Log(title = "11.1.1添加购物车", businessType = BusinessType.INSERT)
    @PostMapping("/addcar")
    @ApiOperation(value = "11.1.1添加购物车", notes = "添加购物车")
    public AjaxResult addCar(@RequestBody CarEntity carEntity){
        String userCode=carEntity.getUserCode();
//        if(StringUtils.isEmpty(userCode)){
//            return new AjaxResult().error("用户Id不能为空");
//        }
        String skuNo=carEntity.getSkuNo();
        if(StringUtils.isEmpty(skuNo)){
            return new AjaxResult().error("商品编码不能为空");
        }
        int cnt=carEntity.getCnt();
        if(cnt<=0){
            return new AjaxResult().error("数量不能为0");
        }
        try{
           carService.addCar(carEntity);
            return AjaxResult.success("购物车添加成功");
        }
        catch (Exception e){
            logger.error("购物车添加失败", e);
            return AjaxResult.error("购物车添加失败");
        }
    }

    @Log(title = "11.1.2 查询购物车", businessType = BusinessType.INSERT)
    @PostMapping("/selectcar")
    @ApiOperation(value = "11.1.2 查询购物车", notes = "查询购物车")
    public AjaxResult selectCar(@RequestBody CarEntity carEntity){
        String userCode=carEntity.getUserCode();
        if(StringUtils.isEmpty(userCode)){
            return new AjaxResult().error("用户Id不能为空");
        }
        try{
            List<CarEntity> carList=carService.selectCar(carEntity);
            return AjaxResult.success("购物车查询成功",carList);
        }
        catch (Exception e){
            logger.error("购物车查询失败", e);
            return AjaxResult.error("购物车查询失败");
        }
    }

    @Log(title = "11.1.3 删除购物车", businessType = BusinessType.INSERT)
    @PostMapping("/delcar")
    @ApiOperation(value = "11.1.3 删除购物车", notes = " 删除购物车")
    public AjaxResult delCar(){

        try{
            carService.delCar();
            return AjaxResult.success("删除购物车成功");
        }
        catch (Exception e){
            logger.error("删除购物车失败", e);
            return AjaxResult.error("删除购物车失败");
        }
    }

    @Log(title = "11.1.4 改变数量", businessType = BusinessType.INSERT)
    @PostMapping("/updatecnt")
    @ApiOperation(value = "11.1.4 改变数量", notes = "改变数量")
    public AjaxResult updateCnt(@RequestBody CarEntity carEntity){
        try{
            carService.updateCnt(carEntity);
            return AjaxResult.success("改变数量成功");
        }
        catch (Exception e){
            logger.error("改变数量失败", e);
            return AjaxResult.error("改变数量失败");
        }
    }

    @Log(title = "11.1.5 改变勾选", businessType = BusinessType.INSERT)
    @PostMapping("/updatecheck")
    @ApiOperation(value = "11.1.4 改变勾选", notes = "改变勾选")
    public AjaxResult updateCheck(@RequestBody CarEntity carEntity){
        try{
            carService.updateChecked(carEntity);
            return AjaxResult.success("改变勾选成功");
        }
        catch (Exception e){
            logger.error("改变勾选失败", e);
            return AjaxResult.error("改变勾选失败");
        }
    }
}
