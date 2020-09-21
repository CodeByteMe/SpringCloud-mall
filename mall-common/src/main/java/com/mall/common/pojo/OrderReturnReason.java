package com.mall.common.pojo;

import io.swagger.annotations.ApiModel;
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
@ApiModel(value = "订单退货原因对象", description = "包含订单退货原因信息的对象")
public class OrderReturnReason {

    private Integer id;
    private String name;
    private Integer sort;
    private Integer status;
    private Date createTime;

}
