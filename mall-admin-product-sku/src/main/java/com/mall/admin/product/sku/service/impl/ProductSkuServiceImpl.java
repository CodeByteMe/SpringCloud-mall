package com.mall.admin.product.sku.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mall.admin.product.sku.dao.ProductSkuDAO;
import com.mall.admin.product.sku.service.ProductSkuService;
import com.mall.common.pojo.SkuStock;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/*
 *   作者：官宣轩
 *   日期：2020-09-26
 */
@Service
public class ProductSkuServiceImpl implements ProductSkuService {

    @Resource
    private ProductSkuDAO productSkuDAO;
    public void setProductSkuDAO(ProductSkuDAO productSkuDAO) {
        this.productSkuDAO = productSkuDAO;
    }

    @Resource
    private StringRedisTemplate stringRedisTemplate;
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public List<SkuStock> productSkuList(String productId) {
        List<SkuStock> skuStocks = null;
        try {
            stringRedisTemplate.delete("productSkuList");
            String s = (String) stringRedisTemplate.boundHashOps("productSkuList").get("productSkuList-" + productId);
            if (s == null) {
                synchronized (this) {
                    s = (String) stringRedisTemplate.boundHashOps("productSkuList").get("productSkuList-" + productId);
                    if (s == null) {
                        skuStocks = productSkuDAO.productSkuList(productId);
                        String jsonStr = mapper.writeValueAsString(skuStocks);
                        stringRedisTemplate.boundHashOps("productSkuList").put("productSkuList-" + productId, jsonStr);
                    }
                }
            } else {
                skuStocks = mapper.readValue(s, new TypeReference<List<SkuStock>>() {
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return skuStocks;
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ,propagation = Propagation.REQUIRED)
    public boolean productSkuAdd(SkuStock skuStock) {
        boolean b = productSkuDAO.productSkuAdd(skuStock);
        if (b){
            stringRedisTemplate.delete("productSkuList");
            stringRedisTemplate.delete("getSkuStockByProductId");
        }
        return b;
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ,propagation = Propagation.REQUIRED)
    public boolean productSkuDel(String skuId) {
        boolean b = productSkuDAO.productSkuDel(skuId);
        if (b){
            stringRedisTemplate.delete("productSkuList");
            stringRedisTemplate.delete("getSkuStockByProductId");
        }
        return b;
    }
}
