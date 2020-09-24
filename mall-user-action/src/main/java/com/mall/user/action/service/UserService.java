package com.mall.user.action.service;

import com.mall.common.pojo.AdminUser;

import java.util.List;

public interface UserService {
    public int addUser(AdminUser adminUser);
    public String getCompanyId(String adminId);
}
