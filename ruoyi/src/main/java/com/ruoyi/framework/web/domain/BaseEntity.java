package com.ruoyi.framework.web.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import springfox.documentation.annotations.ApiIgnore;

/**
 * Entity基类
 *
 * @author wangdong
 */
@Getter
@Setter
@ApiModel(value = "Entity基类", description = "Entity基类")
public class BaseEntity implements Serializable {

    /**
     * 序列化id
     */
    private static final long serialVersionUID = 1L;

    /**
     * 搜索值
     */
    @ApiModelProperty(value = "搜索值",hidden = true)
    private String searchValue;

    /**
     * 创建者
     */
    @ApiModelProperty(value = "创建者",hidden = true)
    private String createBy;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间",hidden = true)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 更新者
     */
    @ApiModelProperty(value = "更新者",hidden = true)
    private String updateBy;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间",hidden = true)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /**
     * 版本号
     */
    @ApiModelProperty(value = "版本号",hidden = true)
    private Long version;

    /**
     * 删除标记（0：未删除；2：已删除）
     */
    @ApiModelProperty(value = "删除标记（0：未删除；2：已删除）",hidden = true)
    private String delFlag;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    /**
     * 数据权限
     */
    @ApiModelProperty(value = "数据权限",hidden = true)
    private String dataScope;

    /**
     * 开始时间
     */
    @ApiModelProperty(value = "开始时间",hidden = true)
    @JsonIgnore
    private String beginTime;

    /**
     * 结束时间
     */
    @ApiModelProperty(value = "结束时间",hidden = true)
    @JsonIgnore
    private String endTime;

    /**
     * 请求参数
     */
    @ApiModelProperty(value = "请求参数",hidden = true)
    private Map<String, Object> params;

    /**
     * 请求参数get方法
     *
     * @return 请求参数
     */
    public Map<String, Object> getParams() {
        if (params == null) {
            params = new HashMap<>(16);
        }
        return params;
    }

}
