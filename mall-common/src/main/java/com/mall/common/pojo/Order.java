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
@ApiModel(value = "订单对象", description = "包含订单信息的对象")
public class Order {

    @ApiModelProperty(value = "订单ID" ,dataType = "integer", required = true)
    private Integer id;
    @ApiModelProperty(value = "订单UUID" ,dataType = "string", required = true)
    private String orderId;
    @ApiModelProperty(value = "用户UUID" ,dataType = "string", required = true)
    private String memberId;
    @ApiModelProperty(value = "提交时间" ,dataType = "date", required = true)
    private Date createTime;
    @ApiModelProperty(value = "用户账号" ,dataType = "string", required = true)
    private String memberUsername;
    @ApiModelProperty(value = "订单总金额" ,dataType = "double", required = true)
    private double totalAmount;
    @ApiModelProperty(value = "应付金额" ,dataType = "double", required = true)
    private double payAmount;
    @ApiModelProperty(value = "促销优化金额" ,dataType = "double", required = true)
    private double promotionAmount;
    @ApiModelProperty(value = "支付方式" ,dataType = "integer", required = true)
    private Integer payType;
    @ApiModelProperty(value = "订单状态" ,dataType = "integer", required = true)
    private Integer status;
    @ApiModelProperty(value = "订单类型" ,dataType = "integer", required = true)
    private Integer orderType;
    @ApiModelProperty(value = "地址对象" ,dataType = "address", required = true)
    private Address address;
    @ApiModelProperty(value = "自动确认时间" ,dataType = "integer", required = true)
    private Integer autoConfirmDay;
    @ApiModelProperty(value = "活动信息" ,dataType = "string", required = true)
    private String promotionInfo;
    @ApiModelProperty(value = "订单备注" ,dataType = "string", required = true)
    private String note;
    @ApiModelProperty(value = "确认收获状态" ,dataType = "integer", required = true)
    private Integer confirmStatus;
    @ApiModelProperty(value = "支付时间" ,dataType = "date", required = true)
    private Date paymentTime;
    @ApiModelProperty(value = "发货时间" ,dataType = "date", required = true)
    private Date deliveryTime;
    @ApiModelProperty(value = "确认收获时间" ,dataType = "date", required = true)
    private Date receiveTime;
    @ApiModelProperty(value = "修改时间" ,dataType = "date", required = true)
    private Date modifyTime;

}
