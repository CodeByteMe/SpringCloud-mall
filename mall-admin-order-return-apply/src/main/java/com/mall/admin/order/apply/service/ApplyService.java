package com.mall.admin.order.apply.service;

import com.github.pagehelper.PageInfo;

/**
 * ApplyService
 *
 * @Author BessCroft
 * @Date 2020/9/26 15:18
 */
public interface ApplyService {
    public PageInfo listOrderReturn(int pageNum, int pageSize,String adminId);
    public boolean deleteOrderReturn(Integer id);
    public boolean updateApplyStatus(Integer id,Integer status);
}
