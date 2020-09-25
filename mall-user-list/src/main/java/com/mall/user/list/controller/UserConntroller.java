package com.mall.user.list.controller;

import com.github.pagehelper.PageInfo;
import com.mall.common.pojo.AdminUser;
import com.mall.common.vo.ResultVO;
import com.mall.user.list.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@CrossOrigin
@RequestMapping("/user")
@Api(tags = "用户列表接口")
public class UserConntroller {
    @Resource
    private UserService userService;
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ApiOperation(value = "后台用户列表接口", notes = "需要携带token")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageInfo", value = "从第几行显示",required = true, dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "一页显示多少数据",required = true, dataType = "int"),
            @ApiImplicitParam(name = "token", value = "token验证信息", required = true, type = "String")
    })
    public ResultVO listUser(@RequestParam Integer page, @RequestParam Integer pageSize, @RequestHeader(required = true) String token) {
        Jws<Claims> jws = Jwts.parser().setSigningKey("fadj@Jq4$fka").parseClaimsJws(token);
        String adminId = jws.getBody().getId();
        System.out.println(adminId);
        String companyId = userService.getCid(adminId);
        System.out.println(companyId);
        PageInfo pageInfo = userService.UserListBycompanyId(companyId,page, pageSize);
        System.out.println(pageInfo);
        return new ResultVO(0,"success",pageInfo);
    }
}
