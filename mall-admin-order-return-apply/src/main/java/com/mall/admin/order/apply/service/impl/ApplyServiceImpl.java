package com.mall.admin.order.apply.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mall.admin.order.apply.dao.ApplyDAO;
import com.mall.admin.order.apply.service.ApplyService;
import com.mall.common.pojo.OrderReturn;
import com.mall.common.pojo.OrderReturnReason;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * ApplyServiceImpl
 *
 * @Author BessCroft
 * @Date 2020/9/26 15:18
 */
@Service
public class ApplyServiceImpl implements ApplyService {

    @Resource
    private ApplyDAO applyDAO;

    @Override
    public PageInfo listOrderReturn(int pageNum, int pageSize,String adminId) {
        PageHelper.startPage(pageNum, pageSize);
        List<OrderReturn> orderReturns = applyDAO.listOrderReturn(adminId);
        PageInfo pageInfo = new PageInfo(orderReturns);
        return pageInfo;
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ,propagation = Propagation.REQUIRED)
    public boolean deleteOrderReturn(Integer id) {
        return applyDAO.deleteOrderReturn(id) > 0;
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ,propagation = Propagation.REQUIRED)
    public boolean updateApplyStatus(Integer id, Integer status) {
        return applyDAO.updateApplyStatus(id, status) > 0;
    }
}
