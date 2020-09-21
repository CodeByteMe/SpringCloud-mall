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
@ApiModel(value = "购物车对象", description = "包含购物车信息的对象")
public class CartItem {

    @ApiModelProperty(value = "购物车ID" ,dataType = "integer", required = true)
    private Integer id;
    @ApiModelProperty(value = "商品UUID" ,dataType = "string", required = true)
    private String productId;
    @ApiModelProperty(value = "套餐UUID" ,dataType = "string", required = true)
    private String productSkuId;
    @ApiModelProperty(value = "用户UUID" ,dataType = "string", required = true)
    private String memberId;
    @ApiModelProperty(value = "用户昵称" ,dataType = "string", required = true)
    private String memberNickname;
    @ApiModelProperty(value = "购买数量" ,dataType = "integer", required = true)
    private Integer quantity;
    private double price;
    private String productPic;
    private String productName;
    @ApiModelProperty(value = "商品副标题（卖点）" ,dataType = "string", required = true)
    private String productSubTitle;
    @ApiModelProperty(value = "创建时间" ,dataType = "date", required = true)
    private Date createDate;
    @ApiModelProperty(value = "修改时间" ,dataType = "date", required = true)
    private Date modifyDate;
    @ApiModelProperty(value = "商品分类ID" ,dataType = "integer", required = true)
    private Integer productCategoryId;
    @ApiModelProperty(value = "商品品牌" ,dataType = "string", required = true)
    private String productBrand;
    @ApiModelProperty(value = "商品销售属性" ,dataType = "string", required = true)
    private String productAttr;

}
