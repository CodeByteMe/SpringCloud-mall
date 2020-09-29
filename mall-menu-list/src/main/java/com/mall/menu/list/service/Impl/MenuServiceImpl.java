package com.mall.menu.list.service.Impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mall.common.pojo.Menu;
import com.mall.menu.list.dao.MenuDAO;
import com.mall.menu.list.service.MenuService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {
    @Resource
    private MenuDAO menuDAO;
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    private ObjectMapper mapper = new ObjectMapper();
    @Override
    public PageInfo MenuList(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Menu> menus = null;
        try {
            stringRedisTemplate.delete("listMenuChildren");
            String s = (String) stringRedisTemplate.boundHashOps("listMenuChildren").get("listMenuChildren");
            if (s == null) {
                synchronized (this) {
                    s = (String) stringRedisTemplate.boundHashOps("listMenuChildren").get("listMenuChildren");
                    if (s == null) {
                        menus = menuDAO.selectMenu();
                        String jsonStr = mapper.writeValueAsString(menus);
                        stringRedisTemplate.boundHashOps("listMenuChildren").put("listMenuChildren",jsonStr);
                    }
                }
            } else {
                menus = mapper.readValue(s, new TypeReference<List<Menu>>() {
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        PageInfo pageInfo = new PageInfo(menus);
        return pageInfo;
    }

    @Override
    public PageInfo listMenuChildren(int parentId, int pageNum, int pageSize) {
        List<Menu> menus = null;
        PageHelper.startPage(pageNum, pageSize);
        try {
            stringRedisTemplate.delete("listMenuChildren");
            String s = (String) stringRedisTemplate.boundHashOps("listMenuChildren").get("parentId-" + parentId);
            if (s == null) {
                synchronized (this) {
                    s = (String) stringRedisTemplate.boundHashOps("listMenuChildren").get("parentId-" + parentId);
                    if (s == null) {
                        menus = menuDAO.listMenuChildren(parentId);
                        String jsonStr = mapper.writeValueAsString(menus);
                        stringRedisTemplate.boundHashOps("listMenuChildren").put("parentId-"+parentId,jsonStr);
                    }
                }
            } else {
                menus = mapper.readValue(s, new TypeReference<List<Menu>>() {
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        PageInfo pageInfo = new PageInfo(menus);
        return pageInfo;
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ,propagation = Propagation.REQUIRED)
    public boolean addMenu(Menu menu) {
        boolean b = menuDAO.addMenu(menu);
        if (b){
            stringRedisTemplate.delete("listMenuChildren");
            return true;
        }
        return false;
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ,propagation = Propagation.REQUIRED)
    public boolean delMenu(int id) {
        boolean b = menuDAO.delMenu(id);
        if (b){
            stringRedisTemplate.delete("listMenuChildren");
            return true;
        }
        return false;
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ,propagation = Propagation.REQUIRED)
    public boolean updateMenu(Menu menu) {
        boolean b = menuDAO.updateMenu(menu);
        if (b){
            stringRedisTemplate.delete("listMenuChildren");
            return true;
        }
        return false;
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ,propagation = Propagation.REQUIRED)
    public boolean switchMenu(int id, int hidden) {
        boolean b = menuDAO.switchMenu(id,hidden);
        if (b){
            stringRedisTemplate.delete("listMenuChildren");
            return true;
        }
        return false;
    }

}
