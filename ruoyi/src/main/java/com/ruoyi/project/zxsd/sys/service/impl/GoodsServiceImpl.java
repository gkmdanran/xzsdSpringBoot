package com.ruoyi.project.zxsd.sys.service.impl;

import com.ruoyi.common.utils.IdUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.framework.security.LoginUser;
import com.ruoyi.project.zxsd.sys.domain.GoodsEntity;
import com.ruoyi.project.zxsd.sys.domain.StoreInfoEntity;
import com.ruoyi.project.zxsd.sys.domain.SystemUserEntity;
import com.ruoyi.project.zxsd.sys.mapper.GoodsMapper;
import com.ruoyi.project.zxsd.sys.mapper.SystemUserMapper;
import com.ruoyi.project.zxsd.sys.service.IGoodsService;
import com.ruoyi.project.zxsd.sys.service.IStoreInfoService;
import com.ruoyi.project.zxsd.sys.util.SystemCodeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author gkm
 * @version 1.0
 * @date 2020/5/25 10:41
 */
@Service
@Slf4j
public class GoodsServiceImpl implements IGoodsService{
    @Autowired
    private final GoodsMapper goodsMapper;
    @Autowired
    private final SystemUserMapper sysUserMapper;
    @Autowired
    private final IStoreInfoService storeInfoService;
    public GoodsServiceImpl(GoodsMapper goodsMapper,SystemUserMapper sysUserMapper,IStoreInfoService storeInfoService) {
        this.goodsMapper = goodsMapper;
        this.sysUserMapper=sysUserMapper;
        this.storeInfoService=storeInfoService;
    }

    @Override
    @Transactional  //事务回滚
    public void addGoodsList(List<GoodsEntity> goodsList) {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        SystemUserEntity systemUserEntity = loginUser.getSystemUserEntity();
        for(int i =0;i<goodsList.size();i++){
            GoodsEntity goodsEntity=(GoodsEntity)goodsList.get(i);
            goodsEntity.setCreateBy(systemUserEntity.getUserName());
            goodsEntity.setUpdateBy(systemUserEntity.getUserName());
            goodsEntity.setId(IdUtils.fastSimpleUUID());

            int sortNo=0;
            goodsEntity.setSortNo(sortNo+1);
        }
        goodsMapper.addGoodsList(goodsList);
        for(int i=0;i<goodsList.size();i++){
            goodsList.get(i).setSurPicUrl("");
            goodsMapper.addPic(goodsList.get(i));
        }
    }

    @Override
    public List<GoodsEntity> getGoodsList(GoodsEntity goodsEntity) {

        return goodsMapper.getGoodsList(goodsEntity);
    }

    @Override
    public void upGoods(String id) {
        goodsMapper.upGoods(id);
    }
    @Override
    public void downGoods(String id) {
        goodsMapper.downGoods(id);
    }

    @Override
    @Transactional  //事务回滚
    public void delGoods(String id) {
        goodsMapper.delGoods(id);
        goodsMapper.delPic(id);
    }

    @Override
    @Transactional  //事务回滚
    public void insertGood(GoodsEntity goodsEntity) {
        String skuNo = SystemCodeUtil.getUserCode();
        goodsEntity.setSkuNo(skuNo);
        String id = IdUtils.fastSimpleUUID();
        goodsEntity.setId(id);
        LoginUser loginUser = SecurityUtils.getLoginUser();
        String userName = loginUser.getUsername();
        goodsEntity.setCreateBy(userName);
        goodsEntity.setUpdateBy(userName);
        int sortNo = 0;
        try{
            sortNo = sysUserMapper.getMaxSortNo("t_goods_sku");
        }catch (Exception e){
            log.error("插入商品信息异常",e);
        }
        goodsEntity.setSortNo(sortNo+1);
        goodsMapper.insertGood(goodsEntity);
        goodsMapper.addPic(goodsEntity);
    }

    @Override
    @Transactional  //事务回滚
    public void editGood(GoodsEntity goodsEntity) {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        String userName = loginUser.getUsername();
        goodsEntity.setUpdateBy(userName);
        goodsMapper.editGood(goodsEntity);
        goodsMapper.editPic(goodsEntity);
    }
}
