package com.mall.role.list.dao;

import com.mall.common.pojo.Role;

import java.util.List;

public interface RoleDAO {
    public List<Role> selectAllRole();
    public int insertRole(Role role);
}
