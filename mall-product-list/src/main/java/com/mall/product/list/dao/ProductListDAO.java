package com.mall.product.list.dao;

import com.mall.common.pojo.Product;
import com.mall.common.pojo.SkuStock;

import java.util.List;

/*
 *   作者：官宣轩
 *   日期：2020-09-21
 */
public interface ProductListDAO {

    public List<Product> productList(String adminId);

    public List<Product> productAllList();

    /**
     * 根据商品id查询商品套餐详情
     * @param productId
     * @return
     */
    public Product productDetailByProductId(String productId);

    /**
     * 根据商品id查询商品套餐详情
     * @param productId
     * @return
     */
    public List<SkuStock> getSkuStockByProductId(String productId);
}
