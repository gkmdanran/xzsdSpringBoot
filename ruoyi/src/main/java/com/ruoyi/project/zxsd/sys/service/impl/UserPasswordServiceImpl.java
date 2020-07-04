package com.ruoyi.project.zxsd.sys.service.impl;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.project.common.enums.TerminalEnum;
import com.ruoyi.project.zxsd.sys.domain.UserPasswordEntity;
import com.ruoyi.project.zxsd.sys.mapper.UserPasswordMapper;
import com.ruoyi.project.zxsd.sys.service.IUserPasswordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author gkm
 * @version 1.0
 * @date 2020/6/3 11:10
 */
@Service
@Slf4j
public class UserPasswordServiceImpl implements IUserPasswordService{
    @Autowired//自动装配方式，从Spring IoC容器中去查找
    private final UserPasswordMapper userPasswordMapper;

    public UserPasswordServiceImpl(UserPasswordMapper userPasswordMapper) {
        this.userPasswordMapper = userPasswordMapper;
    }

    @Override
    public void editPassword(UserPasswordEntity userPasswordEntity) {
        userPasswordEntity.setNewPassword(SecurityUtils.encryptPassword(
                !"".equals(userPasswordEntity.getNewPassword()) ? userPasswordEntity.getNewPassword()
                        : TerminalEnum.BACKEND.getPassword()));


        userPasswordMapper.editPassword(userPasswordEntity);
    }

    @Override
    public List<UserPasswordEntity> selectPassword(UserPasswordEntity userPasswordEntity) {
        return userPasswordMapper.selectPassword(userPasswordEntity);
    }
}
