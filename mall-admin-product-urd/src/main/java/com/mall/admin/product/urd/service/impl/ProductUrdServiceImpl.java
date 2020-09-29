package com.mall.admin.product.urd.service.impl;

import com.mall.admin.product.urd.dao.ProductUrdDAO;
import com.mall.admin.product.urd.service.ProductUrdService;
import com.mall.common.pojo.Product;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/*
 *   作者：官宣轩
 *   日期：2020-09-26
 */
@Service
public class ProductUrdServiceImpl implements ProductUrdService {

    @Resource
    private ProductUrdDAO productUrdDAO;
    public void setProductUrdDAO(ProductUrdDAO productUrdDAO) {
        this.productUrdDAO = productUrdDAO;
    }

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ,propagation = Propagation.REQUIRED)
    public boolean productUpdate(Product product) {
        boolean b = productUrdDAO.productUpdate(product);
        if (b){
            stringRedisTemplate.delete("productList");
            stringRedisTemplate.delete("productDetailByProductId");
            stringRedisTemplate.delete("getSkuStockByProductId");
            stringRedisTemplate.delete("producAlltList");
        }
        return productUrdDAO.productUpdate(product);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ,propagation = Propagation.REQUIRED)
    public boolean productDel(String productId) {
        boolean b = productUrdDAO.productDel(productId);
        if (b){
            stringRedisTemplate.delete("productList");
            stringRedisTemplate.delete("productDetailByProductId");
            stringRedisTemplate.delete("getSkuStockByProductId");
            stringRedisTemplate.delete("producAlltList");
        }
        return b;
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ,propagation = Propagation.REQUIRED)
    public boolean productStatus(String productId, Integer publishStatus) {
        boolean b = productUrdDAO.productStatus(productId, publishStatus);
        if (b){
            stringRedisTemplate.delete("productList");
            stringRedisTemplate.delete("productDetailByProductId");
            stringRedisTemplate.delete("getSkuStockByProductId");
            stringRedisTemplate.delete("producAlltList");
        }
        return b;
    }
}
