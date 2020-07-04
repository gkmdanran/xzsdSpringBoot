package com.ruoyi.project.storage.domain;

import com.ruoyi.framework.web.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 广告对象 t_advertisement
 *
 * @author wangdong
 * @date 2020.04.28
 */
@Getter
@Setter
@ApiModel(value = "广告对象", description = "广告对象")
public class Advertisement extends BaseEntity {

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
     * 标题
     */
    @ApiModelProperty(value = "标题")
    private String title;

    /**
     * 内容
     */
    @ApiModelProperty(value = "内容")
    private String content;

    /**
     * 图片路径
     */
    @ApiModelProperty(value = "图片路径")
    private String imgUrl;

    /**
     * 可获积分
     */
    @ApiModelProperty(value = "可获积分")
    private Long points;

    /**
     * 是否启用（0：启用；1：停用）
     */
    @ApiModelProperty(value = "是否启用（0：启用；1：停用）")
    private Integer isEnable;

    /**
     * 序号
     */
    @ApiModelProperty(value = "序号")
    private Long sortNo;

}
