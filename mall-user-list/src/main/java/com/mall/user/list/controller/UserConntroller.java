package com.mall.user.list.controller;

import com.mall.common.pojo.AdminUser;
import com.mall.common.vo.ResultVO;
import com.mall.user.list.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/user")
@Api(tags = "用户列表接口")
public class UserConntroller {
    @Resource
    private UserService userService;
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResultVO listGoods(int page, int limit, @RequestHeader(required = true) String token) {
        Jws<Claims> jws = Jwts.parser().setSigningKey("qf123").parseClaimsJws(token);
        String companyId = jws.getBody().getId();
        List<AdminUser> adminUsers = userService.selectUserListBycompanyid(companyId,page,limit);
        if (adminUsers != null) {
            return new ResultVO(0,"查询成功",adminUsers);
        } else {
            return new ResultVO(1,"查询失败",null);
        }
    }
}
