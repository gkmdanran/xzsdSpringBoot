package com.ruoyi.project.storage.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 箱子信息选择对象
 *
 * @author wangdong
 * @date 2020-05-09
 */
@Data
@ApiModel(value = "箱子信息选择对象", description = "箱子信息选择对象")
public class BoxInfoSelectVO {

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
     * 版本号
     */
    @ApiModelProperty(value = "版本号")
    private Long version;

}
