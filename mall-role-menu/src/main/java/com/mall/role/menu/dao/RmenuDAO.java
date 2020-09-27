package com.mall.role.menu.dao;

import com.mall.common.pojo.RoleMenuRelation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RmenuDAO {
    public List<String> selectById(int roleId);
    public int delById(int roleId);
    public int insertById(@Param("roleId") int roleId, @Param("menuId") int menuId);
}
