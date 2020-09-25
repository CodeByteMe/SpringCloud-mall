package com.mall.admin.order.list.service;

import com.github.pagehelper.PageInfo;
import com.mall.common.pojo.Address;
import com.mall.common.pojo.MemberUser;
import com.mall.common.pojo.Order;

import java.util.List;

/**
 * OrderService
 *
 * @Author BessCroft
 * @Date 2020/9/24 17:38
 */
public interface OrderService {
    public String getCompanyId(int id);
    public PageInfo listOrderByCompanyId(int pageNum, int pageSize, String companyId);
    public boolean deleteOrderById(String orderId);
    public Address getAddressByAddressId(String addressId);
    public MemberUser getMemberUserByMemberId(String memberId);
}
