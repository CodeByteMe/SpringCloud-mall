package com.mall.admin.product.category.add.service.impl;

import com.mall.admin.product.category.add.dao.ProductCategoryDAO;
import com.mall.admin.product.category.add.service.ProductCategoryService;
import com.mall.common.pojo.ProductCategory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/*
 *   作者：官宣轩
 *   日期：2020-09-27
 */
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {



    @Resource
    private ProductCategoryDAO productCategoryDAO;
    public void setProductCategoryDAO(ProductCategoryDAO productCategoryDAO) {
        this.productCategoryDAO = productCategoryDAO;
    }

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ,propagation = Propagation.REQUIRED)
    public boolean productCategoryAdd(ProductCategory productCategory) {
        boolean b = productCategoryDAO.productCategoryAdd(productCategory);
        if (b){
            stringRedisTemplate.delete("productCategoryList");
            stringRedisTemplate.delete("productCategoryListByParentId");
            stringRedisTemplate.delete("productCategoryOptions");
        }
        return b;
    }

}
