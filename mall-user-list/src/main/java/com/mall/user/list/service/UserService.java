package com.mall.user.list.service;

import com.github.pagehelper.PageInfo;
import com.mall.common.pojo.AdminUser;

import java.util.List;

public interface UserService {
    public PageInfo UserListBycompanyId( String companyId,int pageNum,int pageSize);
    public String getCid(String adminId);
}
