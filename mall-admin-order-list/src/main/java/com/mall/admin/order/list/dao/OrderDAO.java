package com.mall.admin.order.list.dao;

import com.mall.common.pojo.*;

import java.util.List;

/**
 * OrderDAO
 *
 * @Author BessCroft
 * @Date 2020/9/24 15:07
 */
public interface OrderDAO {
    /**
     * 根据id查询公司id
     * @param id
     * @return
     */
    public String getCompanyId(int id);
    /**
     * 根据公司id查询所有的订单
     * @param companyId
     * @return
     */
    public List<Order> listOrderByCompanyId(String companyId);

    /**
     * 根据订单id删除订单
     * @param orderId
     * @return
     */
    public int deleteOrderById(String orderId);

    /**
     * 根据订单中的地址id查询当前订单的地址
     * @param addressId
     * @return
     */
    public Address getAddressByAddressId(String addressId);

    /**
     * 根据订单中的用户id查询用户信息
     * @param memberId
     * @return
     */
    public MemberUser getMemberUserByMemberId(String memberId);

    /**
     * 根据前台用户id查询该用户所有订单记录
     * @param memberId
     * @return
     */
    public List<Order> getOrderListByMemberId(String memberId);

    /**
     * 根据订单id查询该订单的详细信息
     * @param orderId
     * @return
     */
    public List<OrderDTO> getOrderByOrderId(String orderId);

    /**
     * 根据订单id查询该订单的详细信息
     * @param orderId
     * @return
     */
    public List<OrderItem> getOrderItemByOrderId(String orderId);
}
