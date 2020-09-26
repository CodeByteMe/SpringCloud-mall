package com.mall.admin.order.reason.dao;

import com.mall.common.pojo.FlashPromotionProductRelation;
import com.mall.common.pojo.OrderReturnReason;

import java.util.List;

/**
 * ReasonDAO
 *
 * @Author BessCroft
 * @Date 2020/9/25 17:11
 */
public interface ReasonDAO {
    /**
     * 查询FlashPromotionProductRelation表中的所有信息
     * @return
     */
    public List<OrderReturnReason> listOrderReturnReason();
}
