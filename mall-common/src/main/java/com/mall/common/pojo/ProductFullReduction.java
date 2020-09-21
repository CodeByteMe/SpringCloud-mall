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
@ApiModel(value = "产品满减对象", description = "包含产品满减信息的对象")
public class ProductFullReduction {

    @ApiModelProperty(value = "产品满减ID" ,dataType = "integer", required = true)
    private Integer id;
    private String productId;
    @ApiModelProperty(value = "满减价格" ,dataType = "double", required = true)
    private double fullPrice;
    @ApiModelProperty(value = "减少价格" ,dataType = "double", required = true)
    private double reducePrice;

}
