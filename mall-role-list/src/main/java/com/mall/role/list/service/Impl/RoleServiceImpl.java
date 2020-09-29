package com.mall.role.list.service.Impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mall.common.pojo.Role;
import com.mall.role.list.dao.RoleDAO;
import com.mall.role.list.service.RoleService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Resource
    private RoleDAO roleDAO;
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public List<Role> selectAllRole() {
        List<Role> roles = null;
        try {
            String s = (String) stringRedisTemplate.boundHashOps("selectAllRole").get("selectAllRole");
            if (s == null) {
                synchronized (this) {
                    s = (String) stringRedisTemplate.boundHashOps("selectAllRole").get("selectAllRole");
                    if (s == null) {
                        roles = roleDAO.selectAllRole();
                        String jsonStr = mapper.writeValueAsString(roles);
                        stringRedisTemplate.boundHashOps("selectAllRole").put("selectAllRole",jsonStr);
                    }
                }
            } else {
                roles = mapper.readValue(s, new TypeReference<List<Role>>() {
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return roles;
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ,propagation = Propagation.REQUIRED)
    public boolean insertRole(Role role) {
        boolean b = roleDAO.insertRole(role);
        if (b){
            stringRedisTemplate.delete("selectAllRole");

            return true;
        }
        return false;
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ,propagation = Propagation.REQUIRED)
    public boolean compileRole(int id, String name, String description) {
        boolean b = roleDAO.updateRole(id,name,description);
        if (b){
            stringRedisTemplate.delete("selectAllRole");
            stringRedisTemplate.delete("selectById");
            return true;
        }
        return false;
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ,propagation = Propagation.REQUIRED)
    public boolean delRole(int id) {
        boolean b = roleDAO.delRole(id);
        if (b){
            stringRedisTemplate.delete("selectAllRole");
            stringRedisTemplate.delete("selectById");
            return true;
        }
        return false;
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ,propagation = Propagation.REQUIRED)
    public boolean switchRole(int id, int status) {
        boolean b = roleDAO.switchRole(id,status);
        if (b){
            stringRedisTemplate.delete("selectAllRole");
            return true;
        }
        return false;
    }
}
