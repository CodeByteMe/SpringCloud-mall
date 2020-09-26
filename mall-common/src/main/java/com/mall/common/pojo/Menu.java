package com.mall.common.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.List;

/*
 *   作者：官宣轩
 *   日期：2020-09-21
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@ApiModel(value = "后台菜单对象", description = "包含后台菜单信息的对象")
public class Menu {

    @ApiModelProperty(value = "后台菜单ID" ,dataType = "integer", required = true)
    private Integer id;
    @ApiModelProperty(value = "父级ID" ,dataType = "integer", required = true)
    private Integer parentId;
    @ApiModelProperty(value = "创建时间" ,dataType = "date", required = true)
    private Date createTime;
    @ApiModelProperty(value = "菜单名称" ,dataType = "string", required = true)
    private String title;
    @ApiModelProperty(value = "菜单级数" ,dataType = "integer", required = true)
    private Integer level;
    @ApiModelProperty(value = "菜单排序" ,dataType = "integer", required = true)
    private Integer sort;
    @ApiModelProperty(value = "访问路径" ,dataType = "string", required = true)
    private String url;
    @ApiModelProperty(value = "图标" ,dataType = "string", required = true)
    private String icon;
    @ApiModelProperty(value = "前端隐藏" ,dataType = "integer", required = true)
    private Integer hidden;
    @ApiModelProperty(value = "子级菜单" ,dataType = "menu", required = true)
    private List<Menu> children;

    public Menu(Integer id, Integer parentId, Date createTime, String title, Integer level, Integer sort, String url, String icon, Integer hidden) {
        this.id = id;
        this.parentId = parentId;
        this.createTime = createTime;
        this.title = title;
        this.level = level;
        this.sort = sort;
        this.url = url;
        this.icon = icon;
        this.hidden = hidden;
    }
}
