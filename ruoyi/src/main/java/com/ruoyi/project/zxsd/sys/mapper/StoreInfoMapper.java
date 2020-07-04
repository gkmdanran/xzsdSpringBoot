package com.ruoyi.project.zxsd.sys.mapper;

import com.ruoyi.project.zxsd.sys.domain.CountryEntity;
import com.ruoyi.project.zxsd.sys.domain.ProvinceEntity;
import com.ruoyi.project.zxsd.sys.domain.StoreInfoEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author gkm
 * @version 1.0
 * @date 2020/5/19 9:05
 */
public interface StoreInfoMapper {
    List<ProvinceEntity> selectProvince();
    List<CountryEntity> selectCounty(@Param("provinceCode") String provinceCode);
    List<StoreInfoEntity> getListStoreInfo(StoreInfoEntity storeInfoEntity);
    void delStoreInfo(StoreInfoEntity storeInfoEntity);
    void insertStoreInfo(StoreInfoEntity storeInfoEntity);
    void editStoreInfo(StoreInfoEntity storeInfoEntity);
    StoreInfoEntity getStoreInfoByUserCode(@Param("userId") String userId);
}
