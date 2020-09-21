package com.mall.admin.home.controller;

import com.mall.admin.home.service.MenuService;
import com.mall.common.pojo.Menu;
import com.mall.common.vo.ResultVO;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
public class HomeController {

    @Resource
    private MenuService menuService;

    public ResultVO getMenu(@RequestParam String adminId) {
        List<Menu> menus = menuService.listMenu(adminId);
        for (Menu menu:menus) {
            Integer id = menu.getId();
            List<Menu> children = menuService.listMenuChildren(adminId, id);
            menu.setChildren(children);
        }
        return new ResultVO(0,"success",menus);
    }
}
