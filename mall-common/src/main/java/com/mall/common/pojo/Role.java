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
@ApiModel(value = "后台用户角色对象", description = "包含后台用户角色信息的对象")
public class Role {

    @ApiModelProperty(value = "角色ID" ,dataType = "integer", required = true)
    private Integer id;
    private String name;
    @ApiModelProperty(value = "描述" ,dataType = "integer", required = true)
    private String description;
    @ApiModelProperty(value = "后台用户数量" ,dataType = "integer", required = true)
    private Integer adminCount;
    private Date createTime;
    private Integer status;
    private Integer sort;

}
