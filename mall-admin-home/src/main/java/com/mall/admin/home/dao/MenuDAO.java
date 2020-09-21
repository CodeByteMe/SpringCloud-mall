package com.mall.admin.home.dao;

import com.mall.common.pojo.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * MenuDAO
 *
 * @Author BessCroft
 * @Date 2020/9/21 20:53
 */
public interface MenuDAO {
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
    public List<Menu> listMenuChildren(@Param("adminId") int adminId,
                                       @Param("parentId") int parentId);
}
