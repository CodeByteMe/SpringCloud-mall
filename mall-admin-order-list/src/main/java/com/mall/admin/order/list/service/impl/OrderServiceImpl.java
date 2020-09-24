package com.mall.admin.order.list.service.impl;

import com.mall.admin.order.list.dao.OrderDAO;
import com.mall.admin.order.list.service.OrderService;
import com.mall.common.pojo.Order;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * OrderServiceImpl
 *
 * @Author BessCroft
 * @Date 2020/9/24 17:39
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderDAO orderDAO;

    @Override
    public String getCompanyId(int id) {
        return orderDAO.getCompanyId(id);
    }

    @Override
    public List<Order> listOrderByCompanyId(String companyId) {
        return orderDAO.listOrderByCompanyId(companyId);
    }
}
