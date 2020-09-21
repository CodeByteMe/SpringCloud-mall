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
@ToString
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "前台用户对象", description = "包含前台用户信息的对象")
public class MemberUser {
    @ApiModelProperty(value = "用户id" ,dataType = "Integer", required = true)
    private Integer id;
    @ApiModelProperty(value = "用户uuid" ,dataType = "String", required = true)
    private String memberId;
    @ApiModelProperty(value = "用户名" ,dataType = "String", required = true)
    private String username;
    @ApiModelProperty(value = "密码" ,dataType = "String", required = true)
    private String password;
    @ApiModelProperty(value = "用户昵称" ,dataType = "String", required = true)
    private String nickname;
    private String phone;
    @ApiModelProperty(value = "账号启用状态" ,dataType = "Integer", required = true)
    private Integer status;
    @ApiModelProperty(value = "注册时间" ,dataType = "date", required = true)
    private Date createTime;
    @ApiModelProperty(value = "头像" ,dataType = "string", required = true)
    private String icon;
    @ApiModelProperty(value = "性别" ,dataType = "integer", required = true)
    private Integer gender;
    private String city;
    private String job;
    @ApiModelProperty(value = "个性签名" ,dataType = "string", required = true)
    private String personalizedSignature;
}
