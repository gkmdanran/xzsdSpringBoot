package com.ruoyi.project.zxsd.sys.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ruoyi.framework.web.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author gkm
 * @version 1.0
 * @date 2020/5/19 10:30
 */
@Getter
@Setter
@ApiModel(value = "省", description = "省")

public class ProvinceEntity extends BaseEntity{

    /**
     * 序列化id
     */
    private static final long serialVersionUID = 1L;
    /**
     * 省编码
     */
    @ApiModelProperty(value = "省编码")
    private String provinceCode;
    /**
     * 省名称
     */
    @ApiModelProperty(value = "省名称")
    private String provinceName;
}
