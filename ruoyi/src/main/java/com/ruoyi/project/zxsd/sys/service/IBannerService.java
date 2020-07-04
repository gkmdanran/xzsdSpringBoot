package com.ruoyi.project.zxsd.sys.service;

import com.ruoyi.project.zxsd.sys.domain.BannerEntity;

import java.util.List;

/**
 * @author gkm
 * @version 1.0
 * @date 2020/5/22 9:58
 */
public interface IBannerService {
    void insertBannerPic(BannerEntity bannerEntity);
    void startStatus( String bannerCode);
    void stopStatus( String bannerCode);
    void delBannerPic( String bannerCode);
    List<BannerEntity> selectBannerPic(BannerEntity bannerEntity);
}
