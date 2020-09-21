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
@ApiModel(value = "订单退货申请对象", description = "包含订单信息的对象")
public class OrderReturn {

    @ApiModelProperty(value = "订单退货申请ID" ,dataType = "integer", required = true)
    private Integer id;
    @ApiModelProperty(value = "订单UUID" ,dataType = "string", required = true)
    private String orderId;
    @ApiModelProperty(value = "地址UUID" ,dataType = "string", required = true)
    private String addressId;
    @ApiModelProperty(value = "商品UUID" ,dataType = "string", required = true)
    private String productId;
    @ApiModelProperty(value = "申请时间" ,dataType = "date", required = true)
    private Date createTime;
    @ApiModelProperty(value = "用户用户名" ,dataType = "string", required = true)
    private String memberUsername;
    @ApiModelProperty(value = "退款金额" ,dataType = "double", required = true)
    private double returnAmount;
    private String returnName;
    private String returnPhone;
    @ApiModelProperty(value = "退货状态" ,dataType = "integer", required = true)
    private Integer status;
    @ApiModelProperty(value = "处理时间" ,dataType = "date", required = true)
    private Date handleTime;
    private String productPic;
    private String productName;
    private String productBrand;
    private String productAttr;
    @ApiModelProperty(value = "退货数量" ,dataType = "integer", required = true)
    private Integer productCount;
    @ApiModelProperty(value = "商品单价" ,dataType = "double", required = true)
    private double productPrice;
    @ApiModelProperty(value = "商品实际支付单价" ,dataType = "double", required = true)
    private double productRealPrice;
    @ApiModelProperty(value = "原因" ,dataType = "string", required = true)
    private String reason;
    private String description;
    @ApiModelProperty(value = "凭证图片" ,dataType = "string", required = true)
    private String proofPics;
    private String handleNote;
    private String handleMan;
    private String receiveMan;
    private Date receiveTime;
    private String receiveNote;

}
