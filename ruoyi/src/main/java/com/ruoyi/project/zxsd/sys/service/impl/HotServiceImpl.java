package com.ruoyi.project.zxsd.sys.service.impl;

import com.ruoyi.common.utils.IdUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.framework.security.LoginUser;
import com.ruoyi.project.zxsd.sys.domain.HotEntity;
import com.ruoyi.project.zxsd.sys.domain.SystemUserEntity;
import com.ruoyi.project.zxsd.sys.mapper.HotMapper;
import com.ruoyi.project.zxsd.sys.service.IHotService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author gkm
 * @version 1.0
 * @date 2020/5/26 11:11
 */
@Service
@Slf4j
public class HotServiceImpl implements IHotService{
    @Autowired
    private final HotMapper hotMapper;

    public HotServiceImpl(HotMapper hotMapper) {
        this.hotMapper=hotMapper;
    }

    @Override
    public void insertHot(HotEntity hotEntity) {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        SystemUserEntity systemUserEntity = loginUser.getSystemUserEntity();
        hotEntity.setCreateBy(systemUserEntity.getUserName());
        hotEntity.setUpdateBy(systemUserEntity.getUserName());
        hotEntity.setId(IdUtils.fastSimpleUUID());
        hotMapper.insertHot(hotEntity);
    }

    @Override
    public List<HotEntity> selectHot(HotEntity hotEntity) {
        return hotMapper.selectHot(hotEntity);
    }

    @Override
    public void delHot(String id) {
       hotMapper.delHot(id);
    }

    @Override
    public void editHot(HotEntity hotEntity) {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        SystemUserEntity systemUserEntity = loginUser.getSystemUserEntity();
        hotEntity.setUpdateBy(systemUserEntity.getUserName());
        hotMapper.editHot(hotEntity);
    }
}
