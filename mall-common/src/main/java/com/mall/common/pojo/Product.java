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
@ApiModel(value = "商品对象", description = "包含商品信息的对象")
public class Product {

    @ApiModelProperty(value = "商品ID" ,dataType = "integer", required = true)
    private Integer id;
    @ApiModelProperty(value = "商品UUID" ,dataType = "string", required = true)
    private String productId;
    @ApiModelProperty(value = "商品种类ID" ,dataType = "integer", required = true)
    private Integer productCategoryId;
    @ApiModelProperty(value = "商品种类名称" ,dataType = "string", required = true)
    private String productCategoryName;
    @ApiModelProperty(value = "商品种类属性ID" ,dataType = "integer", required = true)
    private Integer productAttributeCategoryId;
    @ApiModelProperty(value = "商品名称" ,dataType = "string", required = true)
    private String name;
    @ApiModelProperty(value = "商品图片" ,dataType = "string", required = true)
    private String pic;
    @ApiModelProperty(value = "商品上架状态" ,dataType = "integer", required = true)
    private Integer publishStatus;
    @ApiModelProperty(value = "排序" ,dataType = "integer", required = true)
    private Integer sort;
    @ApiModelProperty(value = "销量" ,dataType = "integer", required = true)
    private Integer sale;
    @ApiModelProperty(value = "商品价格" ,dataType = "double", required = true)
    private double price;
    @ApiModelProperty(value = "商品促销价格" ,dataType = "double", required = true)
    private double promotionPrice;
    @ApiModelProperty(value = "商品副标题" ,dataType = "string", required = true)
    private String subTitle;
    @ApiModelProperty(value = "商品描述" ,dataType = "string", required = true)
    private String description;
    @ApiModelProperty(value = "市场价" ,dataType = "double", required = true)
    private double originalPrice;
    @ApiModelProperty(value = "库存" ,dataType = "integer", required = true)
    private Integer stock;
    @ApiModelProperty(value = "库存预警值" ,dataType = "integer", required = true)
    private Integer lowStock;
    @ApiModelProperty(value = "单位" ,dataType = "string", required = true)
    private String unit;
    @ApiModelProperty(value = "重量" ,dataType = "double", required = true)
    private double weight;
    @ApiModelProperty(value = "是否为预告商品" ,dataType = "integer", required = true)
    private Integer previewStatus;
    private String keywords;
    private String note;
    private String detailTitle;
    private String detailDesc;
    @ApiModelProperty(value = "商品详情网页访问路径" ,dataType = "string", required = true)
    private String detailHtml;
    @ApiModelProperty(value = "促销开始时间" ,dataType = "date", required = true)
    private Date promotionStartTime;
    @ApiModelProperty(value = "促销结束时间" ,dataType = "date", required = true)
    private Date promotionEndTime;
    @ApiModelProperty(value = "活动限购数量" ,dataType = "integer", required = true)
    private Integer promotionPerLimit;
    @ApiModelProperty(value = "促销类型" ,dataType = "integer", required = true)
    private Integer promotionType;
    @ApiModelProperty(value = "套餐集合" ,dataType = "List<SkuStock>", required = true)
    private List<SkuStock> SkuStock;
    @ApiModelProperty(value = "公司id" ,dataType = "String", required = true)
    private String companyId;


}
