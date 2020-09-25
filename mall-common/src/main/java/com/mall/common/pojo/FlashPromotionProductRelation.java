package com.mall.common.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * FlashPromotionProductRelation
 *
 * @Author BessCroft
 * @Date 2020/9/25 11:43
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@ApiModel(value = "商品限时购与商品关系表对象", description = "包含商品限时购与商品关系表对象的实体类")
public class FlashPromotionProductRelation {
    @ApiModelProperty(value = "编号" ,dataType = "Integer", required = true)
    private Integer id;
    @ApiModelProperty(value = "秒杀表编号" ,dataType = "Integer", required = true)
    private Integer flashPromotionId;
    private Integer flashPromotionSessionId;
    private Integer productId;
    @ApiModelProperty(value = "限时购价格" ,dataType = "Integer", required = true)
    private double flashPromotionPrice;
    @ApiModelProperty(value = "限时购数量" ,dataType = "Integer", required = true)
    private Integer flashPromotionCount;
    @ApiModelProperty(value = "每人限购数量" ,dataType = "Integer", required = true)
    private Integer flashPromotionLimit;
    @ApiModelProperty(value = "排序" ,dataType = "Integer", required = true)
    private Integer sort;
}
