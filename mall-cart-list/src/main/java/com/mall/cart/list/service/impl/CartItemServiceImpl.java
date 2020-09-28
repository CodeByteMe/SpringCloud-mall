package com.mall.cart.list.service.impl;


import com.mall.cart.list.dao.CartItemDAO;
import com.mall.cart.list.service.CartItemService;
import com.mall.common.pojo.CartItem;
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

    @Override
    public List<CartItem> getCartItemByMemberId(String memberId) {
        return cartItemDAO.getCartItemByMemberId(memberId);
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
    @Override
    public boolean deleteCart(int id) {
        return cartItemDAO.deleteCart(id) > 0;
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
    @Override
    public boolean addCart(CartItem cartItem) {
        return cartItemDAO.addCart(cartItem)>0;
    }
}
