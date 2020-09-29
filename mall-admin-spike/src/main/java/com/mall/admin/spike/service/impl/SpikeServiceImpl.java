package com.mall.admin.spike.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mall.admin.spike.dao.SpikeDAO;
import com.mall.admin.spike.service.SpikeService;
import com.mall.common.pojo.*;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * SpikeServiceImpl
 *
 * @Author BessCroft
 * @Date 2020/9/27 10:19
 */
@Service
public class SpikeServiceImpl implements SpikeService {

    @Resource
    private SpikeDAO spikeDAO;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    private ObjectMapper mapper = new ObjectMapper();

    /**
     * 新增秒杀（事务）
     * @param flashPromotion
     * @param flashPromotionProductRelation
     * @return
     */
    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ,propagation = Propagation.REQUIRED)
    public boolean insertSpike(FlashPromotion flashPromotion, FlashPromotionProductRelation flashPromotionProductRelation) {
        int i = spikeDAO.insertSpike(flashPromotion);
        if (i>0) {
            Integer id = flashPromotion.getId();
            flashPromotionProductRelation.setFlashPromotionId(id);
            int i1 = spikeDAO.insertSpikeProduct(flashPromotionProductRelation);
            if (i1>0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Product> listProductByAdminId(String adminId) {
        return spikeDAO.listProductByAdminId(adminId);
    }

    @Override
    public List<FlashPromotion> listFlashPromotion(String adminId) {
        return spikeDAO.listFlashPromotion(adminId);
    }

    @Override
    public boolean deleteFlashPromotion(Integer id) {
        return spikeDAO.deleteFlashPromotion(id) > 0;
    }

    @Override
    public List<Map> getFlashPromotion() {
        return spikeDAO.getFlashPromotion();
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
    public boolean addOrder(Order order,OrderItem orderItem) {
        int i = spikeDAO.addOrder(order);
        if (i > 0) {
            int j = spikeDAO.addOrderItem(orderItem);
            if (j > 0) {
                int k = spikeDAO.updateFlashPromotionCount(orderItem.getProductId());
                if (k > 0) {
                    stringRedisTemplate.delete("listOrderByCompanyId");
                    stringRedisTemplate.delete("getOrderListByMemberId");
                    stringRedisTemplate.delete("getOrderByOrderId");
                    stringRedisTemplate.delete("getOrderItemByOrderId");
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public int getFlashPromotionCount(String productId) {
        return spikeDAO.getFlashPromotionCount(productId);
    }
}
