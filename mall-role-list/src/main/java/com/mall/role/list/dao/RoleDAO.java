package com.mall.role.list.dao;

import com.mall.common.pojo.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleDAO {
    public List<Role> selectAllRole();
    public int insertRole(Role role);
    public int updateRole(@Param("id") int id, @Param("name") String name, @Param("description") String description);
    public int delRole(int id);
    public int switchRole(@Param("id") int id,@Param("status")int status);
}
