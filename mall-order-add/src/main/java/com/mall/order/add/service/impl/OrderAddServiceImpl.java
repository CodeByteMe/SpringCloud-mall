package com.mall.order.add.service.impl;

import com.mall.common.pojo.Order;
import com.mall.common.pojo.OrderItem;
import com.mall.order.add.dao.OrderAddDAO;
import com.mall.order.add.service.OrderAddService;
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

    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
    @Override
    public boolean addOrder(Order order,List<OrderItem> list) {
//        if(orderAddDAO.addOrder(order)>0){
//            return orderAddDAO.addOrderItem(list)>0;
//        }
        int i = orderAddDAO.addOrder(order);
        if (i>0) {
            for (OrderItem item:list) {
                int i1 = orderAddDAO.addOrderItem(item);
            }
            return true;
        }
        return false;
    }

//    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
//    @Override
//    public boolean addOrderItem(List<OrderItem> list) {
//        return orderAddDAO.addOrderItem(list)>0;
//    }
}
