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
    public List<Menu>  firstMenu();
}
