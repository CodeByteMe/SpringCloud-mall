package com.mall.admin.order.reason.service;

import com.github.pagehelper.PageInfo;
import com.mall.common.pojo.FlashPromotionProductRelation;

import java.util.List;

/**
 * ReasonService
 *
 * @Author BessCroft
 * @Date 2020/9/25 18:12
 */
public interface ReasonService {
    public PageInfo listOrderReturnReason(int pageNum, int pageSize);
}
