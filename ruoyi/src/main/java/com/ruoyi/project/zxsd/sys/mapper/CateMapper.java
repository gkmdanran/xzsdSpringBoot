package com.ruoyi.project.zxsd.sys.mapper;

import com.ruoyi.project.zxsd.sys.domain.CateEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author gkm
 * @version 1.0
 * @date 2020/5/23 13:42
 */
public interface CateMapper {
    void insertCate(CateEntity cateEntity);
    int getParentLevel(String cateCodeParent);
    List<CateEntity> selectCate();
    List<CateEntity> getCateInfo(@Param("cateCode") String cateCode);
    void delCate(@Param("cateCode") String cateCode);
    void editCate(CateEntity cateEntity);
    List<CateEntity> selectFirstCate();
    List<CateEntity> selectSecondCate(@Param("cateCodeParent") String cateCodeParent);
}
