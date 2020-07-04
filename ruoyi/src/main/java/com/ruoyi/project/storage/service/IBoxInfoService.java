package com.ruoyi.project.storage.service;

import com.ruoyi.project.storage.domain.BoxInfo;
import com.ruoyi.project.storage.domain.vo.BoxInfoVO;

import java.util.List;

/**
 * 箱子信息Service接口
 *
 * @author wangdong
 * @date 2020-05-09
 */
public interface IBoxInfoService {

    /**
     * 查询箱子信息列表
     *
     * @param boxInfoVO 箱子信息显示对象
     * @return 箱子信息集合
     */
    List<BoxInfoVO> selectBoxInfoList(BoxInfoVO boxInfoVO);

    /**
     * 新增箱子信息
     *
     * @param boxInfo 箱子信息显示对象
     * @return 结果
     */
    int insertBoxInfo(BoxInfo boxInfo);

    /**
     * 批量删除箱子信息
     *
     * @param ids 需要删除的箱子信息ID
     * @return 结果
     */
    int deleteBoxInfoByIds(Long[] ids);

}
