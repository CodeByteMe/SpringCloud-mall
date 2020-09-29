package com.mall.admin.product.category.ud.service.impl;

import com.mall.admin.product.category.ud.dao.ProductCategoryDAO;
import com.mall.admin.product.category.ud.service.ProductCategoryService;
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
    public boolean productCategoryDel(Integer productCategoryId) {
        boolean b = productCategoryDAO.productCategoryDel(productCategoryId);
        if (b){
            stringRedisTemplate.delete("productCategoryList");
            stringRedisTemplate.delete("productCategoryListByParentId");
            stringRedisTemplate.delete("productCategoryOptions");
        }
        return b;
    }

    @Override
    public boolean productCategoryUpdate(ProductCategory productCategory) {
        if (productCategory.getParentId()==0){
            productCategory.setLevel(0);
        }else {
            productCategory.setLevel(1);
        }
        boolean b = productCategoryDAO.productCategoryUpdate(productCategory);
        if (b){
            stringRedisTemplate.delete("productCategoryList");
            stringRedisTemplate.delete("productCategoryListByParentId");
            stringRedisTemplate.delete("productCategoryOptions");
        }
        return b;
    }
}
