package com.mall.common.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * Admin
 *
 * @Author BessCroft
 * @Date 2020/9/20 20:20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(value = "后台用户对象", description = "包含后台用户信息的对象")
public class AdminUser {

    @ApiModelProperty(value = "用户id" ,dataType = "Integet", required = true)
    private Integer id;
    @ApiModelProperty(value = "用户uuid" ,dataType = "String", required = true)
    private String adminId;
    @ApiModelProperty(value = "用户名" ,dataType = "String", required = true)
    private String username;
    @ApiModelProperty(value = "密码" ,dataType = "String", required = true)
    private String password;
    private String icon;
    private String email;
    private String nickName;
    private String note;
    private Date createTime;
    private Date loginTime;
    @ApiModelProperty(value = "帐号启用状态" ,dataType = "Integet", required = true)
    private Integer status;
    @ApiModelProperty(value = "公司id" ,dataType = "String", required = true)
    private String companyId;
    @ApiModelProperty(value = "公司名称" ,dataType = "String", required = true)
    private String companyName;

    public AdminUser(String username, String password, String email, String note, Integer status) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.note = note;
        this.status = status;
    }
}
