package com.mall.admin.home.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mall.admin.home.dao.MenuDAO;
import com.mall.admin.home.service.MenuService;
import com.mall.common.pojo.Menu;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * MenuServiceImpl
 *
 * @Author BessCroft
 * @Date 2020/9/21 22:00
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Resource
    private MenuDAO menuDAO;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public List<Menu> listMenu(int adminId) {
        List<Menu> menus = null;
        try {
            String s = (String) stringRedisTemplate.boundHashOps("listMenu").get("adminId-" + adminId);
            if (s == null) {
                synchronized (this) {
                    s = (String) stringRedisTemplate.boundHashOps("listMenu").get("adminId-" + adminId);
                    if (s == null) {
                        menus = menuDAO.listMenu(adminId);
                        String jsonStr = mapper.writeValueAsString(menus);
                        stringRedisTemplate.boundHashOps("listMenu").put("adminId-"+adminId,jsonStr);
                    }
                }
            } else {
                menus = mapper.readValue(s, new TypeReference<List<Menu>>() {
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return menus;
    }

    @Override
    public List<Menu> listMenuChildren(int adminId, int parentId) {
        List<Menu> menus = null;
        try {
            String s = (String) stringRedisTemplate.boundHashOps("listMenuChildren").get("adminId-" + adminId + "-parentId-" + parentId);
            if (s == null) {
                synchronized (this) {
                    s = (String) stringRedisTemplate.boundHashOps("listMenuChildren").get("adminId-" + adminId + "-parentId-" + parentId);
                    if (s == null) {
                        menus = menuDAO.listMenuChildren(adminId,parentId);
                        String jsonStr = mapper.writeValueAsString(menus);
                        stringRedisTemplate.boundHashOps("listMenuChildren").put("adminId-" + adminId + "-parentId-" + parentId,jsonStr);
                    }
                }
            } else {
                menus = mapper.readValue(s, new TypeReference<List<Menu>>() {
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return menus;
    }
}
