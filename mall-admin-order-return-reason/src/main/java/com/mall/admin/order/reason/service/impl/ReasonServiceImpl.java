package com.mall.admin.order.reason.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mall.admin.order.reason.dao.ReasonDAO;
import com.mall.admin.order.reason.service.ReasonService;
import com.mall.common.pojo.FlashPromotionProductRelation;
import com.mall.common.pojo.Order;
import com.mall.common.pojo.OrderReturnReason;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ,propagation = Propagation.REQUIRED)
    public boolean updateStatus(Integer status, Integer id) {
        return reasonDAO.updateStatus(status, id) > 0;
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ,propagation = Propagation.REQUIRED)
    public boolean deleteReason(Integer id) {
        return reasonDAO.deleteReason(id) > 0;
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ,propagation = Propagation.REQUIRED)
    public boolean insertReason(OrderReturnReason reason) {
        return reasonDAO.insertReason(reason) > 0;
    }
}
