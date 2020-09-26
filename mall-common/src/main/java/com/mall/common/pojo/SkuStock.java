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
@ApiModel(value = "套餐对象", description = "包含套餐信息的对象")
public class SkuStock {

    @ApiModelProperty(value = "套餐ID" ,dataType = "integer", required = true)
    private Integer id;
    @ApiModelProperty(value = "套餐UUID" ,dataType = "string", required = true)
    private String skuId;
    @ApiModelProperty(value = "商品UUID" ,dataType = "string", required = true)
    private String productId;
    private double price;
    @ApiModelProperty(value = "库存" ,dataType = "integer", required = true)
    private Integer stock;
    @ApiModelProperty(value = "预警库存" ,dataType = "integer", required = true)
    private Integer lowStock;
    private String pic;
    @ApiModelProperty(value = "销量" ,dataType = "integer", required = true)
    private Integer sale;
    @ApiModelProperty(value = "单品促销价格" ,dataType = "double", required = true)
    private double promotionPrice;
    @ApiModelProperty(value = "锁定库存" ,dataType = "integer", required = true)
    private Integer lockStock;
    @ApiModelProperty(value = "颜色" ,dataType = "string", required = true)
    private String color;
    @ApiModelProperty(value = "尺寸" ,dataType = "string", required = true)
    private String size;

}
