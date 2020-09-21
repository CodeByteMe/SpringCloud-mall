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
@ApiModel(value = "产品属性分类对象", description = "包含产品属性分类信息的对象")
public class ProductAttributeCategory {

    @ApiModelProperty(value = "产品属性分类ID" ,dataType = "integer", required = true)
    private Integer id;
    private String name;
    @ApiModelProperty(value = "属性数量" ,dataType = "integer", required = true)
    private Integer attributeCount;
    @ApiModelProperty(value = "参数数量" ,dataType = "integer", required = true)
    private Integer paramCount;

}
