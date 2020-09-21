package com.mall.admin.auth.controller;

import com.mall.admin.auth.service.AdminService;
import com.mall.admin.auth.service.MemberService;
import com.mall.common.pojo.AdminUser;
import com.mall.common.pojo.MemberUser;
import com.mall.common.util.JWTUtil;
import com.mall.common.util.MD5Util;
import com.mall.common.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.UUID;

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

    @Resource
    private MemberService memberService;

    @RequestMapping(value = "/adminLogin",method = RequestMethod.POST)
    @ApiOperation(value = "后台用户登录接口", notes = "后台登录的唯一接口，不需要携带token")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "登录用户名",required = true, dataType = "String"),
            @ApiImplicitParam(name = "password", value = "登录密码",required = true, dataType = "String")
    })
    public ResultVO adminLogin(@RequestParam String username,@RequestParam String password) {
        System.out.println(username);
        System.out.println(password);
        String s = MD5Util.md5(password);
        AdminUser adminUser = adminService.loginByAdminUser(username, s);
        System.out.println(adminUser);
        if (adminUser != null) {
            String token = JWTUtil.encrypt(adminUser.getUsername(), adminUser.getAdminId(), "admin");
            System.out.println("登录成功");
            return new ResultVO(0,"success",adminUser.getNickName(),token);
        } else {
            System.out.println("登录失败");
            return new ResultVO(1,"fail",null);
        }
    }

    @RequestMapping(value = "/memberLogin",method = RequestMethod.POST)
    @ApiOperation(value = "前台用户登录接口", notes = "前台登录的唯一接口，不需要携带token")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "登录用户名",required = true, dataType = "String"),
            @ApiImplicitParam(name = "password", value = "登录密码",required = true, dataType = "String")
    })
    public ResultVO memberLogin(@RequestParam String username,@RequestParam String password) {
        String s = MD5Util.md5(password);
        MemberUser memberUser = memberService.loginByMemberUser(username, s);
        if (memberUser != null) {
            String token = JWTUtil.encrypt(memberUser.getUsername(), memberUser.getMemberId(), "member");
            return new ResultVO(0,"success",memberUser.getNickname(),token);
        } else {
            return new ResultVO(1,"fail",null);
        }
    }

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    @ApiOperation(value = "前台用户注册接口", notes = "用来注册前台用户的接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "memberId", value = "用户ID(UUID)", required = true, type = "String"),
            @ApiImplicitParam(name = "username", value = "登录用户名", required = true, type = "String"),
            @ApiImplicitParam(name = "password", value = "登录密码", required = true, type = "String"),
            @ApiImplicitParam(name = "nickname", value = "昵称", required = true, type = "String"),
            @ApiImplicitParam(name = "phone", value = "手机号", required = true, type = "String"),
            @ApiImplicitParam(name = "gender", value = "性别", required = true, type = "gender"),
            @ApiImplicitParam(name = "birthday", value = "生日", required = true, type = "Date"),
            @ApiImplicitParam(name = "city", value = "城市", required = true, type = "String"),
            @ApiImplicitParam(name = "job", value = "职业", required = true, type = "String"),
            @ApiImplicitParam(name = "personalizedSignature", value = "个性签名", required = true, type = "String")
    })
    public ResultVO register(@RequestParam String username,
                             @RequestParam String password,
                             @RequestParam String nickname,
                             @RequestParam String phone,
                             @RequestParam int gender,
                             @RequestParam String city,
                             @RequestParam String job,
                             @RequestParam String personalizedSignature){
        String memberId = UUID.randomUUID().toString().replace("-", "");
        String pwd = MD5Util.md5(password);
        int i = memberService.insertMemberUser(new MemberUser(null,memberId,username,pwd,nickname,phone,1,new Date(),null,gender,city,job,personalizedSignature));
        if (i>0) {
            return new ResultVO(0,"注册成功",null);
        } else {
            return new ResultVO(1,"注册失败",null);
        }
    }

    @RequestMapping(value = "/getId",method = RequestMethod.POST)
    @ApiOperation(value = "服务调用接口", notes = "请求adminId，响应id")
    @ApiImplicitParam(name = "password", value = "登录密码",required = true, dataType = "String")
    public String getId(@RequestParam String adminId) {
        Integer id = adminService.getId(adminId);
        return id+"";
    }
}
