package com.ruoyi.project.zxsd.sys.mapper;

import com.ruoyi.project.zxsd.sys.domain.HotEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author gkm
 * @version 1.0
 * @date 2020/5/26 11:10
 */
public interface HotMapper {
    void insertHot(HotEntity hotEntity);
    List<HotEntity> selectHot(HotEntity hotEntity);
    void delHot(@Param("id") String id);
    void editHot(HotEntity hotEntity);
}
