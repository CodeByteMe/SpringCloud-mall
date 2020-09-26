package com.mall.admin.product.category.list.service;

import com.github.pagehelper.PageInfo;
import com.mall.common.vo.OptionsVO;

import java.util.List;

/*
 *   作者：官宣轩
 *   日期：2020-09-24
 */
public interface ProductCategoryListService {

    public PageInfo productCategoryList(int pageNum, int pageSize);

    public PageInfo productCategoryListByParentId(int parentId ,int pageNum, int pageSize);

    public List<OptionsVO> productCategoryOptions(); //下拉框

}
