package com.mall.role.menu.service;

import com.mall.common.pojo.RoleMenuRelation;

import java.util.List;

public interface RmenuService {
    public List<String> selectById(int roleId);
    public boolean insertById( int roleId,String a[]);
}
