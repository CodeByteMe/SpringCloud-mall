package com.mall.menu.list.dao;

import com.mall.common.pojo.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MenuDAO {
    /**
     *
     * @return
     */
    public List<Menu> selectMenu();
    public List<Menu> listMenuChildren(@Param("parentId") int parentId);
    public boolean addMenu(Menu menu);
    public boolean delMenu(int id);
    public boolean updateMenu(Menu menu);
    public boolean switchMenu(@Param("id") int id,@Param("hidden")int hidden);
}
