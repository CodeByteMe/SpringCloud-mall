package com.mall.menu.list.controller;

import com.github.pagehelper.PageInfo;
import com.mall.common.pojo.Menu;
import com.mall.common.vo.ResultVO;
import com.mall.menu.list.service.MenuService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/menu")
@Api(tags = "后台菜单列表接口")
public class MenuController {
    private MenuService menuService;
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ApiOperation(value = "后台菜单列表接口", notes = "需要携带token")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageInfo", value = "从第几行显示",required = true, dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "一页显示多少数据",required = true, dataType = "int"),
            @ApiImplicitParam(name = "token", value = "token验证信息", required = true, type = "String")
    })
    public ResultVO listMenu(@RequestParam Integer page,
                             @RequestParam Integer pageSize,
                             @RequestHeader(required = true) String token) {
        Jws<Claims> jws = Jwts.parser().setSigningKey("fadj@Jq4$fka").parseClaimsJws(token);
        PageInfo pageInfo = menuService.MenuList(page,pageSize);
        System.out.println(pageInfo);
        return new ResultVO(0,"success",pageInfo);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ApiOperation(value = "后台子菜单列表接口", notes = "需要携带token")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "parentId", value = "子菜单",required = true, dataType = "int"),
            @ApiImplicitParam(name = "pageInfo", value = "从第几行显示",required = true, dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "一页显示多少数据",required = true, dataType = "int"),
            @ApiImplicitParam(name = "token", value = "token验证信息", required = true, type = "String")
    })
    public ResultVO listChildren(@RequestParam Integer parentId,
                                 @RequestParam Integer page,
                                 @RequestParam Integer pageSize,
                                 @RequestHeader(required = true) String token) {
        Jws<Claims> jws = Jwts.parser().setSigningKey("fadj@Jq4$fka").parseClaimsJws(token);
        PageInfo pageInfo = menuService.listMenuChildren(parentId,page,pageSize);
        System.out.println(pageInfo);
        return new ResultVO(0,"success",pageInfo);
    }
    @RequestMapping(value = "/firstMenu", method = RequestMethod.GET)
    @ApiOperation(value = "后台子菜单列表接口", notes = "需要携带token")
    public ResultVO firstMenu(@RequestHeader(required = true) String token) {
        Jws<Claims> jws = Jwts.parser().setSigningKey("fadj@Jq4$fka").parseClaimsJws(token);
        List<Menu> firstMenu = menuService.firstMenu();
        return new ResultVO(0,"success",firstMenu);
    }
}
