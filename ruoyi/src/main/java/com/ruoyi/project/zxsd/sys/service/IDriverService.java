package com.ruoyi.project.zxsd.sys.service;

import com.ruoyi.project.zxsd.sys.domain.DriverEntity;

import java.util.List;

/**
 * @author gkm
 * @version 1.0
 * @date 2020/5/21 9:25
 */
public interface IDriverService {
    void insertDriver(DriverEntity driverEntity);
    void delDriver(String userCode);
    List<DriverEntity> selectDriver(DriverEntity driverEntity);
    void editDriver(DriverEntity driverEntity);
}
