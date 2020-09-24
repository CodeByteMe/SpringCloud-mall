package com.mall.admin.product.attribute.category.list.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mall.admin.product.attribute.category.list.dao.ProductAttributeCategoryListDAO;
import com.mall.admin.product.attribute.category.list.service.ProductAttributeCategoryListService;
import com.mall.common.pojo.ProductAttributeCategory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/*
 *   作者：官宣轩
 *   日期：2020-09-24
 */
@Service
public class ProductAttributeCategoryListServiceImpl implements ProductAttributeCategoryListService {

    @Resource
    private ProductAttributeCategoryListDAO productAttributeCategoryListDAO;
    public void setProductAttributeCategoryListDAO(ProductAttributeCategoryListDAO productAttributeCategoryListDAO) {
        this.productAttributeCategoryListDAO = productAttributeCategoryListDAO;
    }

    @Override
    public PageInfo productAttributeCategoryList(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<ProductAttributeCategory> productAttributeCategories = productAttributeCategoryListDAO.productAttributeCategoryList();
        PageInfo pageInfo = new PageInfo(productAttributeCategories);
        return pageInfo;
    }
}
