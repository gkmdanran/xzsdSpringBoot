package com.ruoyi.project.zxsd.sys.mapper;

import com.ruoyi.framework.web.domain.server.Sys;
import com.ruoyi.project.zxsd.sys.domain.SystemMenuEntity;
import com.ruoyi.project.zxsd.sys.domain.SystemUserEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户Mapper接口
 *
 * @author wangdong
 * @date 2020-05-02
 */
public interface SystemUserMapper {

    SystemUserEntity getSystemUserByUserName(String userName);

    void insertSystemUser(SystemUserEntity systemUserEntity);

    void insertSystemUserInfo(SystemUserEntity systemUserEntity);

    int getMaxSortNo(@Param("tableName") String tableName);

    void editSystemUserInfo(SystemUserEntity systemUserEntity);

    void editSystemUser(SystemUserEntity systemUserEntity);

    void deleteSystemUser(SystemUserEntity systemUserEntity);

    void deleteSystemUserInfo(SystemUserEntity systemUserEntity);

    List<SystemUserEntity> selectUser(SystemUserEntity systemUserEntity);
    void editInviteCode(SystemUserEntity systemUserEntity);
}
