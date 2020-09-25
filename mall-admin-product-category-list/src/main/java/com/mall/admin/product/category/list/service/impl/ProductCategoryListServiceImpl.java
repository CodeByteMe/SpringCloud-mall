package com.mall.admin.product.category.list.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mall.admin.product.category.list.dao.ProductCategoryListDAO;
import com.mall.admin.product.category.list.service.ProductCategoryListService;
import com.mall.common.pojo.ProductCategory;
import com.mall.common.vo.OptionsVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/*
 *   作者：官宣轩
 *   日期：2020-09-24
 */
@Service
public class ProductCategoryListServiceImpl implements ProductCategoryListService {

    @Resource
    private ProductCategoryListDAO productCategoryListDAO;
    public void setProductCategoryListDAO(ProductCategoryListDAO productCategoryListDAO) {
        this.productCategoryListDAO = productCategoryListDAO;
    }

    @Override
    public PageInfo productCategoryList(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<ProductCategory> productCategories = productCategoryListDAO.productCategoryList();
        PageInfo pageInfo = new PageInfo(productCategories);
        return pageInfo;
    }

    @Override
    public PageInfo productCategoryListByParentId(int parentId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<ProductCategory> productCategories = productCategoryListDAO.productCategoryListByParentId(parentId);
        PageInfo pageInfo = new PageInfo(productCategories);
        return pageInfo;
    }

    @Override
    public OptionsVO productCategoryOptions() {
        return productCategoryListDAO.productCategoryOptions();
    }


}
