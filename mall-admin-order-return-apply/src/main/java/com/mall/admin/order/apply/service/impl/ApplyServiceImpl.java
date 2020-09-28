package com.mall.admin.order.apply.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mall.admin.order.apply.dao.ApplyDAO;
import com.mall.admin.order.apply.service.ApplyService;
import com.mall.common.pojo.OrderReturn;
import com.mall.common.pojo.OrderReturnReason;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * ApplyServiceImpl
 *
 * @Author BessCroft
 * @Date 2020/9/26 15:18
 */
@Service
public class ApplyServiceImpl implements ApplyService {

    @Resource
    private ApplyDAO applyDAO;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public PageInfo listOrderReturn(int pageNum, int pageSize,String adminId) {
        List<OrderReturn> orderReturns = null;
        PageHelper.startPage(pageNum, pageSize);
        try {
            String s = (String) stringRedisTemplate.boundHashOps("listOrderReturn").get("adminId-" + adminId);
            if (s == null) {
                synchronized (this) {
                    s = (String) stringRedisTemplate.boundHashOps("listOrderReturn").get("adminId-" + adminId);
                    if (s == null) {
                        orderReturns = applyDAO.listOrderReturn(adminId);
                        String jsonStr = mapper.writeValueAsString(orderReturns);
                        stringRedisTemplate.boundHashOps("listOrderReturn").put("adminId-" + adminId,jsonStr);
                    }
                }
            } else {
                orderReturns = mapper.readValue(s, new TypeReference<List<OrderReturn>>() {
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        PageInfo pageInfo = new PageInfo(orderReturns);
        return pageInfo;
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ,propagation = Propagation.REQUIRED)
    public boolean deleteOrderReturn(Integer id) {
        int i = applyDAO.deleteOrderReturn(id);
        if (i>0) {
            stringRedisTemplate.delete("listOrderReturn");
            return true;
        }
        return false;
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ,propagation = Propagation.REQUIRED)
    public boolean updateApplyStatus(Integer id, Integer status) {
        int i = applyDAO.updateApplyStatus(id, status);
        if (i>0) {
            stringRedisTemplate.delete("listOrderReturn");
            return true;
        }
        return false;
    }
}
