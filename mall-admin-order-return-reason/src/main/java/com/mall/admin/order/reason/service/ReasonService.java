package com.mall.admin.order.reason.service;

import com.github.pagehelper.PageInfo;
import com.mall.common.pojo.OrderReturnReason;

/**
 * ReasonService
 *
 * @Author BessCroft
 * @Date 2020/9/25 18:12
 */
public interface ReasonService {
    public PageInfo listOrderReturnReason(int pageNum, int pageSize);
    public boolean updateStatus(Integer status,Integer id);
    public boolean deleteReason(Integer id);
    public boolean insertReason(OrderReturnReason reason);
}
