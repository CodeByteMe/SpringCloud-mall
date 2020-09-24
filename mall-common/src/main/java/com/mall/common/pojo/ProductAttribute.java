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
@ApiModel(value = "商品属性参数对象", description = "包含商品属性参数信息的对象")
public class ProductAttribute {

    @ApiModelProperty(value = "商品属性参数ID" ,dataType = "integer", required = true)
    private Integer id;
    @ApiModelProperty(value = "产品属性分类ID" ,dataType = "integer", required = true)
    private Integer productAttributeCategoryId;
    private String name;
    @ApiModelProperty(value = "产品属性分类名称" ,dataType = "integer", required = true)
    private String productAttributeCategoryName;
    @ApiModelProperty(value = "属性选择类型：0->唯一；1->单选；2->多选" ,dataType = "integer", required = true)
    private Integer selectType;
    @ApiModelProperty(value = "属性录入方式：0->手工录入；1->从列表中选取" ,dataType = "integer", required = true)
    private Integer inputType;
    @ApiModelProperty(value = "可选值列表，以逗号隔开" ,dataType = "integer", required = true)
    private String inputList;
    @ApiModelProperty(value = "排序字段：最高的可以单独上传图片" ,dataType = "integer", required = true)
    private Integer sort;
    @ApiModelProperty(value = "分类筛选样式：1->普通；1->颜色" ,dataType = "integer", required = true)
    private Integer filterType;
    @ApiModelProperty(value = "检索类型；0->不需要进行检索；1->关键字检索；2->范围检索" ,dataType = "integer", required = true)
    private Integer searchType;
    @ApiModelProperty(value = "相同属性产品是否关联；0->不关联；1->关联" ,dataType = "integer", required = true)
    private Integer relatedStatus;
    @ApiModelProperty(value = "是否支持手动新增；0->不支持；1->支持" ,dataType = "integer", required = true)
    private Integer handAddStatus;
    @ApiModelProperty(value = "属性的类型；0->规格；1->参数" ,dataType = "integer", required = true)
    private Integer type;

}
