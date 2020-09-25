package com.mall.user.action.controller;

import com.mall.common.pojo.AdminUser;
import com.mall.common.util.MD5Util;
import com.mall.common.vo.ResultVO;
import com.mall.user.action.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.swagger.annotations.Api;
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
    public ResultVO adduser(@RequestParam String username ,
                            @RequestParam String password,
                            @RequestParam String email,
                            @RequestParam String note,
                            @RequestParam String status,
                            @RequestHeader(required = true) String token) {
        Jws<Claims> jws = Jwts.parser().setSigningKey("fadj@Jq4$fka").parseClaimsJws(token);
        String companyId = jws.getBody().getId();
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
    public ResultVO delUser(int id, @RequestHeader(required = true) String token) {
        Jws<Claims> jws = Jwts.parser().setSigningKey("fadj@Jq4$fka").parseClaimsJws(token);
        int i = userService.delectUser(id);
        if (i >0) {
            return new ResultVO(0,"删除成功");
        } else {
            return new ResultVO(1,"删除失败");
        }
    }
}
