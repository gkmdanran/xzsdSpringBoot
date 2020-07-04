package com.ruoyi.project.storage.domain;

import com.ruoyi.framework.web.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * 箱子规格对象 t_box_standard
 *
 * @author wangdong
 * @date 2020-05-06
 */
@Getter
@Setter
@ApiModel(value = "箱子规格对象", description = "箱子规格对象")
public class BoxStandard extends BaseEntity {

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
    @NotNull(message = "箱子规格不能为空")
    @ApiModelProperty(value = "箱子规格（如20*20*20）")
    private String boxStandard;

    /**
     * 箱子积分单价（每月积分单价）
     */
    @NotNull(message = "箱子积分单价不能为空")
    @ApiModelProperty(value = "箱子积分单价（每月积分单价）")
    private Long boxUnitPrice;

    /**
     * 序号
     */
    @ApiModelProperty(value = "id")
    private Long sortNo;

}
