package com.ruoyi.project.storage.service.impl;

import com.ruoyi.common.exception.CustomException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.project.common.util.ParameterUtil;
import com.ruoyi.project.common.util.SeqGeneratorUtil;
import com.ruoyi.project.storage.domain.BoxInfo;
import com.ruoyi.project.storage.domain.vo.BoxStandardSelectVO;
import com.ruoyi.project.storage.domain.vo.BoxStandardVO;
import com.ruoyi.project.storage.mapper.BoxInfoMapper;
import com.ruoyi.project.storage.mapper.BoxStandardMapper;
import com.ruoyi.project.storage.service.IBoxStandardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 箱子规格Service业务层处理
 *
 * @author wangdong
 * @date 2020-05-07
 */
@Service
public class BoxStandardServiceImpl implements IBoxStandardService {

    /**
     * 箱子规格Mapper
     */
    private final BoxStandardMapper boxStandardMapper;

    /**
     * 箱子信息Mapper
     */
    private final BoxInfoMapper boxInfoMapper;

    /**
     * 构造方法注入
     *
     * @param boxStandardMapper 箱子规格Mapper
     * @param boxInfoMapper     箱子信息Mapper
     */
    @Autowired
    public BoxStandardServiceImpl(BoxStandardMapper boxStandardMapper, BoxInfoMapper boxInfoMapper) {
        this.boxStandardMapper = boxStandardMapper;
        this.boxInfoMapper = boxInfoMapper;
    }

    /**
     * 查询箱子规格列表
     *
     * @param boxStandardVO 箱子规格展示对象
     * @return 箱子规格
     */
    @Override
    public List<BoxStandardVO> selectBoxStandardList(BoxStandardVO boxStandardVO) {
        // 返回箱子规格列表
        return boxStandardMapper.selectBoxStandardList(boxStandardVO);
    }

    /**
     * 新增箱子规格
     *
     * @param boxStandardVO 箱子规格显示对象
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertBoxStandard(BoxStandardVO boxStandardVO) {
        // 通过箱子规格查询箱子规格信息
        BoxStandardSelectVO boxStandardSelectVO = boxStandardMapper.selectBoxStandardByStandard(boxStandardVO);
        // 判断查询到的箱子规格信息
        if (boxStandardSelectVO != null) {
            // (BoxStandard的boxUnitPrice加了@NotBlank，所以一定不为空)
            if (!boxStandardVO.getBoxUnitPrice().equals(boxStandardSelectVO.getBoxUnitPrice())) {
                // 若积分单价不同，则手动抛异常
                throw new CustomException("当前规格【" + boxStandardVO.getBoxStandard() + "】已存在，对应所需积分为【" + boxStandardSelectVO.getBoxUnitPrice() + "】，请重新填写");
            }
            // 判断传递进来的库存数量是否小于等于0
            if (boxStandardVO.getInventoryNumber() <= 0L) {
                // 不插入箱子信息表，也不插入箱子规格表，手动抛异常
                throw new CustomException("当前规格【" + boxStandardVO.getBoxStandard() + "】已存在，本次无新增库存，无需重新新增");
            }
        } else {
            // 设置创建基础字段
            ParameterUtil.setCreateEntity(boxStandardVO);
            // 新增箱子规格
            int insertCount = boxStandardMapper.insertBoxStandard(boxStandardVO);
            // 判断插入箱子规格结果
            if (insertCount <= 0) {
                // 插入失败，抛异常
                throw new CustomException("箱子规格插入失败，请联系管理员");
            }
            // 判断传递进来的库存数量是否小于等于0
            if (boxStandardVO.getInventoryNumber() <= 0L) {
                // 直接返回插入条数
                return insertCount;
            }
        }
        // 返回批量插入箱子信息条数
        return boxInfoMapper.batchInsertBoxInfo(initBoxInfoList(boxStandardVO));
    }

    /**
     * 根据箱子规格显示对象初始化箱子信息List
     *
     * @param boxStandardVO 箱子规格显示对象
     * @return 箱子信息List
     */
    private List<BoxInfo> initBoxInfoList(BoxStandardVO boxStandardVO) {
        // 获取当前时间
        Date date = DateUtils.getNowDate();
        // 获取当前登录人用户名
        String userName = SecurityUtils.getUsername();
        // 初始化箱子信息List
        List<BoxInfo> list = new ArrayList<>();
        // 遍历（此处确定库存数量必大于0）
        for (int i = 0; i < boxStandardVO.getInventoryNumber(); i++) {
            // 实例化箱子信息对象
            BoxInfo boxInfo = new BoxInfo();
            // 设置箱子编号（年月日+6位序列）（年月日+6位序列为固定工具类，故Long.valueOf未判空）
            boxInfo.setBoxCode(Long.valueOf(SeqGeneratorUtil.seqGenerator(DateUtils.getNowDateStr(), 6)));
            // 设置箱子规格
            boxInfo.setBoxStandard(boxStandardVO.getBoxStandard());
            // 设置箱子积分单价
            boxInfo.setBoxUnitPrice(boxStandardVO.getBoxUnitPrice());
            // 设置未使用
            boxInfo.setIsUsed(0);
            // 设置备注
            boxInfo.setRemark(boxStandardVO.getRemark());
            // 设置创建人
            boxInfo.setCreateBy(userName);
            // 设置创建时间
            boxInfo.setCreateTime(date);
            // 设置更新人
            boxInfo.setUpdateBy(userName);
            // 设置更新时间
            boxInfo.setUpdateTime(date);
            // 设置版本号
            boxInfo.setVersion(0L);
            // 设置未删除
            boxInfo.setDelFlag("0");
            // 箱子实例添加到箱子信息List中
            list.add(boxInfo);
        }
        // 返回箱子信息List
        return list;
    }

    /**
     * 批量删除箱子规格
     *
     * @param ids 需要删除的箱子规格ID
     * @return 结果
     */
    @Override
    public int deleteBoxStandardByIds(Long[] ids) {
        // 校验
        if (boxStandardMapper.selectBoxInfoCountByIds(ids) > 0) {
            // 若箱子规格id对应规格下仍有箱子，手动抛异常
            throw new CustomException("删除箱子规格失败，规格下仍有箱子");
        }
        // 返回修改条数
        return boxStandardMapper.deleteBoxStandardByIds(ParameterUtil.getBatchUpdateMapByIds(ids));
    }

    /**
     * 查询箱子规格下拉列表
     *
     * @return 箱子规格下拉集合
     */
    @Override
    public List<BoxStandardSelectVO> selectBoxStandardSelectList() {
        return boxStandardMapper.selectBoxStandardSelectList();
    }

    /**
     * 查询有效箱子规格列表（非分页）
     *
     * @return 有效箱子规格集合
     */
    @Override
    public List<BoxStandardSelectVO> selectBoxStandardEffectiveList() {
        return boxStandardMapper.selectBoxStandardEffectiveList();
    }

}
