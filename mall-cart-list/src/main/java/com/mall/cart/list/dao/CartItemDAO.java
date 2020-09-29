package com.mall.cart.list.dao;

import com.mall.common.pojo.CartItem;

import java.util.List;

public interface CartItemDAO {

    /**
     * 根据用户id查询该用户所有购物车记录
     * @param memberId
     * @return
     */
    public List<CartItem> getCartItemByMemberId(String memberId);

    /**
     * 用户根据id删除一条购物车记录
     * @param id
     * @return
     */
    public int deleteCart(int id);

    /**
     * 新增购物车记录
     * @param cartItem
     * @return
     */
    public int addCart(CartItem cartItem);

}
