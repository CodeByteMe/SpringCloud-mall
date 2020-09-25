package com.mall.admin.product.category.list.dao;

import com.mall.common.pojo.ProductCategory;
import com.mall.common.vo.OptionsVO;

import java.util.List;

/*
 *   作者：官宣轩
 *   日期：2020-09-24
 */
public interface ProductCategoryListDAO {

    public List<ProductCategory> productCategoryList();

    public List<ProductCategory> productCategoryListByParentId(int parentId);

    public OptionsVO productCategoryOptions(); //下拉框

}
