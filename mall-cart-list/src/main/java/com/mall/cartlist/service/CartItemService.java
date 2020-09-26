package com.mall.cartlist.service;

import com.mall.common.pojo.CartItem;

import java.util.List;

public interface CartItemService {

    public List<CartItem> getCartItemByMemberId(String memberId);
}
