package com.mall.admin.product.attribute.category.add.service.impl;

import com.mall.admin.product.attribute.category.add.dao.ProductAttributeCategoryAddDAO;
import com.mall.admin.product.attribute.category.add.service.ProductAttributeCategoryAddService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class ProductAttributeCategoryAddServiceImpl implements ProductAttributeCategoryAddService {

    @Resource
    private ProductAttributeCategoryAddDAO productAttributeCategoryAddDAO;
    public void setProductAttributeCategoryAddDAO(ProductAttributeCategoryAddDAO productAttributeCategoryAddDAO) {
        this.productAttributeCategoryAddDAO = productAttributeCategoryAddDAO;
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ,propagation = Propagation.REQUIRED)
    public boolean productAttributeCategoryAdd(String name) {
        return productAttributeCategoryAddDAO.productAttributeCategoryAdd(name);
    }
}
