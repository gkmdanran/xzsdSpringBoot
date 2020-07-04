package com.ruoyi.project.zxsd.sys.mapper;

import com.ruoyi.project.zxsd.sys.domain.CarEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author gkm
 * @version 1.0
 * @date 2020/5/28 10:04
 */
public interface CarMapper {
    void addCar(CarEntity carEntity);
    List<CarEntity> selectCar(CarEntity carEntity);
    void delCar(@Param("userCode") String userCode);
    void updateCnt(CarEntity carEntity);
    void updateChecked(CarEntity carEntity);
}
