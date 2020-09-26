package com.mall.cartlist.dao;

import com.mall.common.pojo.CartItem;

import java.util.List;

public interface CartItemDAO {

    /**
     * 根据用户id查询该用户所有购物车记录
     * @param memberId
     * @return
     */
    public List<CartItem> getCartItemByMemberId(String memberId);
}
