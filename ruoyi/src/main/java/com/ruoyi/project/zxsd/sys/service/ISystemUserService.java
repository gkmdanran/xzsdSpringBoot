package com.ruoyi.project.zxsd.sys.service;

import com.ruoyi.project.zxsd.sys.domain.SystemMenuEntity;
import com.ruoyi.project.zxsd.sys.domain.SystemUserEntity;

import java.util.List;

/**
 * 用户Service接口
 *
 * @author jiaab
 * @date 2020-05-15
 */
public interface ISystemUserService {
    SystemUserEntity getSystemUserByUserName(String userName);

    void insertSystemUser(SystemUserEntity systemUserEntity);

    void editSystemUser(SystemUserEntity systemUserEntity);

    void deleteSystemUser(SystemUserEntity systemUserEntity);

    List<SystemUserEntity> selectUser(SystemUserEntity systemUserEntity);
    void editInviteCode(SystemUserEntity systemUserEntity);
}
