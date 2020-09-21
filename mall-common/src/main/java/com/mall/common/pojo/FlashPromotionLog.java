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
@ApiModel(value = "限时购通知记录对象", description = "包含限时购通知记录信息的对象")
public class FlashPromotionLog {
    @ApiModelProperty(value = "订单ID" ,dataType = "integer", required = true)
    private Integer id;
    private String memberId;
    private String productId;
    private String memberPhone;
    private String productName;
    @ApiModelProperty(value = "用户订阅时间" ,dataType = "date", required = true)
    private Date subscribeTime;
    @ApiModelProperty(value = "发送时间" ,dataType = "date", required = true)
    private Date sendTime;

}
