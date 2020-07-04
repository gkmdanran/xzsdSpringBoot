package com.ruoyi.project.storage.domain.vo;

import com.ruoyi.project.storage.domain.BoxInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 箱子信息显示对象
 *
 * @author wangdong
 * @date 2020-05-09
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "箱子信息显示对象", description = "箱子信息显示对象")
public class BoxInfoVO extends BoxInfo {

    /**
     * 序列化id
     */
    private static final long serialVersionUID = 1L;

    /**
     * 使用人姓名
     */
    @ApiModelProperty(value = "客户姓名")
    private String usedByName;

}
