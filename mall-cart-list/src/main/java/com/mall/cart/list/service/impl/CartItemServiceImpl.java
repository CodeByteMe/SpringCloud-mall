package com.mall.cart.list.service.impl;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mall.cart.list.dao.CartItemDAO;
import com.mall.cart.list.service.CartItemService;
import com.mall.common.pojo.CartItem;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CartItemServiceImpl implements CartItemService {

    @Resource
    private CartItemDAO cartItemDAO;
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public List<CartItem> getCartItemByMemberId(String memberId) {
        List<CartItem> cartItems = null;
        try {
            String s = (String) stringRedisTemplate.boundHashOps("getCartItemByMemberId").get("getCartItemByMemberId-" + memberId);
            if (s == null) {
                synchronized (this) {
                    s = (String) stringRedisTemplate.boundHashOps("getCartItemByMemberId").get("getCartItemByMemberId-" + memberId);
                    if (s == null) {
                        cartItems = cartItemDAO.getCartItemByMemberId(memberId);
                        String jsonStr = mapper.writeValueAsString(cartItems);
                        stringRedisTemplate.boundHashOps("getCartItemByMemberId").put("getCartItemByMemberId-" + memberId, jsonStr);
                    }
                }
            } else {
                cartItems = mapper.readValue(s, new TypeReference<List<CartItem>>() {
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cartItems;
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
    @Override
    public boolean deleteCart(int id) {
        int i = cartItemDAO.deleteCart(id);
        if (i>0){
            stringRedisTemplate.delete("getCartItemByMemberId");
            return true;
        }else {
            return false;
        }
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
    @Override
    public boolean addCart(CartItem cartItem) {
        int i = cartItemDAO.addCart(cartItem);
        if (i>0){
            stringRedisTemplate.delete("getCartItemByMemberId");
            return true;
        }else {
            return false;
        }
    }
}
