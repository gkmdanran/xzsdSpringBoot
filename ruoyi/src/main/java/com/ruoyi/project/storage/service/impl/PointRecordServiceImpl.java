package com.ruoyi.project.storage.service.impl;

import com.ruoyi.project.common.enums.RecordPointWay;
import com.ruoyi.project.common.util.ParameterUtil;
import com.ruoyi.project.storage.domain.PointRecord;
import com.ruoyi.project.storage.mapper.PointRecordMapper;
import com.ruoyi.project.storage.service.IPointRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 积分记录Service业务层处理
 *
 * @author wangdong
 * @date 2020-05-04
 */
@Service
public class PointRecordServiceImpl implements IPointRecordService {

    /**
     * 积分记录Mapper
     */
    private final PointRecordMapper pointRecordMapper;

    /**
     * 构造方法注入
     *
     * @param pointRecordMapper 积分记录Mapper
     */
    @Autowired
    public PointRecordServiceImpl(PointRecordMapper pointRecordMapper) {
        this.pointRecordMapper = pointRecordMapper;
    }

    /**
     * 查询积分记录列表
     *
     * @param userId 用户id
     * @return 积分记录
     */
    @Override
    public List<PointRecord> selectPointRecordList(Long userId) {
        // 实例化积分记录对象
        PointRecord pointRecord = new PointRecord();
        // 设置用户id
        pointRecord.setUserId(userId);
        // 返回积分记录集合
        return pointRecordMapper.selectPointRecordList(pointRecord);
    }

    /**
     * 新增积分记录
     *
     * @param recordPointWay  方式（1：注册赠送；2：活动获得；3：积分使用）
     * @param userId          用户id
     * @param points          积分（积分获得为正数，积分使用未负数）
     * @param advertisementId 广告id
     * @param orderId         订单id
     * @return 条数
     */
    @Override
    public int insertPointRecord(RecordPointWay recordPointWay, Long userId, Long points, Long advertisementId, Long orderId) {
        // 实例化对象
        PointRecord pointRecord = new PointRecord();
        // 如果为手机注册赠送
        if (RecordPointWay.REGIST.equals(recordPointWay)) {
            // 设置注册基础字段
            ParameterUtil.setRegistEntity(pointRecord);
        } else {
            // 设置创建基础字段
            ParameterUtil.setCreateEntity(pointRecord);
        }
        // 设置用户id
        pointRecord.setUserId(userId);
        // 设置方式
        pointRecord.setWay(recordPointWay.getCode());
        // 设置积分
        pointRecord.setPoints(points);
        // 设置广告id
        pointRecord.setAdvertisementId(advertisementId);
        // 设置订单id
        pointRecord.setOrderId(orderId);
        // 返回插入积分记录条数
        return pointRecordMapper.insertPointRecord(pointRecord);
    }

}
