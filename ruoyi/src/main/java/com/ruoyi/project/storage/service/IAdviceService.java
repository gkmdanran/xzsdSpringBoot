package com.ruoyi.project.storage.service;

import com.ruoyi.project.storage.domain.Advice;
import com.ruoyi.project.storage.domain.vo.AdviceVO;

import java.util.List;

/**
 * 意见Service接口
 *
 * @author wangdong
 * @date 2020.05.01
 */
public interface IAdviceService {

    /**
     * 查询意见列表
     *
     * @param adviceVO 意见显示对象
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
