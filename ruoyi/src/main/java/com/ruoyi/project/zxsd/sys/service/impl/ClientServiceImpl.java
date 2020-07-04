package com.ruoyi.project.zxsd.sys.service.impl;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.framework.security.LoginUser;
import com.ruoyi.project.zxsd.sys.domain.ClientEntity;
import com.ruoyi.project.zxsd.sys.domain.StoreInfoEntity;
import com.ruoyi.project.zxsd.sys.domain.SystemUserEntity;
import com.ruoyi.project.zxsd.sys.mapper.ClientMapper;
import com.ruoyi.project.zxsd.sys.mapper.SystemUserMapper;
import com.ruoyi.project.zxsd.sys.service.IClientService;
import com.ruoyi.project.zxsd.sys.service.IStoreInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author gkm
 * @version 1.0
 * @date 2020/5/22 18:29
 */
@Service
@Slf4j
public class ClientServiceImpl implements IClientService{
    @Autowired
    private final SystemUserMapper sysUserMapper;
    @Autowired
    private final ClientMapper clientMapper;
    @Autowired
    private final IStoreInfoService storeInfoService;

    public ClientServiceImpl(SystemUserMapper sysUserMapper,ClientMapper clientMapper,IStoreInfoService storeInfoService) {
        this.sysUserMapper=sysUserMapper;
        this.clientMapper=clientMapper;
        this.storeInfoService=storeInfoService;
    }

    @Override
    public List<ClientEntity> selectClient(ClientEntity clientEntity) {

        return clientMapper.selectClient(clientEntity);
    }
}
