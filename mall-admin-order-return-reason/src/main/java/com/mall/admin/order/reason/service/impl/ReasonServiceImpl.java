package com.mall.admin.order.reason.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mall.admin.order.reason.dao.ReasonDAO;
import com.mall.admin.order.reason.service.ReasonService;
import com.mall.common.pojo.FlashPromotionProductRelation;
import com.mall.common.pojo.Order;
import com.mall.common.pojo.OrderReturnReason;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * ReasonServiceImpl
 *
 * @Author BessCroft
 * @Date 2020/9/25 18:12
 */
@Service
public class ReasonServiceImpl implements ReasonService {

    @Resource
    private ReasonDAO reasonDAO;

    @Override
    public PageInfo listOrderReturnReason(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<OrderReturnReason> orderReturnReasons = reasonDAO.listOrderReturnReason();
        PageInfo pageInfo = new PageInfo(orderReturnReasons);
        return pageInfo;
    }
}
