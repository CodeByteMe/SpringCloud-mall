package com.mall.product.list.service;

import com.github.pagehelper.PageInfo;
import com.mall.common.pojo.Product;
import com.mall.common.pojo.SkuStock;

import java.util.List;

/*
 *   作者：官宣轩
 *   日期：2020-09-21
 */
public interface ProductListService {

    public PageInfo productList(int pageNum,int pageSize,String adminId);
    public Product productDetailByProductId(String productId);
    public List<SkuStock> getSkuStockByProductId(String productId);

    PageInfo producAlltList(Integer page, Integer pageSize);
}
