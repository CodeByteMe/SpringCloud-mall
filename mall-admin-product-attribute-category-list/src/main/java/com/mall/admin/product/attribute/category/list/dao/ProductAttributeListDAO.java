package com.mall.admin.product.attribute.category.list.dao;

import com.mall.common.pojo.ProductAttribute;

import java.util.List;

/*
 *   作者：官宣轩
 *   日期：2020-09-24
 */
public interface ProductAttributeListDAO {

    public List<ProductAttribute> productAttributeListById(int productAttributeCategoryId); //查询属性列表

    public List<ProductAttribute> productParameterListById(int productAttributeCategoryId); //查询参数列表

}
