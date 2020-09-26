package com.mall.admin.product.add.service.impl;

import com.mall.admin.product.add.dao.ProductAddDAO;
import com.mall.admin.product.add.service.ProductAddService;
import com.mall.common.pojo.Product;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/*
 *   作者：官宣轩
 *   日期：2020-09-25
 */
@Service
public class ProductAddServiceImpl implements ProductAddService {

    @Resource
    private ProductAddDAO productAddDAO;
    public void setProductAddDAO(ProductAddDAO productAddDAO) {
        this.productAddDAO = productAddDAO;
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ,propagation = Propagation.REQUIRED)
    public boolean productAdd(Product product) {
        return productAddDAO.productAdd(product);
    }

    @Override
    public String searchCompanyIdByAdminUUID(String adminId) {
        return productAddDAO.searchCompanyIdByAdminUUID(adminId);
    }
}
