package com.ruoyi.project.zxsd.sys.service.impl;

import com.ruoyi.common.utils.IdUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.framework.security.LoginUser;
import com.ruoyi.project.zxsd.sys.domain.BannerEntity;
import com.ruoyi.project.zxsd.sys.domain.SystemUserEntity;
import com.ruoyi.project.zxsd.sys.mapper.BannerMapper;
import com.ruoyi.project.zxsd.sys.mapper.SystemUserMapper;
import com.ruoyi.project.zxsd.sys.service.IBannerService;
import com.ruoyi.project.zxsd.sys.util.SystemCodeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author gkm
 * @version 1.0
 * @date 2020/5/22 9:58
 */
@Service
@Slf4j
public class BannerServiceImpl implements IBannerService{
    @Autowired
    private final BannerMapper bannerMapper;
    @Autowired
    private final SystemUserMapper sysUserMapper;
    public BannerServiceImpl(BannerMapper bannerMapper,SystemUserMapper sysUserMapper) {
        this.bannerMapper=bannerMapper;
        this.sysUserMapper=sysUserMapper;
    }

    @Override
    @Transactional
    public void insertBannerPic(BannerEntity bannerEntity) {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        SystemUserEntity systemUserEntity=loginUser.getSystemUserEntity();
        bannerEntity.setCreateBy(systemUserEntity.getUserName());
        bannerEntity.setUpdateBy(systemUserEntity.getUserName());
        bannerEntity.setId(IdUtils.fastSimpleUUID());
        bannerEntity.setBannerCode(SystemCodeUtil.getUserCode());
        int sortNo = 0;
        try{
            sortNo = sysUserMapper.getMaxSortNo("t_banner_info");
        }catch (Exception e){
            log.error("第一条数据序号异常可不用处理",e);
        }
        bannerEntity.setSortNo(sortNo+1);
        bannerMapper.insertBannerPic(bannerEntity);
    }

    @Override
    public void startStatus(String bannerCode) {
        bannerMapper.startStatus(bannerCode);
    }

    @Override
    public void stopStatus(String bannerCode) {
        bannerMapper.stopStatus(bannerCode);
    }

    @Override
    public void delBannerPic(String bannerCode) {
        bannerMapper.delBannerPic(bannerCode);
    }

    @Override
    public List<BannerEntity> selectBannerPic(BannerEntity bannerEntity) {
        return bannerMapper.selectBannerPic(bannerEntity);
    }
}
