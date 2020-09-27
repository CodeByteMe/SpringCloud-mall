package com.mall.admin.spike.service;

import com.mall.common.pojo.FlashPromotion;
import com.mall.common.pojo.FlashPromotionProductRelation;

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
}
