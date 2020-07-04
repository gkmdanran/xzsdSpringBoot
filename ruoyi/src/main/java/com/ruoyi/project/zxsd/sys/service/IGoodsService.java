package com.ruoyi.project.zxsd.sys.service;

import com.ruoyi.project.zxsd.sys.domain.GoodsEntity;
import com.ruoyi.project.zxsd.sys.mapper.GoodsMapper;

import java.util.List;

/**
 * @author gkm
 * @version 1.0
 * @date 2020/5/25 10:40
 */
public interface IGoodsService {
    void addGoodsList(List<GoodsEntity> goodsList);
    List<GoodsEntity> getGoodsList(GoodsEntity goodsEntity);
    void upGoods(String id);
    void downGoods(String id);
    void delGoods(String id);
    void insertGood(GoodsEntity goodsEntity);
    void editGood(GoodsEntity goodsEntity);
}
