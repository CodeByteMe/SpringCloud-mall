package com.mall.cartlist.service.impl;


import com.mall.cartlist.dao.CartItemDAO;
import com.mall.cartlist.service.CartItemService;
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
}
