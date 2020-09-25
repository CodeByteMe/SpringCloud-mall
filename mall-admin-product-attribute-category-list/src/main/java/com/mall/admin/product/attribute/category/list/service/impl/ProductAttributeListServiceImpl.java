package com.mall.admin.product.attribute.category.list.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mall.admin.product.attribute.category.list.dao.ProductAttributeListDAO;
import com.mall.admin.product.attribute.category.list.service.ProductAttributeListService;
import com.mall.common.pojo.ProductAttribute;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/*
 *   作者：官宣轩
 *   日期：2020-09-24
 */
@Service
public class ProductAttributeListServiceImpl implements ProductAttributeListService {

    @Resource
    private ProductAttributeListDAO productAttributeListDAO;
    public void setProductAttributeListDAO(ProductAttributeListDAO productAttributeListDAO) {
        this.productAttributeListDAO = productAttributeListDAO;
    }

    @Override
    public PageInfo productAttributeListById(int productAttributeCategoryId,int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<ProductAttribute> productAttributes = productAttributeListDAO.productAttributeListById(productAttributeCategoryId);
        PageInfo pageInfo = new PageInfo(productAttributes);
        return pageInfo;
    }

    @Override
    public PageInfo productParameterListById(int productAttributeCategoryId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<ProductAttribute> productAttributes = productAttributeListDAO.productParameterListById(productAttributeCategoryId);
        PageInfo pageInfo = new PageInfo(productAttributes);
        return pageInfo;
    }
}
