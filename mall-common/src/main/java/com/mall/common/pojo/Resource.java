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
@ApiModel(value = "后台资源对象", description = "包含后台资源信息的对象")
public class Resource {

    @ApiModelProperty(value = "后台资源ID" ,dataType = "integer", required = true)
    private Integer id;
    @ApiModelProperty(value = "创建时间" ,dataType = "date", required = true)
    private Date createTime;
    private String name;
    @ApiModelProperty(value = "资源URI" ,dataType = "string", required = true)
    private String uri;
    private String description;
    @ApiModelProperty(value = "资源分类ID" ,dataType = "integer", required = true)
    private Integer categoryId;

}
