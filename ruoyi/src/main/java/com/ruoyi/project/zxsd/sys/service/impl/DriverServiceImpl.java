package com.ruoyi.project.zxsd.sys.service.impl;

import com.ruoyi.common.utils.IdUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.framework.security.LoginUser;
import com.ruoyi.project.zxsd.sys.domain.DriverEntity;
import com.ruoyi.project.zxsd.sys.domain.StoreInfoEntity;
import com.ruoyi.project.zxsd.sys.domain.SystemMenuEntity;
import com.ruoyi.project.zxsd.sys.domain.SystemUserEntity;
import com.ruoyi.project.zxsd.sys.mapper.DriverMapper;
import com.ruoyi.project.zxsd.sys.mapper.SystemUserMapper;
import com.ruoyi.project.zxsd.sys.service.IDriverService;
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
 * @date 2020/5/21 9:25
 */
@Service
@Slf4j
public class DriverServiceImpl implements IDriverService{
    @Autowired
    private final DriverMapper driverMapper;
    @Autowired
    private final IStoreInfoService storeInfoService;
    @Autowired
    private final SystemUserMapper sysUserMapper;
    public DriverServiceImpl(DriverMapper driverMapper,IStoreInfoService storeInfoService,SystemUserMapper sysUserMapper) {
        this.driverMapper=driverMapper;
        this.storeInfoService=storeInfoService;
        this.sysUserMapper=sysUserMapper;
    }

    @Override
    public void insertDriver(DriverEntity driverEntity) {
        //生成司机编号
        driverEntity.setDriverNo(SystemCodeUtil.getUserCode());
        LoginUser loginUser = SecurityUtils.getLoginUser();
        SystemUserEntity systemUserEntity=loginUser.getSystemUserEntity();
        if(systemUserEntity.getIsAdmin() == 0 && systemUserEntity.getRole() == 1){
            StoreInfoEntity storeInfoEntity = storeInfoService.getStoreInfoByUserCode(systemUserEntity.getUserId());
            driverEntity.setStoreNo(storeInfoEntity.getStoreNo());
            driverEntity.setProvinceNo(storeInfoEntity.getProvinceNo());
            driverEntity.setCountyNo(storeInfoEntity.getCountyNo());
            driverEntity.setCreateBy(loginUser.getUsername());
            driverEntity.setUpdateBy(loginUser.getUsername());
            driverEntity.setId(IdUtils.fastSimpleUUID());
            int sortNo = 0;
            try{
                sortNo = sysUserMapper.getMaxSortNo("t_driver_info");
            }catch (Exception e){
                log.error("插入司机信息异常",e);
            }
            driverEntity.setSortNo(sortNo+1);
           driverMapper.insertDriver(driverEntity);
        }
    }

    @Override
    public void delDriver(String userCode) {
        driverMapper.delDriver(userCode);
    }

    @Override
    public List<DriverEntity> selectDriver(DriverEntity driverEntity) {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        SystemUserEntity systemUserEntity=loginUser.getSystemUserEntity();
        String storeNo = "";
        if(systemUserEntity.getIsAdmin() == 0&&systemUserEntity.getRole()==1){
            StoreInfoEntity storeInfoEntity=storeInfoService.getStoreInfoByUserCode(systemUserEntity.getUserId());
            storeNo=storeInfoEntity.getStoreNo();
        }else if(systemUserEntity.getIsAdmin()==1){
            storeNo = "";
        }
        driverEntity.setStoreNo(storeNo);
        return driverMapper.selectDriver(driverEntity);

    }

    @Override
    @Transactional
    public void editDriver(DriverEntity driverEntity) {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        String userName = loginUser.getUsername();
        driverEntity.setUpdateBy(userName);
        driverMapper.editInfo(driverEntity);
        driverMapper.editAddress(driverEntity);
    }
}
