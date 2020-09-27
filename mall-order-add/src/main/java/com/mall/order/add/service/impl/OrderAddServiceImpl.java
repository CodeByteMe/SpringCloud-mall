package com.mall.order.add.service.impl;

import com.mall.common.pojo.Order;
import com.mall.order.add.dao.OrderAddDAO;
import com.mall.order.add.service.OrderAddService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class OrderAddServiceImpl implements OrderAddService {

    @Resource
    private OrderAddDAO orderAddDAO;

    @Override
    public boolean addOrder(Order order) {
        return orderAddDAO.addOrder(order)>0;
    }
}
