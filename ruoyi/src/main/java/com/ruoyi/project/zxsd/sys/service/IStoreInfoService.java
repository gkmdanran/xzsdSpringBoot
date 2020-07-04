package com.ruoyi.project.zxsd.sys.service;

import com.ruoyi.project.zxsd.sys.domain.CountryEntity;
import com.ruoyi.project.zxsd.sys.domain.ProvinceEntity;
import com.ruoyi.project.zxsd.sys.domain.StoreInfoEntity;

import java.util.List;

/**
 * @author gkm
 * @version 1.0
 * @date 2020/5/19 9:06
 */
public interface IStoreInfoService {
    List<ProvinceEntity> selectProvince();
    List<CountryEntity> selectCounty(String provinceCode);
    List<StoreInfoEntity> getListStoreInfo(StoreInfoEntity storeInfoEntity);
    void delStoreInfo(StoreInfoEntity storeInfoEntity);
    void insertStoreInfo(StoreInfoEntity storeInfoEntity);
    void editStoreInfo(StoreInfoEntity storeInfoEntity);
    StoreInfoEntity getStoreInfoByUserCode(String userId);
}
