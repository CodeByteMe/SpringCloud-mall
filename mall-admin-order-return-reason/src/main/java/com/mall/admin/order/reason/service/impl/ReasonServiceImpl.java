package com.mall.admin.order.reason.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mall.admin.order.reason.dao.ReasonDAO;
import com.mall.admin.order.reason.service.ReasonService;
import com.mall.common.pojo.FlashPromotionProductRelation;
import com.mall.common.pojo.Order;
import com.mall.common.pojo.OrderReturnReason;
import org.springframework.data.redis.core.StringRedisTemplate;
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

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public PageInfo listOrderReturnReason(int pageNum, int pageSize) {
        List<OrderReturnReason> orderReturnReasons = null;
        PageHelper.startPage(pageNum, pageSize);
        try {
            stringRedisTemplate.delete("listOrderReturnReason");
            String s = (String) stringRedisTemplate.boundHashOps("listOrderReturnReason").get("listOrderReturnReason" + pageNum);
            if (s == null) {
                synchronized (this) {
                    s = (String) stringRedisTemplate.boundHashOps("listOrderReturnReason").get("listOrderReturnReason" + pageNum);
                    if (s == null) {
                        orderReturnReasons = reasonDAO.listOrderReturnReason();
                        String jsonStr = mapper.writeValueAsString(orderReturnReasons);
                        stringRedisTemplate.boundHashOps("listOrderReturnReason").put("listOrderReturnReason" + pageNum,jsonStr);
                    }
                }
            } else {
                orderReturnReasons = mapper.readValue(s, new TypeReference<List<OrderReturnReason>>() {
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        PageInfo pageInfo = new PageInfo(orderReturnReasons);
        return pageInfo;
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ,propagation = Propagation.REQUIRED)
    public boolean updateStatus(Integer status, Integer id) {
        int i = reasonDAO.updateStatus(status, id);
        if (i > 0) {
            stringRedisTemplate.delete("listOrderReturnReason");
            return true;
        }
        return false;
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ,propagation = Propagation.REQUIRED)
    public boolean deleteReason(Integer id) {
        int i = reasonDAO.deleteReason(id);
        if (i > 0) {
            stringRedisTemplate.delete("listOrderReturnReason");
            return true;
        }
        return false;
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ,propagation = Propagation.REQUIRED)
    public boolean insertReason(OrderReturnReason reason) {
        int i = reasonDAO.insertReason(reason);
        if (i > 0) {
            stringRedisTemplate.delete("listOrderReturnReason");
            return true;
        }
        return false;
    }
}
