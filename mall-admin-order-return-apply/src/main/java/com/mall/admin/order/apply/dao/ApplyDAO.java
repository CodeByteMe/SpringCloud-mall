package com.mall.admin.order.apply.dao;

import com.mall.common.pojo.OrderReturn;

import java.util.List;

/**
 * ApplyDAO
 *
 * @Author BessCroft
 * @Date 2020/9/25 20:04
 */
public interface ApplyDAO {
    /**
     * 查询所有的订单退货申请（只能查询自己公司的，自己权限有的）
     * @return
     */
    public List<OrderReturn> listOrderReturn(String adminId);
}
