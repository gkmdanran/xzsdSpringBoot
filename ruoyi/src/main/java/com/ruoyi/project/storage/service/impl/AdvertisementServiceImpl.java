package com.ruoyi.project.storage.service.impl;

import com.ruoyi.common.constant.HttpStatus;
import com.ruoyi.common.exception.CustomException;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.project.common.enums.RecordPointWay;
import com.ruoyi.project.common.util.ParameterUtil;
import com.ruoyi.project.storage.domain.Advertisement;
import com.ruoyi.project.storage.domain.User;
import com.ruoyi.project.storage.mapper.AdvertisementMapper;
import com.ruoyi.project.storage.mapper.UserMapper;
import com.ruoyi.project.storage.service.IAdvertisementService;
import com.ruoyi.project.storage.service.IPointRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 广告Service业务层处理
 *
 * @author wangdong
 * @date 2020.04.28
 */
@Service
@Slf4j
public class AdvertisementServiceImpl implements IAdvertisementService {

    /**
     * 广告Mapper
     */
    private final AdvertisementMapper advertisementMapper;

    /**
     * 用户Mapper
     */
    private final UserMapper userMapper;

    /**
     * 积分记录Service
     */
    private final IPointRecordService pointRecordService;

    /**
     * 构造方法注入
     *
     * @param advertisementMapper 广告Mapper
     * @param userMapper          用户Mapper
     * @param pointRecordService  积分记录Service
     */
    @Autowired
    public AdvertisementServiceImpl(AdvertisementMapper advertisementMapper, UserMapper userMapper, IPointRecordService pointRecordService) {
        this.advertisementMapper = advertisementMapper;
        this.userMapper = userMapper;
        this.pointRecordService = pointRecordService;
    }

    /**
     * 查询广告列表
     *
     * @param advertisement 广告
     * @return 广告集合
     */
    @Override
    public List<Advertisement> selectAdvertisementList(Advertisement advertisement) {
        // 返回广告列表数据
        return advertisementMapper.selectAdvertisementList(advertisement);
    }

    /**
     * 新增广告
     *
     * @param advertisement 广告
     * @return 条数
     */
    @Override
    public int insertAdvertisement(Advertisement advertisement) {
        // 设置创建基础字段
        ParameterUtil.setCreateEntity(advertisement);
        // 设置启用
        advertisement.setIsEnable(0);
        // 返回插入广告条数
        return advertisementMapper.insertAdvertisement(advertisement);
    }

    /**
     * 修改广告
     *
     * @param advertisement 广告
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateAdvertisement(Advertisement advertisement) {
        ParameterUtil.setUpdateEntity(advertisement);
        // 修改条数
        int count = advertisementMapper.updateAdvertisement(advertisement);
        // 乐观锁判断
        if (count == 0) {
            log.error("AdvertisementServiceImpl.updateAdvertisement failed: 乐观锁");
            // 抛出异常标记乐观锁
            throw new CustomException("广告【" + advertisement.getTitle() + "】已被他人率先修改，请刷新后重试", HttpStatus.CONFLICT);
        }
        // 返回修改条数
        return count;
    }

    /**
     * 批量删除广告
     *
     * @param ids 需要删除的广告ID
     * @return 结果
     */
    @Override
    public int deleteAdvertisementByIds(Long[] ids) {
        // 返回修改条数
        return advertisementMapper.deleteAdvertisementByIds(ParameterUtil.getBatchUpdateMapByIds(ids));
    }

    /**
     * 批量启用/停用广告
     *
     * @param operate 操作类型（"enable"：启用；"disable"：停用）
     * @param ids     需要启用/停用的广告ID数组
     * @return 结果
     */
    @Override
    public int operateAdvertisementByIds(String operate, Long[] ids) {
        // 返回修改条数
        return advertisementMapper.operateAdvertisementByIds(ParameterUtil.getBatchUpdateMapByOperateIds(operate, ids));
    }

    /**
     * 广告积分获取
     *
     * @param advertisement 广告对象
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public long getPointsForUser(Advertisement advertisement) {
        // 实例化用户
        User user = new User();
        // 设置更新基础字段
        ParameterUtil.setUpdateEntity(user);
        // 设置积分（追加积分）
        user.setCurrentPoints(advertisement.getPoints());
        // 设置用户id
        user.setUserId(SecurityUtils.getUserId());
        // 操作用户积分并返回操作条数
        int userOperateCount = userMapper.operateUserPoints(user);
        // 判断更新条数
        if (userOperateCount > 0) {
            // 返回插入积分记录表结果
            return pointRecordService.insertPointRecord(RecordPointWay.ADVERTISEMENT, user.getUserId(), advertisement.getPoints(), advertisement.getId(), null);
        } else {
            // 手动抛异常
            throw new CustomException("广告积分获取失败，请联系管理员", HttpStatus.ERROR);
        }
    }

}
