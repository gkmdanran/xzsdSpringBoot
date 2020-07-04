package com.ruoyi.project.zxsd.sys.service.impl;

import com.ruoyi.common.utils.IdUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.framework.security.LoginUser;
import com.ruoyi.project.zxsd.sys.domain.CarEntity;
import com.ruoyi.project.zxsd.sys.domain.SystemUserEntity;
import com.ruoyi.project.zxsd.sys.mapper.CarMapper;
import com.ruoyi.project.zxsd.sys.mapper.CateMapper;
import com.ruoyi.project.zxsd.sys.mapper.SystemUserMapper;
import com.ruoyi.project.zxsd.sys.service.ICarService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author gkm
 * @version 1.0
 * @date 2020/5/28 10:06
 */
@Service
@Slf4j
public class CarServiceImpl implements ICarService{
    @Autowired
    private final CarMapper carMapper;
    @Autowired
    private final SystemUserMapper sysUserMapper;
    public CarServiceImpl(CarMapper carMapper,SystemUserMapper sysUserMapper) {
        this.carMapper = carMapper;
        this.sysUserMapper=sysUserMapper;
    }

    @Override
    public void addCar(CarEntity carEntity) {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        String userName = loginUser.getUsername();
        SystemUserEntity systemUserEntity=loginUser.getSystemUserEntity();
        carEntity.setCreateBy(userName);
        carEntity.setUpdateBy(userName);
        carEntity.setUserCode(systemUserEntity.getUserId());

        int sortNo = 0;
        try{
            sortNo = sysUserMapper.getMaxSortNo("t_shop_car");
        }catch (Exception e){
            log.error("第一条数据序号异常可不用处理",e);
        }
        carEntity.setSortNo(sortNo+1);
        List<CarEntity> carlist=carMapper.selectCar(carEntity);
        int flag=0;
        int cnt=0;
        String id="";
        for(int i=0;i<carlist.size();i++){
            if(carlist.get(i).getSkuNo().equals(carEntity.getSkuNo())&&carlist.get(i).getUserCode().equals(carEntity.getUserCode())){
                cnt=carlist.get(i).getCnt();
                id=carlist.get(i).getId();
                flag=1;
                break;
            }
        }
        if(flag==1){
            cnt+=carEntity.getCnt();
            carEntity.setCnt(cnt);
            carEntity.setId(id);
            carMapper.updateCnt(carEntity);
        }
        else{
            carEntity.setId(IdUtils.fastSimpleUUID());
            carMapper.addCar(carEntity);
        }

    }

    @Override
    public List<CarEntity> selectCar(CarEntity carEntity) {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        String userName = loginUser.getUsername();
        SystemUserEntity systemUserEntity=loginUser.getSystemUserEntity();
        carEntity.setUserCode(systemUserEntity.getUserId());
        return carMapper.selectCar(carEntity);
    }

    @Override
    public void delCar() {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        SystemUserEntity systemUserEntity=loginUser.getSystemUserEntity();
        carMapper.delCar(systemUserEntity.getUserId());
    }

    @Override
    public void updateCnt(CarEntity carEntity) {
       carMapper.updateCnt(carEntity);
    }

    @Override
    public void updateChecked(CarEntity carEntity) {
        carMapper.updateChecked(carEntity);
    }
}
