package com.mall.admin.product.add.service.impl;

import com.mall.admin.product.add.dao.ProductAddDAO;
import com.mall.admin.product.add.service.ProductAddService;
import com.mall.common.pojo.Product;
import org.springframework.stereotype.Service;

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
    public boolean productAdd(Product product) {
        return productAddDAO.productAdd(product);
    }
}
