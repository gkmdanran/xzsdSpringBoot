package com.ruoyi.project.zxsd.sys.service;

import com.ruoyi.project.zxsd.sys.domain.OrderDetailEntity;
import com.ruoyi.project.zxsd.sys.domain.OrderEntity;

import java.util.List;

/**
 * @author gkm
 * @version 1.0
 * @date 2020/5/26 20:10
 */
public interface IOrderService {
    void insertOrder(List<OrderEntity> orderList);
    List<OrderEntity> selectOrder(OrderEntity orderEntity);
    List<OrderDetailEntity> selectDetails(OrderDetailEntity orderDetailEntity);
    void changeOrderStatus(OrderEntity orderEntity);
}
