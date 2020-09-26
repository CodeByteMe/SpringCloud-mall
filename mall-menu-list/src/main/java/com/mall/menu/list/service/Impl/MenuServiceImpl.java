package com.mall.menu.list.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mall.common.pojo.Menu;
import com.mall.menu.list.dao.MenuDAO;
import com.mall.menu.list.service.MenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {
    @Resource
    private MenuDAO menuDAO;

    @Override
    public PageInfo MenuList(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Menu> menus = menuDAO.selectMenu();
        PageInfo pageInfo = new PageInfo(menus);
        return pageInfo;
    }

    @Override
    public PageInfo listMenuChildren(int parentId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Menu> menus = menuDAO.listMenuChildren(parentId);
        PageInfo pageInfo = new PageInfo(menus);
        return pageInfo;
    }

    @Override
    public int addMenu(Menu menu) {
        return menuDAO.addMenu(menu);
    }

    @Override
    public int delMenu(int id) {
        return menuDAO.delMenu(id);
    }

    @Override
    public int updateMenu(Menu menu) {
        return menuDAO.updateMenu(menu);
    }

}
