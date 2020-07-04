package com.ruoyi.project.zxsd.sys.mapper;

import com.ruoyi.project.zxsd.sys.domain.GoodsEntity;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * @author gkm
 * @version 1.0
 * @date 2020/5/25 10:41
 */
public interface GoodsMapper {
    void addGoodsList(@Param("goodsList") List<GoodsEntity> goodsList);
    List<GoodsEntity> getGoodsList(GoodsEntity goodsEntity);
    void upGoods(@Param("id") String id);
    void downGoods(@Param("id") String id);
    void delGoods(@Param("id") String id);
    void delPic(@Param("id") String id);
    void insertGood(GoodsEntity goodsEntity);
    void editGood(GoodsEntity goodsEntity);
    void addPic(GoodsEntity goodsEntity);
    void editPic(GoodsEntity goodsEntity);
}
