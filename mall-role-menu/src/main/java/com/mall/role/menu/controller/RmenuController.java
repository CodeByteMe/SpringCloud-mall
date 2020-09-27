package com.mall.role.menu.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mall.common.pojo.Menu;
import com.mall.common.pojo.RoleMenuRelation;
import com.mall.common.vo.ResultVO;

import com.mall.role.menu.service.RmenuService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@CrossOrigin
@RequestMapping("/rolemenu")
@RestController
public class RmenuController {
    @Resource
    private RmenuService rmenuService;

    private ObjectMapper mapper = new ObjectMapper();

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ApiOperation(value = "后台角色菜单接口", notes = "需要携带token")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleId", value = "角色id", required = true, type = "Integer"),
            @ApiImplicitParam(name = "token", value = "token验证信息", required = true, type = "String")
    })
    public ResultVO listRole(@RequestParam Integer roleId,@RequestHeader(required = true) String token) {
//        Jws<Claims> jws = Jwts.parser().setSigningKey("fadj@Jq4$fka").parseClaimsJws(token);
        List<String> roleMenuRelations = rmenuService.selectById(roleId);
        return new ResultVO(0,"success",roleMenuRelations);
    }

    @RequestMapping(value = "/addMenu", method = RequestMethod.POST)
    public ResultVO addMenu(@RequestParam Integer roleId,
                            @RequestParam String str,
                            @RequestHeader(required = true) String token) {
        try {
            List<Menu> menus = mapper.readValue(str, new TypeReference<List<Menu>>() {
            });
            for (Menu m:menus
                 ) {
                boolean b =rmenuService.insertById(roleId,m.getId());
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
