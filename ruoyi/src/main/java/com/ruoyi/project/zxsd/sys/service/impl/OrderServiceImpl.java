package com.ruoyi.project.zxsd.sys.service.impl;

/**
 * @author gkm
 * @version 1.0
 * @date 2020/5/26 20:10
 */

import com.ruoyi.common.utils.IdUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.framework.security.LoginUser;
import com.ruoyi.project.zxsd.sys.domain.*;
import com.ruoyi.project.zxsd.sys.mapper.DriverMapper;
import com.ruoyi.project.zxsd.sys.mapper.GoodsMapper;
import com.ruoyi.project.zxsd.sys.mapper.OrderMapper;
import com.ruoyi.project.zxsd.sys.mapper.SystemUserMapper;
import com.ruoyi.project.zxsd.sys.service.IOrderService;
import com.ruoyi.project.zxsd.sys.service.IStoreInfoService;
import com.ruoyi.project.zxsd.sys.util.SystemCodeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

@Service
@Slf4j

public class OrderServiceImpl implements IOrderService{
    @Autowired
    private final OrderMapper orderMapper;
    @Autowired
    private final SystemUserMapper sysUserMapper;
    @Autowired
    private final DriverMapper driverMapper;
    @Autowired
    private final GoodsMapper goodsMapper;
    @Autowired
    private final IStoreInfoService storeInfoService;
    public OrderServiceImpl(OrderMapper orderMapper,SystemUserMapper sysUserMapper,DriverMapper driverMapper,GoodsMapper goodsMapper,IStoreInfoService storeInfoService) {
        this.orderMapper = orderMapper;
        this.sysUserMapper=sysUserMapper;
        this.driverMapper=driverMapper;
        this.goodsMapper=goodsMapper;
        this.storeInfoService=storeInfoService;
    }

    @Override
    @Transactional  //事务回滚
    public void insertOrder(List<OrderEntity> orderList) {

        for(int i=0;i<orderList.size();i++){
            LoginUser loginUser = SecurityUtils.getLoginUser();
            String userName = loginUser.getUsername();
            SystemUserEntity systemUserEntity=loginUser.getSystemUserEntity();
            OrderEntity orderEntity=orderList.get(i);
            orderEntity.setCreateBy(userName);
            orderEntity.setUpdateBy(userName);
            String orderNo="OD"+ SystemCodeUtil.getUserCode();
            orderEntity.setOrderNo(orderNo);
            orderEntity.setUserCode(systemUserEntity.getUserId());
            double AllMoney=0;
            for(int j=0;j<orderEntity.getOrderDetails().size();j++) {
                OrderDetailEntity orderDetailEntity = orderEntity.getOrderDetails().get(j);
                double totalMoney=orderDetailEntity.getGoodsCnt()*orderDetailEntity.getGoodsPrice();
                AllMoney+=totalMoney;
            }
            orderEntity.setOrderMoney(AllMoney);
            orderEntity.setShippingMoney(8);
            orderEntity.setPaymentMoney(AllMoney-orderEntity.getDistrictMoney()+8);
            orderEntity.setId(IdUtils.fastSimpleUUID());
            int sortNo = 0;
            try{
                sortNo = sysUserMapper.getMaxSortNo("t_order_master");
            }catch (Exception e){
                log.error("插入商品信息异常",e);
            }
            orderEntity.setSortNo(sortNo+1);
            for(int j=0;j<orderEntity.getOrderDetails().size();j++){
                    OrderDetailEntity orderDetailEntity = orderEntity.getOrderDetails().get(j);
                    orderDetailEntity.setCreateBy(userName);
                    orderDetailEntity.setUpdateBy(userName);
                   orderDetailEntity.setOrderDetailCode("DT"+ SystemCodeUtil.getUserCode());
                    double totalMoney=orderDetailEntity.getGoodsCnt()*orderDetailEntity.getGoodsPrice();
                   orderDetailEntity.setFeeMoney(totalMoney/AllMoney*orderEntity.getDistrictMoney());
                    orderDetailEntity.setId(IdUtils.fastSimpleUUID());
                   int sortNo1 = 0;
                   try{
                       sortNo1 = sysUserMapper.getMaxSortNo("t_order_detail");
                   }catch (Exception e){
                       log.error("插入商品信息异常",e);
                   }
                   orderDetailEntity.setSortNo(sortNo1+1);
                   orderDetailEntity.setOrderNo(orderNo);
                    GoodsEntity goodsEntity=new GoodsEntity();
                    goodsEntity.setSkuNo(orderDetailEntity.getSkuNo());
                    GoodsEntity sellGoods=goodsMapper.getGoodsList(goodsEntity).get(0);
                    int amountCnt=sellGoods.getAmountCnt();
                    sellGoods.setAmountCnt(amountCnt-orderDetailEntity.getGoodsCnt());
                    int saleCnt=sellGoods.getSaleCnt();
                    sellGoods.setSaleCnt(saleCnt+orderDetailEntity.getGoodsCnt());
                    orderMapper.updateGoods(sellGoods);
                   orderMapper.insertOrderDetail(orderDetailEntity);
            }
            DriverEntity driverEntity=new DriverEntity();
            driverEntity.setStoreNo(orderEntity.getStoreNo());
            List<DriverEntity> driverList=driverMapper.selectDriver(driverEntity);
            Random ra =new Random();
            if(driverList.size()>0){
                int x=ra.nextInt(driverList.size());
                orderEntity.setDriver(driverList.get(x).getDriverName());
            }
            else{
                orderEntity.setDriver("");
            }
            orderMapper.insertOrder(orderEntity);
        }
    }

