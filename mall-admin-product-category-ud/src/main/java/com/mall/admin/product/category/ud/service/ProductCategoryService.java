package com.mall.admin.product.category.ud.service;

import com.mall.common.pojo.ProductCategory;

/*
 *   作者：官宣轩
 *   日期：2020-09-27
 */
public interface ProductCategoryService {

    public boolean productCategoryDel(Integer productCategoryId);   //删除商品分类

    public boolean productCategoryUpdate(ProductCategory productCategory); //修改商品分类

}
