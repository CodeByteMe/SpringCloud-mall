package com.mall.admin.spike.service;

import com.mall.common.pojo.*;

import java.util.List;
import java.util.Map;

/**
 * SpikeService
 *
 * @Author BessCroft
 * @Date 2020/9/27 10:05
 */
public interface SpikeService {
    /**
     * 新增秒杀（事务）
     * @param flashPromotion
     * @param flashPromotionProductRelation
     * @return
     */
    public boolean insertSpike(FlashPromotion flashPromotion,FlashPromotionProductRelation flashPromotionProductRelation);
    public List<Product> listProductByAdminId(String adminId);
    public List<FlashPromotion> listFlashPromotion(String adminId);
    public boolean deleteFlashPromotion(Integer id);
    public List<Map> getFlashPromotion();
    public boolean addOrder(Order order,OrderItem orderItem);
    public int getFlashPromotionCount(Integer relationId);
}
