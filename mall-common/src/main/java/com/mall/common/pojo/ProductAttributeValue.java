package com.mall.common.pojo;

import io.swagger.annotations.ApiModel;
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
@ApiModel(value = "产品参数信息对象", description = "包含产品参数信息的对象")
public class ProductAttributeValue {

    private Integer id;
    private String productId;
    private Integer productAttributeId;
    private String value;

}
