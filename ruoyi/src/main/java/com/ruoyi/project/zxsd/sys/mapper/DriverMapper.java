package com.ruoyi.project.zxsd.sys.mapper;

import com.ruoyi.project.zxsd.sys.domain.DriverEntity;
import org.apache.ibatis.annotations.Param;

import java.sql.Driver;
import java.util.List;

/**
 * @author gkm
 * @version 1.0
 * @date 2020/5/21 9:25
 */
public interface DriverMapper {
    void insertDriver(DriverEntity driverEntity);
    void delDriver(@Param("userCode") String userCode);
    List<DriverEntity> selectDriver(DriverEntity driverEntity);
    void editInfo(DriverEntity driverEntity);
    void editAddress(DriverEntity driverEntity);
}
