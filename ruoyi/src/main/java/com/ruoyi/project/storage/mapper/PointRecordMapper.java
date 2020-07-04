package com.ruoyi.project.storage.mapper;

import com.ruoyi.project.storage.domain.PointRecord;

import java.util.List;

/**
 * 积分记录Mapper接口
 *
 * @author wangdong
 * @date 2020-05-04
 */
public interface PointRecordMapper {

    /**
     * 查询积分记录列表
     *
     * @param pointRecord 积分记录
     * @return 积分记录集合
     */
    List<PointRecord> selectPointRecordList(PointRecord pointRecord);

    /**
     * 新增积分记录
     *
     * @param pointRecord 积分记录
     * @return 条数
     */
    int insertPointRecord(PointRecord pointRecord);

}
