package com.mall.cart.list.service;

import com.mall.common.pojo.CartItem;

import java.util.List;

public interface CartItemService {

    public List<CartItem> getCartItemByMemberId(String memberId);
    public boolean deleteCart(int id);
    public boolean addCart(CartItem cartItem);
}
