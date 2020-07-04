package com.ruoyi.project.storage.mapper;

import com.ruoyi.project.storage.domain.BoxInfo;
import com.ruoyi.project.storage.domain.vo.BoxInfoSelectVO;
import com.ruoyi.project.storage.domain.vo.BoxInfoVO;

import java.util.List;
import java.util.Map;

/**
 * 箱子信息Mapper接口
 *
 * @author wangdong
 * @date 2020-05-06
 */
public interface BoxInfoMapper {

    /**
     * 查询箱子信息列表
     *
     * @param boxInfoVO 箱子信息显示对象
     * @return 箱子信息集合
     */
    List<BoxInfoVO> selectBoxInfoList(BoxInfoVO boxInfoVO);

    /**
     * 新增箱子
     *
     * @param boxInfo 箱子
     * @return 条数
     */
    int insertBoxInfo(BoxInfo boxInfo);

    /**
     * 批量删除箱子
     *
     * @param map ids:需要删除的数据ID
     *            user:操作人
     *            time:操作时间
     * @return 结果
     */
    int deleteBoxInfoByIds(Map<String, Object> map);

    /**
     * 通过箱子信息id查询对应未完成订单数量
     *
     * @param ids 需要删除的箱子信息ID
     * @return 未完成订单数量
     */
    int selectUnfinishedOrderCountByIds(Long[] ids);

    /**
     * 通过箱子规格获取当前可用箱子列表
     *
     * @param boxStandard 箱子规格
     * @return 当前可用箱子列表
     */
    List<BoxInfoSelectVO> selectAvailableBoxListByBoxStandard(String boxStandard);

    /**
     * 批量新增箱子信息
     *
     * @param list 箱子信息列表
     * @return 结果
     */
    int batchInsertBoxInfo(List<BoxInfo> list);

}
