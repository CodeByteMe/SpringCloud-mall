package com.mall.user.list.service.Impl;

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
    public List<AdminUser> selectUserListBycompanyid(String companyId, int page, int limit) {
        int start = (page-1)*limit;
        return userDAO.selectUserListBycompanyid(companyId,start,limit);
    }
}
