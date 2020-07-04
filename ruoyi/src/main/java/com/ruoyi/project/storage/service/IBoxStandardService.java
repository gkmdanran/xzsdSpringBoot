package com.ruoyi.project.storage.service;

import com.ruoyi.project.storage.domain.vo.BoxStandardSelectVO;
import com.ruoyi.project.storage.domain.vo.BoxStandardVO;

import java.util.List;

/**
 * 箱子规格Service接口
 *
 * @author wangdong
 * @date 2020-05-07
 */
public interface IBoxStandardService {

    /**
     * 查询箱子规格列表
     *
     * @param boxStandardVO 箱子规格显示对象
     * @return 箱子规格集合
     */
    List<BoxStandardVO> selectBoxStandardList(BoxStandardVO boxStandardVO);

    /**
     * 新增箱子规格
     *
     * @param boxStandardVO 箱子规格显示对象
     * @return 结果
     */
    int insertBoxStandard(BoxStandardVO boxStandardVO);

    /**
     * 批量删除箱子规格
     *
     * @param ids 需要删除的箱子规格ID
     * @return 结果
     */
    int deleteBoxStandardByIds(Long[] ids);

    /**
     * 查询箱子规格下拉列表
     *
     * @return 箱子规格下拉集合
     */
    List<BoxStandardSelectVO> selectBoxStandardSelectList();

    /**
     * 查询有效箱子规格列表（非分页）
     *
     * @return 有效箱子规格集合
     */
    List<BoxStandardSelectVO> selectBoxStandardEffectiveList();

}
