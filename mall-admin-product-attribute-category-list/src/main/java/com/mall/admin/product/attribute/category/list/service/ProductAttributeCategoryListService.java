package com.mall.admin.product.attribute.category.list.service;


import com.github.pagehelper.PageInfo;
import com.mall.common.pojo.ProductAttributeCategory;

import java.util.List;

/*
 *   作者：官宣轩
 *   日期：2020-09-24
 */
public interface ProductAttributeCategoryListService {

    public PageInfo productAttributeCategoryList(int pageNum, int pageSize);

    public List<ProductAttributeCategory> productAttributeCategoryListAll();

}
