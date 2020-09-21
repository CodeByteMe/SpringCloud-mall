package com.mall.product.list.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mall.common.pojo.Product;
import com.mall.product.list.dao.ProductListDAO;
import com.mall.product.list.service.ProductListService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/*
 *   作者：官宣轩
 *   日期：2020-09-21
 */
@Service
public class ProductListServiceImpl implements ProductListService {

    @Resource
    private ProductListDAO productListDAO;
    public void setProductListDAO(ProductListDAO productListDAO) {
        this.productListDAO = productListDAO;
    }

    @Override
    public PageInfo productList(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Product> products = productListDAO.productList();
        PageInfo pageInfo = new PageInfo(products);
        return pageInfo;
    }
}
