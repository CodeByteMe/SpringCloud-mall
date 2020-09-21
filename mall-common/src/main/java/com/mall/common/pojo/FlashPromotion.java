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
@ApiModel(value = "限时购对象", description = "包含限时购信息的对象")
public class FlashPromotion {

    @ApiModelProperty(value = "限时购ID" ,dataType = "integer", required = true)
    private Integer id;
    private String title;
    @ApiModelProperty(value = "开始日期" ,dataType = "date", required = true)
    private Date startDate;
    @ApiModelProperty(value = "结束日期" ,dataType = "date", required = true)
    private Date endDate;
    private Integer status;
    @ApiModelProperty(value = "秒杀时间段名称" ,dataType = "date", required = true)
    private Date createTime;

}
