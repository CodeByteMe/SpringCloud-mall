package com.mall.menu.list.service;

import com.github.pagehelper.PageInfo;
import com.mall.common.pojo.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MenuService {
    public PageInfo MenuList(int pageNum, int pageSize);
    public PageInfo listMenuChildren(int parentId,int pageNum, int pageSize);
    public List<Menu>  firstMenu();
}
