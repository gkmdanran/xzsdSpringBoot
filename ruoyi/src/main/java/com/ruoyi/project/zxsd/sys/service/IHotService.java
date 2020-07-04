package com.ruoyi.project.zxsd.sys.service;

import com.ruoyi.project.zxsd.sys.domain.HotEntity;

import java.util.List;

/**
 * @author gkm
 * @version 1.0
 * @date 2020/5/26 11:11
 */
public interface IHotService {
    void insertHot(HotEntity hotEntity);
    List<HotEntity> selectHot(HotEntity hotEntity);
    void delHot(String id);
    void editHot(HotEntity hotEntity);
}
