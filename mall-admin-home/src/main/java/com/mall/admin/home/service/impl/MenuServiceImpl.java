package com.mall.admin.home.service.impl;

import com.mall.admin.home.dao.MenuDAO;
import com.mall.admin.home.service.MenuService;
import com.mall.common.pojo.Menu;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * MenuServiceImpl
 *
 * @Author BessCroft
 * @Date 2020/9/21 22:00
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Resource
    private MenuDAO menuDAO;

    @Override
    public List<Menu> listMenu(String adminId) {
        return menuDAO.listMenu(adminId);
    }

    @Override
    public List<Menu> listMenuChildren(String adminId, Integer parentId) {
        return menuDAO.listMenuChildren(adminId,parentId);
    }
}
