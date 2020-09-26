package com.mall.admin.product.attribute.category.list.service;

import com.github.pagehelper.PageInfo;

/*
 *   作者：官宣轩
 *   日期：2020-09-24
 */
public interface ProductAttributeListService {

    public PageInfo productAttributeListById(int productAttributeCategoryId, int pageNum, int pageSize);

    public PageInfo productParameterListById(int productAttributeCategoryId, int pageNum, int pageSize);

}
