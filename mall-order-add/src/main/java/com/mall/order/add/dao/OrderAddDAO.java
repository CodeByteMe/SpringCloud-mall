package com.mall.order.add.dao;

import com.mall.common.pojo.Order;
import com.mall.common.pojo.OrderItem;

public interface OrderAddDAO {

    /**
     * 录单服务
     * @param order
     * @return
     */
    public int addOrder(Order order);

    /**
     * 添加订单商品属性
     * @param list
     * @return
     */
    public int addOrderItem(OrderItem list);

    /**
     * 根据商品的套餐id查询商品库存
     * @param skuId
     * @return
     */
    public int getStock(String skuId);

    /**
     * 根据商品的套餐id修改库存
     * @param skuId
     * @return
     */
    public int updateStock(String skuId);

    /**
     * 根据商品id查询公司id
     * @param productId
     * @return
     */
    public String getCompanyId(String productId);
}
