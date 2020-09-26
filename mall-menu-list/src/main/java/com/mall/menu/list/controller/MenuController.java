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

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/menu")
@Api(tags = "后台菜单列表接口")
public class MenuController {
    @Resource
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

    @RequestMapping(value = "/listChildren", method = RequestMethod.GET)
    @ApiOperation(value = "后台子菜单列表接口", notes = "需要携带token")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "子菜单",required = true, dataType = "int"),
            @ApiImplicitParam(name = "pageInfo", value = "从第几行显示",required = true, dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "一页显示多少数据",required = true, dataType = "int"),
            @ApiImplicitParam(name = "token", value = "token验证信息", required = true, type = "String")
    })
    public ResultVO listChildren(@RequestParam Integer id,
                                 @RequestParam Integer page,
                                 @RequestParam Integer pageSize,
                                 @RequestHeader(required = true) String token) {
        Jws<Claims> jws = Jwts.parser().setSigningKey("fadj@Jq4$fka").parseClaimsJws(token);
        PageInfo pageInfo = menuService.listMenuChildren(id,page,pageSize);
        System.out.println(pageInfo);
        return new ResultVO(0,"success",pageInfo);
    }
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResultVO addMenu(@RequestParam String title ,
                            @RequestParam String id,
                            @RequestParam String sort,
                            @RequestParam String url,
                            @RequestParam String hidden,
                            @RequestHeader(required = true) String token) {
        Jws<Claims> jws = Jwts.parser().setSigningKey("fadj@Jq4$fka").parseClaimsJws(token);
        Menu menu = new Menu(null,Integer.parseInt(id),new Date(),title,0,Integer.parseInt(sort),url,"el-icon-star-off",Integer.parseInt(hidden));
        int i = menuService.addMenu(menu);
        if ( i>0) {
            return new ResultVO(0,"添加成功");
        } else {
            return new ResultVO(1,"添加失败");
        }
    }
    @RequestMapping(value = "/del", method = RequestMethod.DELETE)
    public ResultVO addMenu(@RequestParam String id ,
                            @RequestHeader(required = true) String token) {
        Jws<Claims> jws = Jwts.parser().setSigningKey("fadj@Jq4$fka").parseClaimsJws(token);
        int i = menuService.delMenu(Integer.parseInt(id));
        if ( i>0) {
            return new ResultVO(0,"删除成功");
        } else {
            return new ResultVO(1,"删除失败");
        }
    }
    @RequestMapping(value = "/redact", method = RequestMethod.POST)
    public ResultVO redactMenu(@RequestParam String id,
                            @RequestParam String title ,
                            @RequestParam String parentId ,
                            @RequestParam String sort,
                            @RequestParam String url,
                            @RequestParam String hidden,
                            @RequestHeader(required = true) String token) {
        Jws<Claims> jws = Jwts.parser().setSigningKey("fadj@Jq4$fka").parseClaimsJws(token);
        Menu menu = new Menu();
        menu.setId(Integer.parseInt(id));
        menu.setTitle(title);
        menu.setParentId(Integer.parseInt(parentId));
        menu.setSort(Integer.parseInt(sort));
        menu.setUrl(url);
        menu.setHidden(Integer.parseInt(hidden));
        int i = menuService.updateMenu(menu);
        if ( i>0) {
            return new ResultVO(0,"修改成功");
        } else {
            return new ResultVO(1,"修改失败");
        }
    }
}
