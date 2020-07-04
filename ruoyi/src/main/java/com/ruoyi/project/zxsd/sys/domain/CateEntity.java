package com.ruoyi.project.zxsd.sys.domain;

import com.ruoyi.framework.web.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author gkm
 * @version 1.0
 * @date 2020/5/23 13:40
 */
@Getter
@Setter
@ApiModel(value = "商品分类", description = "商品分类")
public class CateEntity extends BaseEntity{
    /**
     * 序列化id
     */
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ApiModelProperty(value = "id")
    private String id;

    /**
     * 商品分类编码
     */
    @ApiModelProperty(value = "商品分类编码")
    private String cateCode;

    /**
     * 商品分类名称
     */
    @ApiModelProperty(value = "商品分类名称")
    private String cateName;

    /**
     * 商品父编码
     */
    @ApiModelProperty(value = "商品父编码")
    private String cateCodeParent;

    /**
     * 商品分类备注
     */
    @ApiModelProperty(value = "商品分类备注")
    private String remark;

    /**
     * 层级
     */
    @ApiModelProperty(value = "层级")
    private int level;

    /**
     * 是否第一层级
     */
    @ApiModelProperty(value = "是否第一层级")
    private int isParent;

    /**
     * 序号
     */
    @ApiModelProperty(value = "序号")
    private int sortNo;

    @ApiModelProperty(value = "子分类集合",hidden = true)
    private List<CateEntity> childCate;
}
