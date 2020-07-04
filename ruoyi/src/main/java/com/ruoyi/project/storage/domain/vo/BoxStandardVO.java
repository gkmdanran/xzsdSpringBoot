package com.ruoyi.project.storage.domain.vo;

import com.ruoyi.project.storage.domain.BoxStandard;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 箱子规格显示对象
 *
 * @author wangdong
 * @date 2020-05-06
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "箱子规格显示对象", description = "箱子规格显示对象")
public class BoxStandardVO extends BoxStandard {

    /**
     * 序列化id
     */
    private static final long serialVersionUID = 1L;

    /**
     * 总数量
     */
    @ApiModelProperty(value = "总数量")
    private Long totalNumber;

    /**
     * 已使用数量
     */
    @ApiModelProperty(value = "已使用数量")
    private Long usedNumber;

    /**
     * 库存数量
     */
    @ApiModelProperty(value = "库存数量")
    private Long inventoryNumber;

    /**
     * 使用比例
     */
    @ApiModelProperty(value = "使用比例")
    private String useRatio;

}
