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
@ApiModel(value = "订单详细对象", description = "包含订单详细信息的对象")
public class OrderItem {

    @ApiModelProperty(value = "订单ID" ,dataType = "integer", required = true)
    private Integer id;
    @ApiModelProperty(value = "订单UUID" ,dataType = "string", required = true)
    private String orderId;
    @ApiModelProperty(value = "商品UUID" ,dataType = "string", required = true)
    private String orderSn;
    private String productId;
    private String productPic;
    private String productName;
    private String productSn;
    private Double productPrice;
    @ApiModelProperty(value = "购买数量" ,dataType = "integer", required = true)
    private Integer productQuantity;
    @ApiModelProperty(value = "套餐UUID" ,dataType = "string", required = true)
    private String productSkuId;
    private String productSkuCode;
    @ApiModelProperty(value = "商品分类ID" ,dataType = "integer", required = true)
    private Integer productCategoryId;
    @ApiModelProperty(value = "商品促销名称" ,dataType = "string", required = true)
    private String promotionName;
    @ApiModelProperty(value = "商品促销分解金额" ,dataType = "double", required = true)
    private double promotionAmount;
    @ApiModelProperty(value = "商品销售属性" ,dataType = "integer", required = true)
    private String productAttr;
}
