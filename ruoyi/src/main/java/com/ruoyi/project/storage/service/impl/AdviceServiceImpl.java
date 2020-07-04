package com.ruoyi.project.storage.service.impl;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.project.common.util.ParameterUtil;
import com.ruoyi.project.storage.domain.Advice;
import com.ruoyi.project.storage.domain.vo.AdviceVO;
import com.ruoyi.project.storage.mapper.AdviceMapper;
import com.ruoyi.project.storage.service.IAdviceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 广告Service业务层处理
 *
 * @author wangdong
 * @date 2020.05.01
 */
@Service
@Slf4j
public class AdviceServiceImpl implements IAdviceService {

    /**
     * 意见Mapper
     */
    private final AdviceMapper adviceMapper;

    /**
     * 构造方法注入
     *
     * @param adviceMapper 意见Mapper
     */
    @Autowired
    public AdviceServiceImpl(AdviceMapper adviceMapper) {
        this.adviceMapper = adviceMapper;
    }

    /**
     * 查询意见列表
     *
     * @param adviceVO 意见显示对象
     * @return 意见
     */
    @Override
    public List<AdviceVO> selectAdviceList(AdviceVO adviceVO) {
        // 返回意见列表数据
        return adviceMapper.selectAdviceList(adviceVO);
    }

    /**
     * 新增意见
     *
     * @param advice 意见
     * @return 条数
     */
    @Override
    public int insertAdvice(Advice advice) {
        // 设置创建基础字段
        ParameterUtil.setCreateEntity(advice);
        // 设置用户id
        advice.setUserId(SecurityUtils.getUserId());
        // 返回插入意见建议条数
        return adviceMapper.insertAdvice(advice);
    }

}
