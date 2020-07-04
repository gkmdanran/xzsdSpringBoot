package com.ruoyi.project.zxsd.sys.service;

import com.ruoyi.project.zxsd.sys.domain.SystemMenuEntity;

import java.util.List;

/**
 * 用户Service接口
 *
 * @author jiaab
 * @date 2020-05-15
 */
public interface ISystemMenuService {


    void insertSysMenu(SystemMenuEntity systemMenuEntity);

    List<SystemMenuEntity> getMenuByUserRole(int userRole);

    void editSysMenu(SystemMenuEntity systemMenuEntity);

    int delSysMenu(SystemMenuEntity systemMenuEntity);
}
