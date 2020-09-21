package com.mall.product.list.service;

import com.github.pagehelper.PageInfo;

/*
 *   作者：官宣轩
 *   日期：2020-09-21
 */
public interface ProductListService {

    public PageInfo productList(int pageNum,int pageSize);

}
