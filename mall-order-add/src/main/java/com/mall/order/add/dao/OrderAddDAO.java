package com.mall.order.add.dao;

import com.mall.common.pojo.Order;

public interface OrderAddDAO {

    /**
     * 录单服务
     * @param order
     * @return
     */
    public int addOrder(Order order);
}
