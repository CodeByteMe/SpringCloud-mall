package com.mall.admin.product.attribute.category.ud.service.impl;

import com.mall.admin.product.attribute.category.ud.dao.ProductAttributeCategoryUdDAO;
import com.mall.admin.product.attribute.category.ud.service.ProductAttributeCategoryUdService;
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


    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ,propagation = Propagation.REQUIRED)
    public boolean productAttributeCategoryUpdate(String name, Integer id) {
        return productAttributeCategoryUdDAO.productAttributeCategoryUpdate(name,id);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ,propagation = Propagation.REQUIRED)
    public boolean productAttributeCategoryDel(Integer id) {
        return productAttributeCategoryUdDAO.productAttributeCategoryDel(id);
    }
}
