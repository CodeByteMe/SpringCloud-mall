package com.mall.user.action.service.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mall.common.pojo.AdminUser;

import com.mall.user.action.dao.UserDAO;
import com.mall.user.action.service.UserService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserDAO userDAO;

    @Resource
    private StringRedisTemplate stringRedisTemplate;
    private ObjectMapper mapper = new ObjectMapper();

    @Transactional(isolation = Isolation.REPEATABLE_READ,propagation = Propagation.REQUIRED)
    public int delectUser(int id) {
        int i = userDAO.delectUser(id);
        if (i > 0) {
            stringRedisTemplate.delete("UserListBycompanyId");
            return i;
        }
        return i;
    }
    @Transactional(isolation = Isolation.REPEATABLE_READ,propagation = Propagation.REQUIRED)
    public int addUser(AdminUser adminUser) {
        int i=userDAO.addUser(adminUser);
        if (i > 0) {
            stringRedisTemplate.delete("UserListBycompanyId");
            return i;
        }
        return i;
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ,propagation = Propagation.REQUIRED)
    public String getCompanyId(String adminId) {
        String companyId = "";
        try {
            String getCompanyId = (String) stringRedisTemplate.boundHashOps("getCid").get("id-" + adminId);
            if (getCompanyId == null) {
                synchronized (this) {
                    getCompanyId = (String) stringRedisTemplate.boundHashOps("getCid").get("id-" + adminId);
                    if (getCompanyId == null) {
                        companyId = userDAO.getCompanyId(adminId);
                        String jsonStr = mapper.writeValueAsString(companyId);
                        stringRedisTemplate.boundHashOps("getCid").put("id-"+adminId,jsonStr);
                    }
                }
            } else {
                companyId = mapper.readValue(getCompanyId, String.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return companyId;
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ,propagation = Propagation.REQUIRED)
    public int grantRole(int id, int rid) {
        return userDAO.grantRole(id,rid);
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ,propagation = Propagation.REQUIRED)
    public int switchUser(int id, int status) {
        int i = userDAO.switchUser(id, status);
        if (i > 0) {
            stringRedisTemplate.delete("UserListBycompanyId");
            return i;
        }
        return i;
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ,propagation = Propagation.REQUIRED)
    public int updateUser(AdminUser adminUser) {
        int i= userDAO.updateUser(adminUser);
        if (i > 0) {
            stringRedisTemplate.delete("UserListBycompanyId");
            return i;
        }
        return i;
    }
}
