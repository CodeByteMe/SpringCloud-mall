package com.mall.cart.list.service.impl;


import com.mall.cart.list.dao.CartItemDAO;
import com.mall.cart.list.service.CartItemService;
import com.mall.common.pojo.CartItem;
import org.springframework.stereotype.Service;

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

    @Override
    public boolean deleteCart(int id) {
        return cartItemDAO.deleteCart(id) > 0;
    }
}
