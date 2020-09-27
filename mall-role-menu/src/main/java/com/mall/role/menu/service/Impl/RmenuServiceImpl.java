package com.mall.role.menu.service.Impl;

import com.mall.common.pojo.RoleMenuRelation;
import com.mall.role.menu.dao.RmenuDAO;
import com.mall.role.menu.service.RmenuService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RmenuServiceImpl implements RmenuService {
    @Resource
    private RmenuDAO roleMenuDAO;

    @Override
    public List<String> selectById(int roleId) {
        return roleMenuDAO.selectById(roleId);
    }


    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ,propagation = Propagation.REQUIRED)
    public boolean insertById(int roleId, int menuId) {
        int i = roleMenuDAO.delById(roleId);
        int i1 = roleMenuDAO.insertById(roleId, menuId);
        if(i>0&&i1>0){
            return true;
        }else {
            return false;
        }
    }
}
