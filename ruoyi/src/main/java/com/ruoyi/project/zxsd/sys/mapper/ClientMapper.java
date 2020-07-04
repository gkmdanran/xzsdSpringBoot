package com.ruoyi.project.zxsd.sys.mapper;

import com.ruoyi.project.zxsd.sys.domain.ClientEntity;

import java.util.List;

/**
 * @author gkm
 * @version 1.0
 * @date 2020/5/22 18:26
 */
public interface ClientMapper {
    List<ClientEntity> selectClient(ClientEntity clientEntity);
}
