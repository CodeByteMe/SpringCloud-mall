package com.mall.order.add.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mall.common.pojo.CartItem;
import com.mall.common.pojo.Order;
import com.mall.common.pojo.OrderItem;
import com.mall.order.add.dao.OrderAddDAO;
import com.mall.order.add.service.OrderAddService;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

@Service
public class OrderAddServiceImpl implements OrderAddService {

    @Resource
    private OrderAddDAO orderAddDAO;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private RedissonClient redissonClient;

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
    public boolean addOrder(JSONObject order,String memberId) {
        String orderId = UUID.randomUUID().toString().replace("-", "");
        String addressId1 = order.getString("addressId");
        String money = order.getString("money");
        double money1 = Double.parseDouble(money);
        String num = order.getString("num");
        int num1 = Integer.parseInt(num);
        List<CartItem> jsonStrList = Arrays.asList( order.getObject("jsonStrList",CartItem[].class));
        List<OrderItem> orderItems = new ArrayList<>();
        String productId = "";
        for (CartItem cartItem : jsonStrList) {
            OrderItem orderItem=new OrderItem();
            orderItem.setOrderId(orderId);
            orderItem.setProductId(cartItem.getProductId());
            orderItem.setProductPic(cartItem.getProductPic());
            orderItem.setProductName(cartItem.getProductName());
            orderItem.setProductPrice(cartItem.getPrice());
            orderItem.setProductQuantity(cartItem.getQuantity());
            orderItem.setProductSkuId(cartItem.getProductSkuId());
            productId = cartItem.getProductId();
            String lockKey = cartItem.getProductId() + "-LOCK";
            RLock lock = redissonClient.getLock(lockKey);
            lock.lock();
            try {
                int stock = orderAddDAO.getStock(cartItem.getProductSkuId());
                if (stock > cartItem.getQuantity()) {
                    int i = orderAddDAO.addOrderItem(orderItem);
                    if (i > 0) {
                        int j = orderAddDAO.updateStock(cartItem.getProductSkuId());
                    }
                }
            } finally {
                lock.unlock();
            }
        }
        String companyId = orderAddDAO.getCompanyId(productId);
        int i = orderAddDAO.addOrder(new Order(0,orderId,memberId,new Date(),"lisi",money1,money1,0,0,0,0,addressId1,7,null,null,0,null,null,null,null,companyId));
        if (i > 0) {
            stringRedisTemplate.delete("listOrderByCompanyId");
            stringRedisTemplate.delete("getOrderListByMemberId");
            stringRedisTemplate.delete("getOrderByOrderId");
            stringRedisTemplate.delete("getOrderItemByOrderId");
            return true;
        }
        return false;
    }
}
