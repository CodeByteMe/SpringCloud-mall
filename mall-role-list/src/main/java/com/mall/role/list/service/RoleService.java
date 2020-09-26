package com.mall.role.list.service;

import com.mall.common.pojo.Role;

import java.util.List;

public interface RoleService {
    public List<Role> selectAllRole();
    public int insertRole(Role role);
}
