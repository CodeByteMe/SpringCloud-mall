package com.mall.admin.home.service;

import com.mall.common.pojo.Menu;

import java.util.List;

/**
 * MenuService
 *
 * @Author BessCroft
 * @Date 2020/9/21 22:00
 */
public interface MenuService {
    /**
     * 查询父菜单
     * @param adminId
     * @return
     */
    public List<Menu> listMenu(int adminId);

    /**
     * 查询父菜单的子菜单
     * @param adminId
     * @return
     */
    public List<Menu> listMenuChildren(int adminId,int parentId);
}
