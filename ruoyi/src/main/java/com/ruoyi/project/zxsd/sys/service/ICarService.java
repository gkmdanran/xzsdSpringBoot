package com.ruoyi.project.zxsd.sys.service;

import com.ruoyi.project.zxsd.sys.domain.CarEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author gkm
 * @version 1.0
 * @date 2020/5/28 10:06
 */
public interface ICarService {
    void addCar(CarEntity carEntity);
    List<CarEntity> selectCar(CarEntity carEntity);
    void delCar();
    void updateCnt(CarEntity carEntity);
    void updateChecked(CarEntity carEntity);
}
