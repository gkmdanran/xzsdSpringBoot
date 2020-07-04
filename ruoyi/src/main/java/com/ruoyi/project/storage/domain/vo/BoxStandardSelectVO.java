package com.ruoyi.project.storage.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 箱子规格选择对象
 *
 * @author wangdong
 * @date 2020-05-06
 */
@Data
@ApiModel(value = "箱子规格选择对象", description = "箱子规格选择对象")
public class BoxStandardSelectVO {

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
     * 箱子规格（如20*20*20）
     */
    @ApiModelProperty(value = "箱子规格（如20*20*20）")
    private String boxStandard;

    /**
     * 箱子积分单价（每月积分单价）
     */
    @ApiModelProperty(value = "箱子积分单价（每月积分单价）")
    private Long boxUnitPrice;

}
