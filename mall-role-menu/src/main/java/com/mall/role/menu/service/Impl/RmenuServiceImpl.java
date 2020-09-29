package com.mall.role.menu.service.Impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mall.common.pojo.RoleMenuRelation;
import com.mall.role.menu.dao.RmenuDAO;
import com.mall.role.menu.service.RmenuService;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RmenuServiceImpl implements RmenuService {
    @Resource
    private RmenuDAO roleMenuDAO;
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    private ObjectMapper mapper = new ObjectMapper();
    @Override
    public List<String> selectById(int roleId) {
        List<String> menuId = null;
        try {
            String s = (String) stringRedisTemplate.boundHashOps("selectById").get("roleId-" + roleId);
            if (s == null) {
                synchronized (this) {
                    s = (String) stringRedisTemplate.boundHashOps("selectById").get("roleId-" + roleId);
                    if (s == null) {
                        menuId = roleMenuDAO.selectById(roleId);
                        String jsonStr = mapper.writeValueAsString(menuId);
                        stringRedisTemplate.boundHashOps("selectById").put("roleId-" + roleId, jsonStr);
                    }
                }
            } else {
                menuId = mapper.readValue(s, new TypeReference<List<String>>() {
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return menuId;

    }


    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ,propagation = Propagation.REQUIRED)
    public boolean insertById(int roleId,String a[]) {
        int i1 = 0 ;
        int j = roleMenuDAO.delById(roleId);
        for (int i=0; i<a.length; i++) {
            int n = Integer.parseInt(a[i]);
             i1 = roleMenuDAO.insertById(roleId, n);
        }
        if(i1>0&&j>0){
            stringRedisTemplate.delete("selectById");
            return true;
        }else {
            return false;
        }
    }
}
