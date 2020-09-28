package com.mall.role.list.service;

import com.mall.common.pojo.Role;

import java.util.List;

public interface RoleService {
    public List<Role> selectAllRole();
    public int insertRole(Role role);
    public int compileRole(int id,  String name, String description);
    public int delRole(int id);
    public int switchRole(int id,int status);
}
