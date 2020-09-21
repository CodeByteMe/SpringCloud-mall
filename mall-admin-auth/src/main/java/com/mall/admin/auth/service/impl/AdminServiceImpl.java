package com.mall.admin.auth.service.impl;

import com.mall.admin.auth.dao.AdminDAO;
import com.mall.admin.auth.service.AdminService;
import com.mall.common.pojo.AdminUser;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * AdminServiceImpl
 *
 * @Author BessCroft
 * @Date 2020/9/20 22:10
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Resource
    private AdminDAO adminDAO;

    @Override
    public AdminUser loginByAdminUser(String username, String password) {
        return adminDAO.getAdminUser(username,password);
    }

    @Override
    public Integer getId(String adminId) {
        return adminDAO.getId(adminId);
    }
}
