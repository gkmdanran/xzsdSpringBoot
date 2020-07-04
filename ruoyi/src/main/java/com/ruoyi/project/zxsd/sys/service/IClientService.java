package com.ruoyi.project.zxsd.sys.service;

import com.ruoyi.project.zxsd.sys.domain.ClientEntity;

import java.util.List;

/**
 * @author gkm
 * @version 1.0
 * @date 2020/5/22 18:29
 */
public interface IClientService {
    List<ClientEntity> selectClient(ClientEntity clientEntity);
}
