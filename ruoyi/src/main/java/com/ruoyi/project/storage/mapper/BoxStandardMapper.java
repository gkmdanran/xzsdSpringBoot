package com.ruoyi.project.storage.mapper;

import com.ruoyi.project.storage.domain.BoxStandard;
import com.ruoyi.project.storage.domain.vo.BoxStandardSelectVO;
import com.ruoyi.project.storage.domain.vo.BoxStandardVO;

import java.util.List;
import java.util.Map;

/**
 * 箱子规格Mapper接口
 *
 * @author wangdong
 * @date 2020-05-07
 */
public interface BoxStandardMapper {

    /**
     * 查询箱子规格列表
     *
     * @param boxStandardVO 箱子规格展示对象
     * @return 箱子规格集合
     */
    List<BoxStandardVO> selectBoxStandardList(BoxStandardVO boxStandardVO);

    /**
     * 新增箱子规格
     *
     * @param boxStandard 箱子规格
     * @return 结果
     */
    int insertBoxStandard(BoxStandard boxStandard);

    /**
     * 批量删除箱子规格
     *
     * @param map ids:需要删除的数据ID
     *            user:操作人
     *            time:操作时间
     * @return 结果
     */
    int deleteBoxStandardByIds(Map<String, Object> map);

    /**
     * 查询箱子规格下拉列表
     *
     * @return 箱子规格下拉集合
     */
    List<BoxStandardSelectVO> selectBoxStandardSelectList();

    /**
     * 查询有效箱子规格列表
     *
     * @return 有效箱子规格集合
     */
    List<BoxStandardSelectVO> selectBoxStandardEffectiveList();

    /**
     * 通过箱子规格查询箱子规格信息
     *
     * @param boxStandard 箱子规格
     * @return 箱子规格信息
     */
    BoxStandardSelectVO selectBoxStandardByStandard(BoxStandard boxStandard);

    /**
     * 通过箱子id查询规格下有无箱子
     *
     * @param ids 需要删除的箱子规格ID
     * @return 箱子数量
     */
    int selectBoxInfoCountByIds(Long[] ids);

}
