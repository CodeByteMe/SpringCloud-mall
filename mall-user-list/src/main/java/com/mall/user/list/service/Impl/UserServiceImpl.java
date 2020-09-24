package com.mall.user.list.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mall.common.pojo.AdminUser;
import com.mall.user.list.dao.UserDAO;
import com.mall.user.list.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserDAO userDAO;
    @Override
    public PageInfo UserListBycompanyId(String companyId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<AdminUser> adminUsers = userDAO.selectUserListBycompanyId(companyId);
        PageInfo pageInfo = new PageInfo(adminUsers);
        return pageInfo;
    }

    @Override
    public String getCid(String adminId) {
        return userDAO.selectCompanyId(adminId);
    }
}
