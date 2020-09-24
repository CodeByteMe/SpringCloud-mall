package com.mall.admin.order.list.dao;

import com.mall.common.pojo.Order;

import java.util.List;

/**
 * OrderDAO
 *
 * @Author BessCroft
 * @Date 2020/9/24 15:07
 */
public interface OrderDAO {
    /**
     * 根据id查询公司id
     * @param id
     * @return
     */
    public String getCompanyId(int id);
    /**
     * 根据公司id查询所有的订单
     * @param companyId
     * @return
     */
    public List<Order> listOrderByCompanyId(String companyId);
}
