package com.ruoyi.project.zxsd.sys.service.impl;

import com.ruoyi.common.utils.IdUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.security.LoginUser;
import com.ruoyi.project.system.mapper.SysUserMapper;
import com.ruoyi.project.zxsd.sys.domain.CateEntity;
import com.ruoyi.project.zxsd.sys.domain.GoodsEntity;
import com.ruoyi.project.zxsd.sys.domain.SystemMenuEntity;
import com.ruoyi.project.zxsd.sys.domain.SystemUserEntity;
import com.ruoyi.project.zxsd.sys.mapper.CateMapper;
import com.ruoyi.project.zxsd.sys.mapper.GoodsMapper;
import com.ruoyi.project.zxsd.sys.mapper.SystemUserMapper;
import com.ruoyi.project.zxsd.sys.service.ICateService;
import com.ruoyi.project.zxsd.sys.util.SystemCodeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gkm
 * @version 1.0
 * @date 2020/5/23 13:43
 */
@Service
@Slf4j
public class CateServiceImpl implements ICateService{
    @Autowired
    private final CateMapper cateMapper;

    @Autowired
    private final SystemUserMapper sysUserMapper;
    @Autowired
    private final GoodsMapper goodsMapper;

    public CateServiceImpl(CateMapper cateMapper,SystemUserMapper sysUserMapper,GoodsMapper goodsMapper) {
        this.cateMapper=cateMapper;
        this.sysUserMapper=sysUserMapper;
        this.goodsMapper=goodsMapper;
    }

    @Override
    public void insertCate(CateEntity cateEntity) {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        SystemUserEntity systemUserEntity=loginUser.getSystemUserEntity();
        cateEntity.setCreateBy(systemUserEntity.getUserName());
        cateEntity.setUpdateBy(systemUserEntity.getUserName());
        cateEntity.setCateCode(SystemCodeUtil.getUserCode());
        cateEntity.setId(IdUtils.fastSimpleUUID());
        int sortNo = 0;
        try{
            sortNo = sysUserMapper.getMaxSortNo("t_code_cate");
        }catch (Exception e){
            log.error("第一条数据序号异常可不用处理",e);
        }
        cateEntity.setSortNo(sortNo+1);
        int parentLevel=1;
        if(StringUtils.isNotEmpty(cateEntity.getCateCodeParent())){
            parentLevel = cateMapper.getParentLevel(cateEntity.getCateCodeParent());
           cateEntity.setLevel(parentLevel+1);
           cateEntity.setIsParent(0);
        }
        else {
            cateEntity.setLevel(parentLevel);
//            cateEntity.setp("0");
            cateEntity.setIsParent(1);
        }
        cateMapper.insertCate(cateEntity);
    }

    @Override
    public List<CateEntity> selectCate() {
        List<CateEntity> cateList = cateMapper.selectCate();
        List<CateEntity> oneList = new ArrayList<>();
        for (int i = 0; i < cateList.size(); i++) {
            if (cateList.get(i).getLevel()==1){
                oneList.add(cateList.get(i));
            }
        }
        //为一级菜单设置子菜单准备递归
        for (CateEntity cate:oneList) {
            //获取父菜单下所有子菜单调用getChildList
            List<CateEntity> childList = getChildList(String.valueOf(cate.getCateCode()),cateList);
            cate.setChildCate(childList);
        }
        return oneList;

    }

    public List<CateEntity> getChildList(String oneCateCode,List<CateEntity> cateList){
        //构建子菜单
        List<CateEntity> childList = new ArrayList<>();
        //遍历传入的list
        for (CateEntity cate:cateList) {
            //所有菜单的父id与传入的根节点id比较，若相等则为该级菜单的子菜单
            if (String.valueOf(cate.getCateCodeParent()).equals(oneCateCode)){
                childList.add(cate);
            }
        }
        //递归
        for (CateEntity m:childList) {
            m.setChildCate(getChildList(String.valueOf(m.getCateCode()),cateList));
        }
        if (childList.size() == 0){
            return null;
        }
        return childList;
    }

    @Override
    public List<CateEntity> getCateInfo(String cateCode) {
        return cateMapper.getCateInfo(cateCode);
    }

    @Override
    public int delCate(String cateCode) {
        String catecodes=null;
        List<CateEntity> cateList=cateMapper.getCateInfo(catecodes);
        GoodsEntity goodsEntity=new GoodsEntity();
        List<GoodsEntity> goodList=goodsMapper.getGoodsList(goodsEntity);
        for(GoodsEntity good :goodList){
            if(good.getCateCode().equals(cateCode)){
                return -2;
            }
        }
        for(CateEntity cate:cateList){
            if(cate.getCateCodeParent()!=null&&cate.getCateCodeParent().equals(cateCode)){
                return -1;
            }
        }
        try{
            cateMapper.delCate(cateCode);
            return 1;
        }
        catch (Exception e){
            return -1;
        }
    }

    @Override
    public void editCate(CateEntity cateEntity) {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        SystemUserEntity systemUserEntity=loginUser.getSystemUserEntity();
        cateEntity.setUpdateBy(systemUserEntity.getUserName());
        cateMapper.editCate(cateEntity);
    }

    @Override
    public List<CateEntity> selectFirstCate() {
        return cateMapper.selectFirstCate();
    }

    @Override
    public List<CateEntity> selectSecondCate(String cateCodeParent) {
        return cateMapper.selectSecondCate(cateCodeParent);
    }
}
