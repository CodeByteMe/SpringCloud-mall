package com.mall.admin.product.category.add.service.impl;

import com.mall.admin.product.category.add.dao.ProductCategoryDAO;
import com.mall.admin.product.category.add.service.ProductCategoryService;
import com.mall.common.pojo.ProductCategory;
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

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ,propagation = Propagation.REQUIRED)
    public boolean productCategoryAdd(ProductCategory productCategory) {
        return productCategoryDAO.productCategoryAdd(productCategory);
    }

}
