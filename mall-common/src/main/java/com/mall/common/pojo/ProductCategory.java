package com.mall.common.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/*
 *   作者：官宣轩
 *   日期：2020-09-21
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@ApiModel(value = "产品分类对象", description = "包含产品分类信息的对象")
public class ProductCategory {

    @ApiModelProperty(value = "产品分类ID" ,dataType = "integer", required = true)
    private Integer id;
    @ApiModelProperty(value = "上级分类的编号：0表示一级分类" ,dataType = "integer", required = true)
    private Integer parentId;
    private String name;
    @ApiModelProperty(value = "分类级别：0->1级；1->2级" ,dataType = "integer", required = true)
    private Integer level;
    private Integer productCount;
    private String productUnit;
    @ApiModelProperty(value = "是否显示在导航栏：0->不显示；1->显示" ,dataType = "integer", required = true)
    private Integer navStatus;
    @ApiModelProperty(value = "显示状态：0->不显示；1->显示" ,dataType = "integer", required = true)
    private Integer showStatus;
    private Integer sort;
    @ApiModelProperty(value = "图标" ,dataType = "integer", required = true)
    private String icon;
    private String keywords;
    private String description;

}
