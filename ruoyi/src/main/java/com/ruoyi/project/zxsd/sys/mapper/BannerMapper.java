package com.ruoyi.project.zxsd.sys.mapper;

import com.ruoyi.project.zxsd.sys.domain.BannerEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author gkm
 * @version 1.0
 * @date 2020/5/22 9:57
 */
public interface BannerMapper {
    void insertBannerPic(BannerEntity bannerEntity);
    void startStatus(@Param("bannerCode") String bannerCode);
    void stopStatus(@Param("bannerCode") String bannerCode);
    void delBannerPic(@Param("bannerCode") String bannerCode);
    List<BannerEntity> selectBannerPic(BannerEntity bannerEntity);
}
