package com.mall.common.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/*
 *   作者：官宣轩
 *   日期：2020-09-21
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@ApiModel(value = "后台用户权限对象", description = "包含后台用户权限信息的对象")
public class Permission {

    @ApiModelProperty(value = "后台用户权限ID" ,dataType = "integer", required = true)
    private Integer id;
    @ApiModelProperty(value = "父级ID" ,dataType = "integer", required = true)
    private Integer pid;
    private String name;
    @ApiModelProperty(value = "权限值" ,dataType = "string", required = true)
    private String value;
    @ApiModelProperty(value = "图标" ,dataType = "string", required = true)
    private String icon;
    @ApiModelProperty(value = "权限类型：0->目录；1->菜单；2->按钮（接口绑定权限）" ,dataType = "integer", required = true)
    private Integer type;
    @ApiModelProperty(value = "前端资源访问路径" ,dataType = "string", required = true)
    private String uri;
    @ApiModelProperty(value = "启用状态；0->禁用；1->启用" ,dataType = "integer", required = true)
    private Integer status;
    @ApiModelProperty(value = "创建时间" ,dataType = "date", required = true)
    private Date createTime;
    @ApiModelProperty(value = "排序" ,dataType = "integer", required = true)
    private Integer sort;

}
