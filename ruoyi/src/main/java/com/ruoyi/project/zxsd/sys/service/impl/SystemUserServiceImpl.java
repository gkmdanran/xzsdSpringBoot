package com.ruoyi.project.zxsd.sys.service.impl;

import com.ruoyi.common.utils.IdUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.framework.security.LoginUser;
import com.ruoyi.project.common.enums.TerminalEnum;
import com.ruoyi.project.zxsd.sys.domain.SystemUserEntity;
import com.ruoyi.project.zxsd.sys.mapper.SystemUserMapper;
import com.ruoyi.project.zxsd.sys.service.ISystemUserService;
import com.ruoyi.project.zxsd.sys.util.SystemCodeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 用户 业务层处理
 *
 * @author ruoyi
 */
@Service
@Slf4j
public class SystemUserServiceImpl implements ISystemUserService {
    /**
     * 用户Mapper
     */
    @Autowired//自动装配方式，从Spring IoC容器中去查找
    private final SystemUserMapper sysUserMapper;

    public SystemUserServiceImpl(SystemUserMapper sysUserMapper) {
        this.sysUserMapper = sysUserMapper;
    }


    @Override
    public SystemUserEntity getSystemUserByUserName(String userName) {
        SystemUserEntity systemUserEntity = sysUserMapper.getSystemUserByUserName(userName);
        return systemUserEntity;
    }

    @Override
    @Transactional  //事务回滚
    public void insertSystemUser(SystemUserEntity systemUserEntity) {
//        LoginUser loginUser = SecurityUtils.getLoginUser();
//        String userName = loginUser.getUsername();
//        systemUserEntity.setCreateBy(userName);
//        systemUserEntity.setUpdateBy(userName);
        try{
            LoginUser loginUser = SecurityUtils.getLoginUser();
            String userName = loginUser.getUsername();
            systemUserEntity.setCreateBy(userName);
            systemUserEntity.setUpdateBy(userName);
        }catch (Exception e){
            systemUserEntity.setCreateBy(systemUserEntity.getUserName());
            systemUserEntity.setUpdateBy(systemUserEntity.getUserName());
        } //捕获异常
        systemUserEntity.setPassword(SecurityUtils.encryptPassword(!"".equals(systemUserEntity.getPassword()) ? systemUserEntity.getPassword() : TerminalEnum.BACKEND.getPassword()));
        String userId = SystemCodeUtil.getUserCode();
        systemUserEntity.setUserId(userId);
        String id = IdUtils.fastSimpleUUID();
        systemUserEntity.setId(id);

        sysUserMapper.insertSystemUser(systemUserEntity);

        int sortNo = sysUserMapper.getMaxSortNo("t_sys_user");
        systemUserEntity.setSortNo(sortNo+1);
        sysUserMapper.insertSystemUserInfo(systemUserEntity);
    }

    @Override
    @Transactional  //事务回滚
    public void editSystemUser(SystemUserEntity systemUserEntity) {
        sysUserMapper.editSystemUser(systemUserEntity);
        sysUserMapper.editSystemUserInfo(systemUserEntity);
    }

    @Override
    public void deleteSystemUser(SystemUserEntity systemUserEntity) {
        sysUserMapper.deleteSystemUserInfo(systemUserEntity);
        sysUserMapper.deleteSystemUser(systemUserEntity);
    }

    @Override
    public List<SystemUserEntity> selectUser(SystemUserEntity systemUserEntity) {
        return sysUserMapper.selectUser(systemUserEntity);
    }

    @Override
    public void editInviteCode(SystemUserEntity systemUserEntity) {
        sysUserMapper.editInviteCode(systemUserEntity);
    }
}
