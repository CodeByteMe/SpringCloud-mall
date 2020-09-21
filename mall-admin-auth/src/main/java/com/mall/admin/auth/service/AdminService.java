package com.mall.admin.auth.service;

import com.mall.common.pojo.AdminUser;

/**
 * AdminService
 *
 * @Author BessCroft
 * @Date 2020/9/20 21:21
 */
public interface AdminService {
    public AdminUser loginByAdminUser(String username,String password);
    public Integer getId(String adminId);
}
