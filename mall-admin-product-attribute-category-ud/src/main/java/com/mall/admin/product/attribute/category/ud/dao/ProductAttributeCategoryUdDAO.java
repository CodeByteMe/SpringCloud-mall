package com.mall.admin.product.attribute.category.ud.dao;

import org.apache.ibatis.annotations.Param;

public interface ProductAttributeCategoryUdDAO {

    public boolean productAttributeCategoryUpdate(@Param("name") String name, @Param("id") Integer id);    //修改商品分类属性

    public boolean productAttributeCategoryDel(Integer id);   //删除商品分类属性

}
