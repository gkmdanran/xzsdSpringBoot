package com.ruoyi.project.zxsd.sys.service;

import com.ruoyi.project.zxsd.sys.domain.CateEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author gkm
 * @version 1.0
 * @date 2020/5/23 13:43
 */
public interface ICateService {
    void insertCate(CateEntity cateEntity);
    List<CateEntity> selectCate();
    List<CateEntity> getCateInfo(String cateCode);
    int delCate(String cateCode);
    void editCate(CateEntity cateEntity);
    List<CateEntity> selectFirstCate();
    List<CateEntity> selectSecondCate(String cateCodeParent);
}
