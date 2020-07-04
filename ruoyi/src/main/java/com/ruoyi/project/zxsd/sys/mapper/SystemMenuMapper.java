package com.ruoyi.project.zxsd.sys.mapper;

import com.ruoyi.project.zxsd.sys.domain.SystemMenuEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户Mapper接口
 *
 * @author wangdong
 * @date 2020-05-02
 */
public interface SystemMenuMapper {

    void insertSysMenu(SystemMenuEntity systemMenuEntity);

    List<SystemMenuEntity> getMenuByUserRole(@Param("userRole") int userRole);

    int getParentLevel(String parentMenuCode);

    void editSysMenu(SystemMenuEntity systemMenuEntity);

    void delSysMenu(SystemMenuEntity systemMenuEntity);
}
