package com.mall.admin.order.list.service;

import com.mall.common.pojo.Order;

import java.util.List;

/**
 * OrderService
 *
 * @Author BessCroft
 * @Date 2020/9/24 17:38
 */
public interface OrderService {
    public String getCompanyId(int id);
    public List<Order> listOrderByCompanyId(String companyId);
}
