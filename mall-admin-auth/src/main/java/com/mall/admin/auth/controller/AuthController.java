package com.mall.admin.auth.controller;

import com.mall.admin.auth.service.AdminService;
import com.mall.common.pojo.AdminUser;
import com.mall.common.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * AuthController
 *
 * @Author BessCroft
 * @Date 2020/9/20 21:20
 */
@RestController
@CrossOrigin
@RequestMapping("/auth")
@Api(tags = "身份验证接口")
public class AuthController {

    @Resource
    private AdminService adminService;

    @RequestMapping(value = "/adminlogin",method = RequestMethod.POST)
    @ApiOperation(value = "后台用户登录接口", notes = "后台登录的唯一接口，不需要携带token")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "登录用户名",required = true, dataType = "String"),
            @ApiImplicitParam(name = "password", value = "登录密码",required = true, dataType = "String")
    })
    public ResultVO adminLogin(String username,String password) {
        System.out.println(username);
        System.out.println(password);
        AdminUser adminUser = adminService.loginByAdminUser(username, password);
        if (adminUser != null) {
            return new ResultVO(0,"success",adminUser);
        } else {
            return new ResultVO(1,"fail",null);
        }
    }
}
