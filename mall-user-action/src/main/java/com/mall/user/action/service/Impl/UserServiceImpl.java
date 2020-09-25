package com.mall.user.action.service.Impl;

import com.mall.common.pojo.AdminUser;

import com.mall.user.action.dao.UserDAO;
import com.mall.user.action.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserDAO userDAO;

    @Override
    public int delectUser(int id) {
        return userDAO.delectUser(id);
    }
    @Override
    public int addUser(AdminUser adminUser) {
        return userDAO.addUser(adminUser);
    }

    @Override
    public String getCompanyId(String adminId) {
        return userDAO.getCompanyId(adminId);
    }
}
