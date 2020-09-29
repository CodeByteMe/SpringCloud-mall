package com.mall.user.action.controller;

import com.mall.common.pojo.AdminUser;
import com.mall.common.util.JWTUtil;
import com.mall.common.util.MD5Util;
import com.mall.common.vo.ResultVO;
import com.mall.user.action.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("/userAction")
@Api(tags = "用户列表接口")
public class UserConntroller {
    @Resource
    private UserService userService;
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ApiOperation(value = "添加用户接口", notes = "需要携带token")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名",required = true, dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码",required = true, dataType = "String"),
            @ApiImplicitParam(name = "email", value = "邮箱",required = true, dataType = "String"),
            @ApiImplicitParam(name = "note", value = "备注",required = true, dataType = "int"),
            @ApiImplicitParam(name = "status", value = "状态",required = true, dataType = "int")
    })
    public ResultVO adduser(@RequestParam String username ,
                            @RequestParam String password,
                            @RequestParam String email,
                            @RequestParam String note,
                            @RequestParam String status,
                            @RequestHeader(required = true) String token) {
        Jws<Claims> jws = JWTUtil.Decrypt(token);
        String companyId = userService.getCompanyId(jws.getBody().getId());
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        String pwd = MD5Util.md5(password);
        AdminUser adminUser =new AdminUser(username,pwd,email,note,Integer.parseInt(status));
        adminUser.setCompanyId(companyId);
        adminUser.setAdminId(uuid);
        adminUser.setCreateTime(new Date());
        int i = userService.addUser(adminUser);
        if (i >0) {
            return new ResultVO(0,"添加成功");
        } else {
            return new ResultVO(1,"添加失败");
        }
    }

    @RequestMapping(value = "/del", method = RequestMethod.GET)
    @ApiOperation(value = "后台商品列表接口", notes = "需要携带token")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户id",required = true, dataType = "int"),
            @ApiImplicitParam(name = "token", value = "token验证信息", required = true, type = "String")
    })
    public ResultVO delUser(@RequestParam int id,
                            @RequestHeader(required = true) String token) {
        Jws<Claims> jws = JWTUtil.Decrypt(token);
        int i = userService.delectUser(id);
        if (i >0) {
            return new ResultVO(0,"删除成功");
        }else {
            return new ResultVO(1,"删除失败");
        }
    }
    @RequestMapping(value = "/grant", method = RequestMethod.POST)
    @ApiOperation(value = "用户授予角色接口", notes = "需要携带token")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户id",required = true, dataType = "int"),
            @ApiImplicitParam(name = "rid", value = "角色id",required = true, dataType = "int")
    })
    public ResultVO grant(@RequestParam int id,
                          @RequestParam int rid,
                          @RequestHeader(required = true) String token) {
        Jws<Claims> jws = JWTUtil.Decrypt(token);
        int i = userService.grantRole(id, rid);
        if (i >0) {
            return new ResultVO(0,"授予角色成功");
        } else {
            return new ResultVO(1,"授予角色失败");
        }
    }
    @RequestMapping(value = "/switch", method = RequestMethod.GET)
    @ApiOperation(value = "用户启用接口", notes = "需要携带token")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户id",required = true, dataType = "int"),
            @ApiImplicitParam(name = "status", value = "状态",required = true, dataType = "int")
    })
    public ResultVO switchUser(@RequestParam int id,
                               @RequestParam int status,@RequestHeader(required = true) String token) {
        Jws<Claims> jws = JWTUtil.Decrypt(token);
        int i = userService.switchUser(id,status);
        if (i >0) {
            return new ResultVO(0,"启用成功");
        } else {
            return new ResultVO(1,"启用失败");
        }
    }
    @RequestMapping(value = "/compile", method = RequestMethod.POST)
    @ApiOperation(value = "修改用户接口", notes = "需要携带token")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户id",required = true, dataType = "int"),
            @ApiImplicitParam(name = "username", value = "用户名",required = true, dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码",required = true, dataType = "String"),
            @ApiImplicitParam(name = "email", value = "邮箱",required = true, dataType = "String"),
            @ApiImplicitParam(name = "nickName", value = "昵称",required = true, dataType = "String"),
            @ApiImplicitParam(name = "note", value = "备注",required = true, dataType = "int"),
            @ApiImplicitParam(name = "status", value = "状态",required = true, dataType = "int")
    })
    public ResultVO compileUser(@RequestParam Integer id,
                                @RequestParam String username,
                                @RequestParam String password,
                                @RequestParam String email,
                                @RequestParam String nickName,
                                @RequestParam String note,
                                @RequestParam Integer status,
                          @RequestHeader(required = true) String token) {
        Jws<Claims> jws = JWTUtil.Decrypt(token);
        AdminUser adminUser = new AdminUser();
        adminUser.setId(id);
        adminUser.setUsername(username);
        adminUser.setPassword(password);
        adminUser.setEmail(email);
        adminUser.setNickName(nickName);
        adminUser.setNote(note);
        adminUser.setStatus(status);
        int i = userService.updateUser(adminUser);
        if (i >0) {
            return new ResultVO(0,"修改成功");
        } else {
            return new ResultVO(1,"修改失败");
        }
    }

}
