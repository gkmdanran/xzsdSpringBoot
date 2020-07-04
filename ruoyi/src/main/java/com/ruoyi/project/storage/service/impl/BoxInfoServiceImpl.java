package com.ruoyi.project.storage.service.impl;

import com.ruoyi.common.exception.CustomException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.project.common.util.ParameterUtil;
import com.ruoyi.project.common.util.SeqGeneratorUtil;
import com.ruoyi.project.storage.domain.BoxInfo;
import com.ruoyi.project.storage.domain.vo.BoxInfoVO;
import com.ruoyi.project.storage.mapper.BoxInfoMapper;
import com.ruoyi.project.storage.service.IBoxInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 箱子信息Service业务层处理
 *
 * @author wangdong
 * @date 2020-05-09
 */
@Service
public class BoxInfoServiceImpl implements IBoxInfoService {

    /**
     * 箱子信息Mapper
     */
    private final BoxInfoMapper boxInfoMapper;

    /**
     * 构造方法注入
     *
     * @param boxInfoMapper 箱子信息Mapper
     */
    @Autowired
    public BoxInfoServiceImpl(BoxInfoMapper boxInfoMapper) {
        this.boxInfoMapper = boxInfoMapper;
    }

    /**
     * 查询箱子信息列表
     *
     * @param boxInfoVO 箱子信息展示对象
     * @return 箱子信息
     */
    @Override
    public List<BoxInfoVO> selectBoxInfoList(BoxInfoVO boxInfoVO) {
        // 返回箱子信息列表
        return boxInfoMapper.selectBoxInfoList(boxInfoVO);
    }

    /**
     * 新增箱子信息
     *
     * @param boxInfo 箱子信息显示对象
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertBoxInfo(BoxInfo boxInfo) {
        // 设置创建基础字段
        ParameterUtil.setCreateEntity(boxInfo);
        // 设置箱子编号（年月日+6位序列）（年月日+6位序列为固定工具类，故Long.valueOf未判空）
        boxInfo.setBoxCode(Long.valueOf(SeqGeneratorUtil.seqGenerator(DateUtils.getNowDateStr(), 6)));
        // 设置未使用
        boxInfo.setIsUsed(0);
        // 返回插入箱子信息条数
        return boxInfoMapper.insertBoxInfo(boxInfo);
    }

    /**
     * 批量删除箱子信息
     *
     * @param ids 需要删除的箱子信息ID
     * @return 结果
     */
    @Override
    public int deleteBoxInfoByIds(Long[] ids) {
        // 通过箱子信息id查询对应未完成订单数量，校验
        if (boxInfoMapper.selectUnfinishedOrderCountByIds(ids) > 0) {
            // 若箱子信息id仍对应订单，手动抛异常
            throw new CustomException("删除箱子信息失败，仍被订单使用");
        }
        // 返回修改条数
        return boxInfoMapper.deleteBoxInfoByIds(ParameterUtil.getBatchUpdateMapByIds(ids));
    }

}
