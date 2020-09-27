package com.mall.common.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@ApiModel(value = "菜单角色关系", description = "包含角色菜单关系的对象")
public class RoleMenuRelation {
    @ApiModelProperty(value = "角色菜单关系id" ,dataType = "integer", required = true)
    private Integer id;
    @ApiModelProperty(value = "角色id" ,dataType = "integer", required = true)
    private Integer roleId;
    @ApiModelProperty(value = "菜单id" ,dataType = "integer", required = true)
    private Integer menuId;
}
