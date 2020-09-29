package com.mall.admin.order.list.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mall.admin.order.list.dao.OrderDAO;
import com.mall.admin.order.list.service.OrderService;
import com.mall.common.pojo.*;
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
            String s = (String) stringRedisTemplate.boundHashOps("getCompanyId").get("id-" + id);
            if (s == null) {
                synchronized (this) {
                    s = (String) stringRedisTemplate.boundHashOps("getCompanyId").get("id-" + id);
                    if (s == null) {
                        companyId = orderDAO.getCompanyId(id);
                        String jsonStr = mapper.writeValueAsString(companyId);
                        stringRedisTemplate.boundHashOps("getCompanyId").put("id-"+id,jsonStr);
                    }
                }
            } else {
                companyId = mapper.readValue(s, String.class);
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
            stringRedisTemplate.delete("listOrderByCompanyId");
            String s = (String) stringRedisTemplate.boundHashOps("listOrderByCompanyId").get("companyId-" + companyId + "-pageNum-" + pageNum);
            if (s == null) {
                synchronized (this) {
                    s = (String) stringRedisTemplate.boundHashOps("listOrderByCompanyId").get("companyId-" + companyId + "-pageNum-" + pageNum);
                    if (s == null) {
                        orders = orderDAO.listOrderByCompanyId(companyId);
                        String jsonStr = mapper.writeValueAsString(orders);
                        stringRedisTemplate.boundHashOps("listOrderByCompanyId").put("companyId-"+companyId + "-pageNum-" + pageNum,jsonStr);
                    }
                }
            } else {
                orders = mapper.readValue(s, new TypeReference<List<Order>>() {
                });
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
        int i = orderDAO.deleteOrderById(orderId);
        if (i > 0) {
            stringRedisTemplate.delete("listOrderByCompanyId");
            stringRedisTemplate.delete("getOrderListByMemberId");
            stringRedisTemplate.delete("getOrderByOrderId");
            stringRedisTemplate.delete("getOrderItemByOrderId");
            return true;
        }
        return false;
    }

    @Override
    public Address getAddressByAddressId(String addressId) {
        Address address = null;
        try {
            String s = (String) stringRedisTemplate.boundHashOps("getAddressByAddressId").get("addressId-" + addressId);
            if (s == null) {
                synchronized (this) {
                    s = (String) stringRedisTemplate.boundHashOps("getAddressByAddressId").get("addressId-" + addressId);
                    if (s == null) {
                        address = orderDAO.getAddressByAddressId(addressId);
                        String jsonStr = mapper.writeValueAsString(address);
                        stringRedisTemplate.boundHashOps("getAddressByAddressId").put("addressId-" + addressId,jsonStr);
                    }
                }
            } else {
                address = mapper.readValue(s, Address.class);
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return address;
    }

    @Override
    public MemberUser getMemberUserByMemberId(String memberId) {
        MemberUser memberUser = null;
        try {
            String s = (String) stringRedisTemplate.boundHashOps("getMemberUserByMemberId").get("memberId-" + memberId);
            if (s == null) {
                synchronized (this) {
                    s = (String) stringRedisTemplate.boundHashOps("getMemberUserByMemberId").get("memberId-" + memberId);
                    if (s == null) {
                        memberUser = orderDAO.getMemberUserByMemberId(memberId);
                        String jsonStr = mapper.writeValueAsString(memberUser);
                        stringRedisTemplate.boundHashOps("getMemberUserByMemberId").put("memberId-" + memberId,jsonStr);
                    }
                }
            } else {
                memberUser = mapper.readValue(s, MemberUser.class);
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return memberUser;
    }

    @Override
    public List<Order> getOrderListByMemberId(String memberId) {
        List<Order> orders = null;
        try {
            String s = (String) stringRedisTemplate.boundHashOps("getOrderListByMemberId").get("memberId-" + memberId);
            if (s == null) {
                synchronized (this) {
                    s = (String) stringRedisTemplate.boundHashOps("getOrderListByMemberId").get("memberId-" + memberId);
                    if (s == null) {
                        orders = orderDAO.getOrderListByMemberId(memberId);
                        String jsonStr = mapper.writeValueAsString(orders);
                        stringRedisTemplate.boundHashOps("getOrderListByMemberId").put("memberId-" + memberId,jsonStr);
                    }
                }
            } else {
                orders = mapper.readValue(s, new TypeReference<List<Order>>() {
                });
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return orders;
    }

    @Override
    public List<OrderDTO> getOrderByOrderId(String orderId) {
        List<OrderDTO> orderDTOS = null;
        try {
            String s = (String) stringRedisTemplate.boundHashOps("getOrderByOrderId").get("orderId-" + orderId);
            if (s == null) {
                synchronized (this) {
                    s = (String) stringRedisTemplate.boundHashOps("getOrderByOrderId").get("orderId-" + orderId);
                    if (s == null) {
                        orderDTOS = orderDAO.getOrderByOrderId(orderId);
                        String jsonStr = mapper.writeValueAsString(orderDTOS);
                        stringRedisTemplate.boundHashOps("getOrderByOrderId").put("orderId-" + orderId,jsonStr);
                    }
                }
            } else {
                orderDTOS = mapper.readValue(s, new TypeReference<List<OrderDTO>>() {
                });
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return orderDTOS;
    }

    @Override
    public List<OrderItem> getOrderItemByOrderId(String orderId) {
        List<OrderItem> orderItems = null;
        try {
            String s = (String) stringRedisTemplate.boundHashOps("getOrderItemByOrderId").get("orderId-" + orderId);
            if (s == null) {
                synchronized (this) {
                    s = (String) stringRedisTemplate.boundHashOps("getOrderItemByOrderId").get("orderId-" + orderId);
                    if (s == null) {
                        orderItems = orderDAO.getOrderItemByOrderId(orderId);
                        String jsonStr = mapper.writeValueAsString(orderItems);
                        stringRedisTemplate.boundHashOps("getOrderItemByOrderId").put("orderId-" + orderId,jsonStr);
                    }
                }
            } else {
                orderItems = mapper.readValue(s, new TypeReference<List<OrderItem>>() {
                });
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return orderItems;
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
    @Override
    public boolean updateStatus(String orderId, int status, int payType) {
        return orderDAO.updateStatus(orderId, status, payType)>0;
    }
}
