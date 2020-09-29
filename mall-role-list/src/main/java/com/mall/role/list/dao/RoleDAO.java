package com.mall.role.list.dao;

import com.mall.common.pojo.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleDAO {
    public List<Role> selectAllRole();
    public boolean insertRole(Role role);
    public boolean updateRole(@Param("id") int id, @Param("name") String name, @Param("description") String description);
    public boolean delRole(int id);
    public boolean switchRole(@Param("id") int id,@Param("status")int status);
}
