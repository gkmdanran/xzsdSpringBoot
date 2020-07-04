package com.ruoyi.project.zxsd.sys.domain;

import com.ruoyi.framework.web.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author gkm
 * @version 1.0
 * @date 2020/5/22 9:49
 */
@Getter
@Setter
@ApiModel(value = "轮播图", description = "轮播图")
public class BannerEntity extends BaseEntity {
    /**
     * 序列化id
     */
    private static final long serialVersionUID = 1L;

    /**
     * 图片路径
     */
    @ApiModelProperty(value = "图片路径")
    private String ImageUrl;

    /**
     * id
     */
    @ApiModelProperty(value = "id")
    private String id;

    /**
     * 状态0关闭 1启用 3全部
     */
    @ApiModelProperty(value = "状态")
    private Integer status;

    /**
     * 开始时间
     */
    @ApiModelProperty(value = "开始时间")
    private Date startTime;

    /**
     * 结束时间
     */
    @ApiModelProperty(value = "结束时间")
    private Date overTime;

    /**
     * 序号
     */
    @ApiModelProperty(value = "序号")
    private int sortNo;

    /**
     * 轮播图码
     */
    @ApiModelProperty(value = "轮播图码")
    private String bannerCode;

    /**
     * 作废标记
     */
    @ApiModelProperty(value = "作废标记")
    private int isDeleted;
}
