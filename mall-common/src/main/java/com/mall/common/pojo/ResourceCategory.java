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
@ApiModel(value = "资源分类对象", description = "包含资源分类信息的对象")
public class ResourceCategory {

    private Integer id;
    private Date createTime;
    private String name;
    private Integer sort;

}
