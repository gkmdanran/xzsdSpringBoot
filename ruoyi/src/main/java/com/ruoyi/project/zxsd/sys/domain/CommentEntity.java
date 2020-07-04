package com.ruoyi.project.zxsd.sys.domain;

import com.ruoyi.framework.web.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author gkm
 * @version 1.0
 * @date 2020/6/7 12:40
 */
@Getter
@Setter
@ApiModel(value = "评论", description = "评论")
public class CommentEntity extends BaseEntity{
    /**
     * 序列化id
     */
    private static final long serialVersionUID = 1L;
    /**
     * 客户账号
     */
    @ApiModelProperty(value = "客户账号")
    private String userName;

    /**
     * 客户id
     */
    @ApiModelProperty(value = "客户id")
    private String userCode;
    /**
     * id
     */
    @ApiModelProperty(value = "评论id")
    private String id;
    /**
     * 详情订单编码
     */
    @ApiModelProperty(value = "详情订单编码")
    private String orderDetailCode;
    /**
     * 商品编号
     */
    @ApiModelProperty(value = "商品编号")
    private String skuNo;

    /**
     * 评论内容
     */
    @ApiModelProperty(value = "评论内容")
    private String commentTxt;

    /**
     * 评论星级
     */
    @ApiModelProperty(value = "评论星级")
    private int star;
}
