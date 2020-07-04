package com.ruoyi.project.zxsd.sys.domain;

import com.ruoyi.framework.web.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 用户对象 sys_menu
 *
 * @author jiaab
 * @date 2020-05-15
 */
@Getter
@Setter
@ApiModel(value = "菜单对象", description = "菜单对象")
public class SystemMenuEntity extends BaseEntity{

    /**
     * 序列化id
     */
    private static final long serialVersionUID = 1L;


    /**
     * 菜单编码
     */
//    @ApiModelProperty(value = "菜单编码",hidden = true)
    @ApiModelProperty(value = "菜单编码")
    private String menuCode;

    /**
     * 菜单名称
     */
    @ApiModelProperty(value = "菜单名称")
    private String menuName;

    /**
     * 菜单路径
     */
    @ApiModelProperty(value = "菜单路径")
    private String menuUrl;
    /**
     * 父编码
     */
    @ApiModelProperty(value = "父编码")
    private String parentMenuCode;

    /**
     * 类型(1代表菜单2代表目录)
     */
    @ApiModelProperty(value = "类型")
    private int type;
    /**
     * 菜单层次（项目根节点是0，下面一次+1）
     */
    @ApiModelProperty(value = "菜单层次",hidden = true)
    private int level;
    /**
     * id
     */
    @ApiModelProperty(value = "id",hidden = true)
    private String id;

    /**
     * 序号
     */
    @ApiModelProperty(value = "序号",hidden = true)
    private int sortNo;
    /**
     * 角色编码
     */
    @ApiModelProperty(value = "角色编码")
    private String authCode;

    @ApiModelProperty(value = "子菜单集合",hidden = true)
    private List<SystemMenuEntity> childMenu;

    /**
     * 图标
     */
    @ApiModelProperty(value = "图标")
    private String icon;
}