    @Override
    public List<OrderEntity> selectOrder(OrderEntity orderEntity) {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        SystemUserEntity systemUserEntity=loginUser.getSystemUserEntity();
        String storeNo = "";
        if(systemUserEntity.getIsAdmin() == 0&&systemUserEntity.getRole()==1){
            StoreInfoEntity storeInfoEntity=storeInfoService.getStoreInfoByUserCode(systemUserEntity.getUserId());
            storeNo=storeInfoEntity.getStoreNo();
        }else if(systemUserEntity.getIsAdmin()==1){
            storeNo = "";
        }
        orderEntity.setRole(systemUserEntity.getRole());
        orderEntity.setStoreNo(storeNo);
        orderEntity.setUserCode(systemUserEntity.getUserId());
        return orderMapper.selectOrder(orderEntity);
    }

    @Override
    public List<OrderDetailEntity> selectDetails(OrderDetailEntity orderDetailEntity) {
        return orderMapper.selectDetails(orderDetailEntity);
    }

    @Override
    @Transactional  //事务回滚
    public void changeOrderStatus(OrderEntity orderEntity) {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        String userName = loginUser.getUsername();
        orderEntity.setUpdateBy(userName);
        if(orderEntity.getOdFlag().equals("2")){
            orderEntity.setOrderStatus(2);
            for(int j=0;j<orderEntity.getOrderDetails().size();j++) {
                OrderDetailEntity orderDetailEntity = orderEntity.getOrderDetails().get(j);
                GoodsEntity goodsEntity = new GoodsEntity();
                goodsEntity.setSkuNo(orderDetailEntity.getSkuNo());
                GoodsEntity sellGoods = goodsMapper.getGoodsList(goodsEntity).get(0);
                int amountCnt = sellGoods.getAmountCnt();
                sellGoods.setAmountCnt(amountCnt + orderDetailEntity.getGoodsCnt());
                int saleCnt = sellGoods.getSaleCnt();
                sellGoods.setSaleCnt(saleCnt - orderDetailEntity.getGoodsCnt());
                orderMapper.updateGoods(sellGoods);
            }
        }
        if(orderEntity.getOdFlag().equals("3")){
            orderEntity.setOrderStatus(3);
        }
       if(orderEntity.getOdFlag().equals("4")){
            orderEntity.setOrderStatus(4);
        }
        if(orderEntity.getOdFlag().equals("5")){
            orderEntity.setOrderStatus(5);
        }
        if(orderEntity.getOdFlag().equals("6")){
            orderEntity.setOrderStatus(6);
        }
        if(orderEntity.getOdFlag().equals("1")){
            orderEntity.setOrderStatus(1);
        }
        OrderDetailEntity orderDetailEntity=new OrderDetailEntity();
        orderDetailEntity.setOdFlag(orderEntity.getOdFlag());
        orderDetailEntity.setOrderNo(orderEntity.getOrderNo());
        orderMapper.changeOrderStatus(orderEntity);
        orderMapper.changeDetailStatus(orderDetailEntity);
    }
}
