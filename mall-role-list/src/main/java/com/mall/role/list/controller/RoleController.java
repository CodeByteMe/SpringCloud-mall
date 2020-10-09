package com.mall.role.list.controller;

import com.mall.common.pojo.Role;
import com.mall.common.util.JWTUtil;
import com.mall.common.vo.ResultVO;
import com.mall.role.list.service.RoleService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@CrossOrigin
@RequestMapping("/role")
@RestController
public class RoleController {
    @Resource
    private RoleService roleService;
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ApiOperation(value = "后台角色列表接口", notes = "需要携带token")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token验证信息", required = true, type = "String")
    })
    public ResultVO listRole( @RequestHeader(required = true) String token) {
        Jws<Claims> jws = JWTUtil.Decrypt(token);
        List<Role> roles = roleService.selectAllRole();
        return new ResultVO(0,"success",roles);
    }
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResultVO adduser(@RequestParam String name ,
                            @RequestParam String description,
                            @RequestHeader(required = true) String token) {
        Jws<Claims> jws = JWTUtil.Decrypt(token);
        Role role = new Role();
        role.setName(name);
        role.setDescription(description);
        role.setAdminCount(0);
        role.setCreateTime(new Date());
        role.setStatus(0);
        role.setSort(1);
        boolean b= roleService.insertRole(role);
        if (b) {
            return new ResultVO(0,"添加成功");
        } else {
            return new ResultVO(1,"添加失败");
        }
    }
    @RequestMapping(value = "/compile", method = RequestMethod.POST)
    public ResultVO compileRole(@RequestParam String id ,
                            @RequestParam String name ,
                            @RequestParam String description,
                            @RequestHeader(required = true) String token) {
        Jws<Claims> jws = JWTUtil.Decrypt(token);
        boolean b =roleService.compileRole(Integer.parseInt(id),name,description);
        if (b) {
            return new ResultVO(0,"修改成功");
        } else {
            return new ResultVO(1,"修改失败");
        }
    }
    @RequestMapping(value = "/del", method = RequestMethod.GET)
    public ResultVO delRole(@RequestParam String id ,
                            @RequestHeader(required = true) String token) {
        Jws<Claims> jws = JWTUtil.Decrypt(token);
        boolean b =roleService.delRole(Integer.parseInt(id));
        if (b) {
            return new ResultVO(0,"删除成功");
        } else {
            return new ResultVO(1,"删除失败");
        }
    }
    @RequestMapping(value = "/switch", method = RequestMethod.GET)
    public ResultVO delRole(@RequestParam String id ,
                            @RequestParam String status ,
                            @RequestHeader(required = true) String token) {
        Jws<Claims> jws = JWTUtil.Decrypt(token);
        boolean b = roleService.switchRole(Integer.parseInt(id), Integer.parseInt(status));
        if (b) {
            return new ResultVO(0,"启用成功");
        } else {
            return new ResultVO(1,"启用失败");
        }
    }
}
