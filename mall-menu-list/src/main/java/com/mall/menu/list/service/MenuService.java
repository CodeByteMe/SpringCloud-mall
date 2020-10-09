package com.mall.menu.list.service;

import com.github.pagehelper.PageInfo;
import com.mall.common.pojo.Menu;

import java.util.List;

public interface MenuService {
    public PageInfo MenuList(int pageNum, int pageSize);
    public PageInfo listMenuChildren(int parentId,int pageNum, int pageSize);
    public boolean addMenu(Menu menu);
    public boolean delMenu(int id);
    public boolean updateMenu(Menu menu);
    public boolean switchMenu(int id,int hidden);
}
