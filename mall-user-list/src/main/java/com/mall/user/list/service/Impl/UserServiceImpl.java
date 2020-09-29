package com.mall.user.list.service.Impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mall.common.pojo.AdminUser;
import com.mall.user.list.dao.UserDAO;
import com.mall.user.list.service.UserService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserDAO userDAO;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    private ObjectMapper mapper = new ObjectMapper();
    @Override
    public PageInfo UserListBycompanyId(String companyId, int pageNum, int pageSize) {
        List<AdminUser> adminUsers = null;
        PageHelper.startPage(pageNum, pageSize);
        try {
            stringRedisTemplate.delete("UserListBycompanyId");
            String s = (String) stringRedisTemplate.boundHashOps("UserListBycompanyId").get("companyId-" + companyId);
            if (s == null) {
                synchronized (this) {
                    s = (String) stringRedisTemplate.boundHashOps("UserListBycompanyId").get("companyId-" + companyId);
                    if (s == null) {
                       adminUsers = userDAO.selectUserListBycompanyId(companyId);
                        String jsonStr = mapper.writeValueAsString(adminUsers);
                        stringRedisTemplate.boundHashOps("UserListBycompanyId").put("companyId-" + companyId, jsonStr);
                        }
                    }
                } else {
                    adminUsers = mapper.readValue(s, new TypeReference<List<AdminUser>>() {
                    });
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
                        PageInfo pageInfo = new PageInfo(adminUsers);
                        return pageInfo;
    }

    @Override
    public String getCid(String adminId) {
        String companyId = "";
        try {
            String getCompanyId = (String) stringRedisTemplate.boundHashOps("getCid").get("id-" + adminId);
            if (getCompanyId == null) {
                synchronized (this) {
                    getCompanyId = (String) stringRedisTemplate.boundHashOps("getCid").get("id-" + adminId);
                    if (getCompanyId == null) {
                        companyId = userDAO.selectCompanyId(adminId);
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
}
