package com.mall.admin.order.list.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mall.admin.order.list.dao.OrderDAO;
import com.mall.admin.order.list.service.OrderService;
import com.mall.common.pojo.OrderDTO;
import com.mall.common.pojo.Address;
import com.mall.common.pojo.MemberUser;
import com.mall.common.pojo.Order;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public String getCompanyId(int id) {
        String companyId = "";
        try {
            String getCompanyId = (String) stringRedisTemplate.boundHashOps("getCompanyId").get("id-" + id);
            if (getCompanyId == null) {
                synchronized (this) {
                    getCompanyId = (String) stringRedisTemplate.boundHashOps("getCompanyId").get("id-" + id);
                    if (getCompanyId == null) {
                        companyId = orderDAO.getCompanyId(id);
                        String jsonStr = mapper.writeValueAsString(companyId);
                        stringRedisTemplate.boundHashOps("getCompanyId").put("id-"+id,jsonStr);
                    } else {
                        companyId = mapper.readValue(getCompanyId, String.class);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return companyId;
    }

    @Override
    public PageInfo listOrderByCompanyId(int pageNum, int pageSize, String companyId) {
        List<Order> orders = null;
        PageHelper.startPage(pageNum, pageSize);
        try {
            String s = (String) stringRedisTemplate.boundHashOps("listOrderByCompanyId").get("companyId-" + companyId);
            if (s == null) {
                synchronized (this) {
                    s = (String) stringRedisTemplate.boundHashOps("listOrderByCompanyId").get("companyId-" + companyId);
                    if (s == null) {
                        orders = orderDAO.listOrderByCompanyId(companyId);
                        String jsonStr = mapper.writeValueAsString(orders);
                        stringRedisTemplate.boundHashOps("listOrderByCompanyId").put("companyId-"+companyId,jsonStr);
                    } else {
                        orders = mapper.readValue(s, new TypeReference<List<Order>>() {
                        });
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        PageInfo pageInfo = new PageInfo(orders);
        return pageInfo;
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ,propagation = Propagation.REQUIRED)
    public boolean deleteOrderById(String orderId) {
        return orderDAO.deleteOrderById(orderId) > 0;
    }

    @Override
    public Address getAddressByAddressId(String addressId) {
        return orderDAO.getAddressByAddressId(addressId);
    }

    @Override
    public MemberUser getMemberUserByMemberId(String memberId) {
        return orderDAO.getMemberUserByMemberId(memberId);
    }

    @Override
    public List<Order> getOrderListByMemberId(String memberId) {
        return orderDAO.getOrderListByMemberId(memberId);
    }

    @Override
    public List<OrderDTO> getOrderByOrderId(String orderId) {
        return orderDAO.getOrderByOrderId(orderId);
    }
}
