package com.mall.order.add.dao;

import com.mall.common.pojo.Order;
import com.mall.common.pojo.OrderItem;

public interface OrderAddDAO {

    /**
     * 录单服务
     * @param order
     * @return
     */
    public int addOrder(Order order);

    /**
     * 添加订单商品属性
     * @param list
     * @return
     */
    public int addOrderItem(OrderItem list);
}
