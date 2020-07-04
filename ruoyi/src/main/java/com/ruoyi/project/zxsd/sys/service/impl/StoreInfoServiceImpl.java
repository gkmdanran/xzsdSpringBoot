package com.ruoyi.project.zxsd.sys.service.impl;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.framework.security.LoginUser;
import com.ruoyi.project.system.mapper.SysUserMapper;
import com.ruoyi.project.zxsd.sys.domain.CountryEntity;
import com.ruoyi.project.zxsd.sys.domain.ProvinceEntity;
import com.ruoyi.project.zxsd.sys.domain.StoreInfoEntity;
import com.ruoyi.project.zxsd.sys.domain.SystemUserEntity;
import com.ruoyi.project.zxsd.sys.mapper.StoreInfoMapper;
import com.ruoyi.project.zxsd.sys.mapper.SystemUserMapper;
import com.ruoyi.project.zxsd.sys.service.IStoreInfoService;
import com.ruoyi.project.zxsd.sys.util.SystemCodeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Queue;
import java.util.Random;

/**
 * @author gkm
 * @version 1.0
 * @date 2020/5/19 9:07
 */
@Service
@Slf4j
public class StoreInfoServiceImpl implements IStoreInfoService{
    @Autowired
    private final StoreInfoMapper storeInfoMapper;

    @Autowired
    private final SystemUserMapper sysUserMapper;
    public StoreInfoServiceImpl(StoreInfoMapper storeInfoMapper,SystemUserMapper sysUserMapper) {
        this.storeInfoMapper=storeInfoMapper;
        this.sysUserMapper = sysUserMapper;
    }

    @Override
    public List<ProvinceEntity> selectProvince() {
        return storeInfoMapper.selectProvince();
    }

    @Override
    public List<CountryEntity> selectCounty(String provinceCode) {
        return storeInfoMapper.selectCounty(provinceCode);
    }

    @Override
    public List<StoreInfoEntity> getListStoreInfo(StoreInfoEntity storeInfoEntity) {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        SystemUserEntity systemUserEntity=loginUser.getSystemUserEntity();
        String userCode = "";
        if(systemUserEntity.getIsAdmin() == 0&&systemUserEntity.getRole()==1){
            userCode=systemUserEntity.getUserId();
        }else if(systemUserEntity.getIsAdmin()==1){
            userCode = "";
        }
        storeInfoEntity.setUserCode(userCode);
        return storeInfoMapper.getListStoreInfo(storeInfoEntity);
    }

    @Override
    public void delStoreInfo(StoreInfoEntity storeInfoEntity) {
        storeInfoMapper.delStoreInfo(storeInfoEntity);
    }

    @Override
    public void insertStoreInfo(StoreInfoEntity storeInfoEntity) {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        String userName = loginUser.getUsername();
        storeInfoEntity.setCreateBy(userName);
        storeInfoEntity.setUpdateBy(userName);
        storeInfoEntity.setInviteCode(getInviteCodes());
        String storeNo = SystemCodeUtil.getMenuCode();
        storeInfoEntity.setStoreNo("S"+storeNo);
        int sortNo=0;
        try{
            sortNo = sysUserMapper.getMaxSortNo("t_store_info");
        }catch (Exception e){
            log.error("插入门店异常", e);
        }

        storeInfoEntity.setSortNo(sortNo+1);
        String id = SystemCodeUtil.getUserCode();
        storeInfoEntity.setId(id);
        storeInfoMapper.insertStoreInfo(storeInfoEntity);
    }

    @Override
    public void editStoreInfo(StoreInfoEntity storeInfoEntity) {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        String userName = loginUser.getUsername();
        storeInfoEntity.setUpdateBy(userName);
        storeInfoMapper.editStoreInfo(storeInfoEntity);
    }
    public static String getInviteCodes(){
        String res="YQM";

        char[]arr=new char[52];
        int count=0;
        for (int i = 97; i <=122; i++) {
            arr[count]=(char)i;
            count++;
        }
        for (int i = 65; i <= 90; i++) {
            arr[count]=(char)i;
            count++;
        }
        Random r=new Random();
        String result="";
        //循环N次
        int time=r.nextInt(4)+1;
        for (int i = 0; i < time; i++) {
            int randomIndex=r.nextInt(arr.length);
            char c = arr[randomIndex];
            //System.out.print(c);
            result+=c;
        }
        int number=r.nextInt(10);
        result+=number;
        //循环4-N次
        for (int i = 0; i < 4-time; i++) {
            int randomIndex=r.nextInt(arr.length);
            char c = arr[randomIndex];
            //System.out.print(c);
            result+=c;
        }
        return res+result;
    }

    @Override
    public StoreInfoEntity getStoreInfoByUserCode(String userId) {
        return storeInfoMapper.getStoreInfoByUserCode(userId);
    }
}
