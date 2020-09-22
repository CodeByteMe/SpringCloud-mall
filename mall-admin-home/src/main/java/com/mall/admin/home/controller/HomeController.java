package com.mall.admin.home.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mall.admin.home.service.AuthService;
import com.mall.admin.home.service.MenuService;
import com.mall.common.pojo.Menu;
import com.mall.common.util.JWTUtil;
import com.mall.common.vo.ResultVO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * HomeController
 *
 * @Author BessCroft
 * @Date 2020/9/21 22:02
 */
@RestController
@CrossOrigin
@RequestMapping("/home")
@Api(tags = "后台框架接口")
public class HomeController {

    @Resource
    private MenuService menuService;

    @Resource
    private AuthService authService;

    private ObjectMapper mapper = new ObjectMapper();

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ApiOperation(value = "菜单信息查询接口" , notes = "根据用户的id查询该用户所有的权限菜单")
    @ApiImplicitParam(name = "token", value = "token验证信息", required = true, type = "String")
    public ResultVO getMenu(@RequestHeader(required = true) String token) {
        // 验证token
        Jws<Claims> jws = JWTUtil.Decrypt(token);
        // 获取解析的token中的用户名、id等
        String adminId = jws.getBody().getId();
        String jsonStr = authService.getId(adminId);
        System.out.println(jsonStr);
        List<Menu> menus = null;
        try {
            Integer i = mapper.readValue(jsonStr, Integer.class);
            menus = menuService.listMenu(i);
            for (Menu menu:menus) {
                Integer id = menu.getId();
                List<Menu> children = menuService.listMenuChildren(i, id);
                menu.setChildren(children);
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return new ResultVO(0,"success",menus);
    }
}
