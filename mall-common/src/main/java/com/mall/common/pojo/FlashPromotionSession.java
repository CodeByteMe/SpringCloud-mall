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
@ApiModel(value = "限时购场次对象", description = "包含限时购场次信息的对象")
public class FlashPromotionSession {

    @ApiModelProperty(value = "限时购场次ID" ,dataType = "integer", required = true)
    private Integer id;
    private String name;
    private Date startTime;
    private Date endTime;
    private Integer status;
    @ApiModelProperty(value = "创建时间" ,dataType = "date", required = true)
    private Date createTime;

}
