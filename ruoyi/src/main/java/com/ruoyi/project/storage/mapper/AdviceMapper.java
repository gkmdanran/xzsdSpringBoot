package com.ruoyi.project.storage.mapper;

import com.ruoyi.project.storage.domain.Advice;
import com.ruoyi.project.storage.domain.vo.AdviceVO;

import java.util.List;

/**
 * 意见Mapper接口
 *
 * @author wangdong
 * @date 2020.05.01
 */
public interface AdviceMapper {

    /**
     * 查询意见列表
     *
     * @param adviceVO 意见
     * @return 意见集合
     */
    List<AdviceVO> selectAdviceList(AdviceVO adviceVO);

    /**
     * 新增意见
     *
     * @param advice 意见
     * @return 条数
     */
    int insertAdvice(Advice advice);

}
