package com.mall.admin.spike.dao;

import com.mall.common.pojo.FlashPromotion;
import com.mall.common.pojo.FlashPromotionProductRelation;

/**
 * SpikeDAO
 *
 * @Author BessCroft
 * @Date 2020/9/27 10:04
 */
public interface SpikeDAO {
    /**
     * 新增秒杀信息
     * @param flashPromotion
     * @return
     */
    public int insertSpike(FlashPromotion flashPromotion);

    /**
     * 新增秒杀绑定的商品信息
     * @param flashPromotionProductRelation
     * @return
     */
    public int insertSpikeProduct(FlashPromotionProductRelation flashPromotionProductRelation);
}
