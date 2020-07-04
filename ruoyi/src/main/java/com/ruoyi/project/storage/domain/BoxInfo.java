package com.ruoyi.project.storage.domain;

import com.ruoyi.framework.web.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 箱子信息对象 t_box_info
 *
 * @author wangdong
 * @date 2020-05-06
 */
@Getter
@Setter
@ApiModel(value = "箱子信息对象", description = "箱子信息对象")
public class BoxInfo extends BaseEntity {

    /**
     * 序列化id
     */
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ApiModelProperty(value = "id")
    private Long id;

    /**
     * 箱子编号（年月日+6位序列）
     */
    @ApiModelProperty(value = "箱子编号（年月日+6位序列）")
    private Long boxCode;

    /**
     * 箱子规格（如20*20*20）
     */
    @ApiModelProperty(value = "箱子规格（如20*20*20）")
    private String boxStandard;

    /**
     * 箱子积分单价（每月积分单价）
     */
    @ApiModelProperty(value = "箱子积分单价（每月积分单价）")
    private Long boxUnitPrice;

    /**
     * 使用人
     */
    @ApiModelProperty(value = "使用人")
    private Long usedBy;

    /**
     * 是否被使用（0：未使用；1：已使用）
     */
    @ApiModelProperty(value = "是否被使用（0：未使用；1：已使用）")
    private Integer isUsed;

    /**
     * 序号
     */
    @ApiModelProperty(value = "序号")
    private Long sortNo;

}
