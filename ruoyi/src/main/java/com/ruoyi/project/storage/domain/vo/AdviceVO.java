package com.ruoyi.project.storage.domain.vo;

import com.ruoyi.project.storage.domain.Advice;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 意见显示对象
 *
 * @author wangdong
 * @date 2020-04-30
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "意见显示对象", description = "意见显示对象")
public class AdviceVO extends Advice {

    /**
     * 序列化id
     */
    private static final long serialVersionUID = 1L;

    /**
     * 客户姓名
     */
    @ApiModelProperty(value = "客户姓名")
    private String nickName;

}
