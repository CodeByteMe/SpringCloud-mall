package com.mall.user.list.service;

import com.mall.common.pojo.AdminUser;

import java.util.List;

public interface UserService {
    public List<AdminUser> selectUserListBycompanyid(String companyId, int page, int limit);
}
