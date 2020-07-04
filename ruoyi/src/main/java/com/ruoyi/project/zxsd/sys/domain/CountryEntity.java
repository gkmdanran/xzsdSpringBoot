package com.ruoyi.project.zxsd.sys.domain;

import com.ruoyi.framework.web.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author gkm
 * @version 1.0
 * @date 2020/5/19 11:08
 */
@Getter
@Setter
@ApiModel(value = "市区", description = "市区")

public class CountryEntity extends BaseEntity{
    /**
     * 序列化id
     */
    private static final long serialVersionUID = 1L;
    /**
     * 所在省编码
     */
    @ApiModelProperty(value = "所在省编码")
    private String provinceCode;
    /**
     * 市区名称
     */
    @ApiModelProperty(value = "市区名称")
    private String countyName;

    /**
     * 市区编码
     */
    @ApiModelProperty(value = "市区名称")
    private String countyCode;
}
