package com.mall.order.add.service;

import com.mall.common.pojo.Order;
import com.mall.common.pojo.OrderItem;

import java.util.List;

public interface OrderAddService {
    public boolean addOrder(Order order,List<OrderItem> list);
//    public boolean addOrderItem(List<OrderItem> list);
}
