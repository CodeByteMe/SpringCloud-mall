package com.mall.role.list.service.Impl;

import com.mall.common.pojo.Role;
import com.mall.role.list.dao.RoleDAO;
import com.mall.role.list.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Resource
    private RoleDAO roleDAO;

    @Override
    public List<Role> selectAllRole() {
        return roleDAO.selectAllRole();
    }

    @Override
    public int insertRole(Role role) {
        return roleDAO.insertRole(role);
    }
}
