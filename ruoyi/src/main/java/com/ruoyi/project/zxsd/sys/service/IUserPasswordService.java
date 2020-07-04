package com.ruoyi.project.zxsd.sys.service;

import com.ruoyi.project.zxsd.sys.domain.UserPasswordEntity;

import java.util.List;

/**
 * @author gkm
 * @version 1.0
 * @date 2020/6/3 11:11
 */
public interface IUserPasswordService {
    void editPassword(UserPasswordEntity userPasswordEntity);
    List<UserPasswordEntity> selectPassword(UserPasswordEntity userPasswordEntity);
}
