package com.mall.admin.product.attribute.category.ud.service.impl;

import com.mall.admin.product.attribute.category.ud.dao.ProductAttributeCategoryUdDAO;
import com.mall.admin.product.attribute.category.ud.service.ProductAttributeCategoryUdService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class ProductAttributeCategoryUdServiceImpl implements ProductAttributeCategoryUdService {

    @Resource
    private ProductAttributeCategoryUdDAO productAttributeCategoryUdDAO;
    public void setProductAttributeCategoryUdDAO(ProductAttributeCategoryUdDAO productAttributeCategoryUdDAO) {
        this.productAttributeCategoryUdDAO = productAttributeCategoryUdDAO;
    }
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ,propagation = Propagation.REQUIRED)
    public boolean productAttributeCategoryUpdate(String name, Integer id) {
        boolean b = productAttributeCategoryUdDAO.productAttributeCategoryUpdate(name, id);
        if (b){
            stringRedisTemplate.delete("productAttributeCategoryList");
            stringRedisTemplate.delete("productAttributeCategoryListAll");
            stringRedisTemplate.delete("productAttributeListById");
            stringRedisTemplate.delete("productParameterListById");
        }
        return b;
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ,propagation = Propagation.REQUIRED)
    public boolean productAttributeCategoryDel(Integer id) {
        boolean b = productAttributeCategoryUdDAO.productAttributeCategoryDel(id);
        if (b){
            stringRedisTemplate.delete("productAttributeCategoryList");
            stringRedisTemplate.delete("productAttributeCategoryListAll");
            stringRedisTemplate.delete("productAttributeListById");
            stringRedisTemplate.delete("productParameterListById");
        }
        return b;
    }
}
