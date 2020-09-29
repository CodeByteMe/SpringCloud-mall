package com.mall.role.list.service;

import com.mall.common.pojo.Role;

import java.util.List;

public interface RoleService {
    public List<Role> selectAllRole();
    public boolean insertRole(Role role);
    public boolean compileRole(int id,  String name, String description);
    public boolean delRole(int id);
    public boolean switchRole(int id,int status);
}
