package com.mall.admin.product.sku.service;

import com.mall.common.pojo.SkuStock;

import java.util.List;

/*
 *   作者：官宣轩
 *   日期：2020-09-26
 */
public interface ProductSkuService {

    public List<SkuStock> productSkuList(String productId); //套餐查询

    public boolean productSkuAdd(SkuStock skuStock); //套餐添加

    public boolean productSkuDel(String skuId); //套餐删除

}
