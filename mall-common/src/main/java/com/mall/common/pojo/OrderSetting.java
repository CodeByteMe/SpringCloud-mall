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
@ApiModel(value = "订单设置对象", description = "包含订单设置信息的对象")
public class OrderSetting {

    @ApiModelProperty(value = "订单设置ID" ,dataType = "integer", required = true)
    private Integer id;
    @ApiModelProperty(value = "秒杀订单超时关闭时间(分)" ,dataType = "integer", required = true)
    private Integer flashOrderOvertime;
    @ApiModelProperty(value = "正常订单超时时间(分)" ,dataType = "integer", required = true)
    private Integer normalOrderOvertime;
    @ApiModelProperty(value = "发货后自动确认收货时间（天）" ,dataType = "integer", required = true)
    private Integer confirmOvertime;
    @ApiModelProperty(value = "自动完成交易时间，不能申请售后（天）" ,dataType = "integer", required = true)
    private Integer finishOvertime;
    @ApiModelProperty(value = "订单完成后自动好评时间（天）" ,dataType = "integer", required = true)
    private Integer commentOvertime;

}
