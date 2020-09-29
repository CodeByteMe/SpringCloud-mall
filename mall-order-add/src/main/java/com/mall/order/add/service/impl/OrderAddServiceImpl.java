package com.mall.order.add.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mall.common.pojo.Order;
import com.mall.common.pojo.OrderItem;
import com.mall.order.add.dao.OrderAddDAO;
import com.mall.order.add.service.OrderAddService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class OrderAddServiceImpl implements OrderAddService {

    @Resource
    private OrderAddDAO orderAddDAO;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    private ObjectMapper mapper = new ObjectMapper();

    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
    @Override
    public boolean addOrder(Order order,List<OrderItem> list) {
        int i = orderAddDAO.addOrder(order);
        if (i>0) {
            for (OrderItem item:list) {
                int i1 = orderAddDAO.addOrderItem(item);
            }
            stringRedisTemplate.delete("listOrderByCompanyId");
            stringRedisTemplate.delete("getOrderListByMemberId");
            stringRedisTemplate.delete("getOrderByOrderId");
            stringRedisTemplate.delete("getOrderItemByOrderId");
            return true;
        }
        return false;
    }
}
